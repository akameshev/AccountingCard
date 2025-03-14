package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/json")
public class InventoryController {
    @Autowired
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllInventory() {
        Optional<List<Inventory>> inventories = inventoryService.findAll();
        if (inventories.isPresent()) {
            List<Inventory> inventoryList = inventories.get();
            return ResponseEntity.ok(inventoryList);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<Inventory> updateQuantity(@PathVariable Long id,
                                                     @PathVariable double quantity) {
        Optional<Inventory> inventory = inventoryService.findById(id);
        if (inventory.isPresent()) {
            inventory.get().setQuantity(quantity);
            inventoryService.save(inventory.get());
            return ResponseEntity.ok(inventory.get());
        }
        return ResponseEntity.notFound().build();
    }


}
