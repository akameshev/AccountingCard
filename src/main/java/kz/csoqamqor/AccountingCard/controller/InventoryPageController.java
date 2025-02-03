package kz.csoqamqor.AccountingCard.controller;

import kz.csoqamqor.AccountingCard.model.Inventory;
import kz.csoqamqor.AccountingCard.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v2")
public class InventoryPageController {
    private final InventoryService inventoryService;
    @Autowired
    public InventoryPageController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @GetMapping
    public String getAllInventory(Model model) {
        List<Inventory> inventories = inventoryService.getAllInventory().get();
        model.addAttribute("inventories", inventories);
        return "inventory-all.html";
    }
    @GetMapping("/")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        Optional<List<Inventory>> optionalInventories = inventoryService.getAllInventory();
        return optionalInventories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public String getInventoryById(@PathVariable Long id, Model model) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryById(id);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            model.addAttribute("inventory", inventory);
            return "inventory.html";
        }
        return "404.html";
    }

    @GetMapping("/add")
    public String addInventory() {
        return "inventory-create.html";
    }
    @PostMapping("/add")
    public String createInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.saveInventory(inventory);
        return "redirect:/v2";
    }
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventoryById(id);
        return "redirect:/v2";
    }



}
