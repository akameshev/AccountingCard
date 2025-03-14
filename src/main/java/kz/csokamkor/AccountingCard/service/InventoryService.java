package kz.csokamkor.AccountingCard.service;

import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    public Optional<Inventory> findById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        return inventory;
    }
    public Optional<List<Inventory>> findAll() {
        List<Inventory> inventory = inventoryRepository.findAll();
        return Optional.of(inventory);
    }
    @Transient
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

}
