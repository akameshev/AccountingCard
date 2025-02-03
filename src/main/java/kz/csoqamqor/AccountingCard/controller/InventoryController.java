package kz.csoqamqor.AccountingCard.controller;

import kz.csoqamqor.AccountingCard.model.Inventory;
import kz.csoqamqor.AccountingCard.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v1")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        Optional<List<Inventory>> optionalInventories = inventoryService.getAllInventory();
        return optionalInventories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryById(id);
        return optionalInventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{name}")
    public ResponseEntity<Inventory> getInventoryByName(@PathVariable String name) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryByName(name);
        return optionalInventory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        if (inventory != null) {
            inventoryService.saveInventory(inventory);
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryById(id);
        if (optionalInventory.isPresent()) {
            inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        Optional<Inventory> optionalInventory = inventoryService.getInventoryById(id);
        if (optionalInventory.isPresent()) {
            inventoryService.deleteInventoryById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
