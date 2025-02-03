package kz.csoqamqor.AccountingCard.service;

import kz.csoqamqor.AccountingCard.model.Inventory;
import kz.csoqamqor.AccountingCard.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    //region Methods
    public Optional<List<Inventory>> getAllInventory() {
        return Optional.of(inventoryRepository.findAll());
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Optional<Inventory> getInventoryByName(String name) {
        return Optional.ofNullable(inventoryRepository.findByName(name));
    }

    public void deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
    }
    public Optional<Inventory> saveInventory(Inventory inventory) {
        return Optional.of(inventoryRepository.save(inventory));
    }

    public Optional<Inventory> updateInventory(Long id,Inventory inventory) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            inventoryOptional.get().setName(inventory.getName());
            inventoryOptional.get().setDescription(inventory.getDescription());
            inventoryOptional.get().setCount(inventory.getCount());
            inventoryOptional.get().setUnitOfMeasurement(inventory.getUnitOfMeasurement());
            return Optional.of(inventoryRepository.save(inventoryOptional.get()));
        }
        return Optional.empty();
    }

    public Optional<Inventory> deleteInventory(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            inventoryRepository.deleteById(id);
        }
        return inventoryOptional;
    }
    //endregion


}
