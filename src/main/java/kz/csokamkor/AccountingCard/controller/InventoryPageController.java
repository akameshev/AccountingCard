package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/inventories")
public class InventoryPageController {
    private final InventoryService inventoryService;

    public InventoryPageController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/all")
    public String getAllInventory(Model model) {
        List<Inventory> inventories = inventoryService.findAll().get();
        model.addAttribute("inventories", inventories);
        return "inventories/inventory-all.html";
    }
    @GetMapping("/{id}")
    public String getInventoryById(@PathVariable Long id, Model model) {
        Optional<Inventory> inventory = inventoryService.findById(id);
        if (inventory.isPresent()) {
            model.addAttribute("inventory", inventory.get());
            return "inventories/inventory-detail";
        }
        return "not-found";
    }
    @GetMapping("/add")
    public String createInventory() {
        return "inventories/inventory-add";
    }
    @PostMapping("/update/{id}")
    public String updateInventory(@PathVariable Long id, @ModelAttribute("inventory") Inventory inventory) {
        inventoryService.update(id, inventory);
        return "redirect:/inventories/all";
    }

    @PostMapping("/add")
    public String addInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.save(inventory);
        return "redirect:/inventories/all";
    }
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteById(id);
        return "redirect:/inventories/all";
    }



}
