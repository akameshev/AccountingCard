package kz.csokamkor.AccountingCard.service;

import jakarta.transaction.Transactional;
import kz.csokamkor.AccountingCard.AccountingCardApplication;
import kz.csokamkor.AccountingCard.model.entities.CardInventory;
import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.repository.CardInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardInventoryService {
    private final CardInventoryRepository cardInventoryRepository;
    private final InventoryService inventoryService;

    public CardInventoryService(CardInventoryRepository cardInventoryRepository, InventoryService inventoryService) {
        this.cardInventoryRepository = cardInventoryRepository;
        this.inventoryService = inventoryService;
    }
    public List<CardInventory> findAll() {
        return cardInventoryRepository.findAll();
    }
    public CardInventory save(CardInventory cardInventory) {
        return cardInventoryRepository.save(cardInventory);
    }
    public CardInventory findById(Long id) {
        Optional<CardInventory> inventoryItem = cardInventoryRepository.findById(id);
        return inventoryItem.orElse(null);
    }
    public void delete(CardInventory cardInventory) {
        cardInventoryRepository.delete(cardInventory);
    }

    @Transactional
    public CardInventory addCardInventory(CardInventory cardInventory, Long inventoryId, Double amount) {
        Optional<Inventory> optionalInventory = inventoryService.findById(inventoryId);
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            cardInventory.setName(inventory.getName());
            cardInventory.setDescription(inventory.getDescription());
            cardInventory.setUnitOfMeasurement(inventory.getUnitOfMeasurement());
            cardInventory.setQuantity(amount);
            inventoryService.findById(inventoryId).ifPresent(inventoryItem -> {inventoryItem.changeQuantity(amount);});
            this.save(cardInventory);
            return cardInventory;
        }
        return null;
    }

    public Optional<CardInventory> findByName(String name) {
        return cardInventoryRepository.findByName(name);
    }



}
