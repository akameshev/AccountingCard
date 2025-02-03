package kz.csoqamqor.AccountingCard.controller;

import kz.csoqamqor.AccountingCard.model.Card;
import kz.csoqamqor.AccountingCard.service.CardService;
import kz.csoqamqor.AccountingCard.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v4")
public class CardControllerJSON {
    private final CardService cardService;
    @Autowired
    private final InventoryService inventoryService;

    public CardControllerJSON(CardService cardService, InventoryService inventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
    }

    public ResponseEntity<List<Card>> getAllCards() {
        Optional<List<Card>> cards = cardService.getAllCards();
        if (cards.isPresent()) {
            return ResponseEntity.ok(cards.get());
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Card> getCardById(Long id) {
        Optional<Card> card = cardService.getCardById(id);
        if (card.isPresent()) {
            return ResponseEntity.ok(card.get());
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Card> createCard(Card card) {
        return ResponseEntity.ok(cardService.addCard(card).get());
    }
    public ResponseEntity<Card> updateCard(Long id, Card card) {
        Optional<Card> cardOptional = cardService.getCardById(id);
        if (cardOptional.isPresent()) {
            cardService.updateCard(id, card);
            return ResponseEntity.ok(cardOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Card> deleteCard(Long id) {
        Optional<Card> cardOptional = cardService.getCardById(id);
        if (cardOptional.isPresent()) {
            cardService.deleteCard(id);
            return ResponseEntity.ok(cardOptional.get());
        }
        return ResponseEntity.ok(cardOptional.get());
    }
}
