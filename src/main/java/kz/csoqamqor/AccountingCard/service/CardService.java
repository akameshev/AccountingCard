package kz.csoqamqor.AccountingCard.service;

import kz.csoqamqor.AccountingCard.model.Card;
import kz.csoqamqor.AccountingCard.model.Inventory;
import kz.csoqamqor.AccountingCard.model.Position;
import kz.csoqamqor.AccountingCard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private final InventoryService inventoryService;
    private final CardRepository cardRepository;
    public CardService(CardRepository cardRepository, InventoryService inventoryService) {
        this.cardRepository = cardRepository;
        this.inventoryService = inventoryService;
    }
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public Optional<List<Card>> getAllCards() {
        return Optional.of(cardRepository.findAll());
    }
    public Optional<Card> addCard(Card card) {
        return Optional.of(cardRepository.save(card));
    }
    public Optional<Card> updateCard(Long id, Card card) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isPresent()) {
            Card cardToUpdate = cardOptional.get();
            cardToUpdate.setPositions(card.getPositions());
            return Optional.of(cardRepository.save(cardToUpdate));
        }
        return Optional.empty();
    }
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public Optional<Inventory> addInventoryToCard(Long cardId,Long inventoryId,Double quantity) {
        Optional<Inventory> inventoryOptional = inventoryService.getInventoryById(inventoryId);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            Position position = new Position();
            position.setInventory(inventory);
                if (sufficiencyCheck(inventory.getCount(),position.getQuantity())){
                position.setQuantity(quantity);
                } else {
                    position.setQuantity(0D);
                }
            Optional<Card> cardOptional = this.getCardById(cardId);
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                card.addPosition(position);
                this.updateCard(card.getId(), card);
            } return Optional.empty();
        }
        return Optional.empty();
    }

    public boolean sufficiencyCheck(Double quantityInventory, Double quantityCard) {
        return quantityCard <= quantityInventory;
    }

}
