package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.service.CardService;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cards/json")
public class CardController {

    private final CardService cardService;
    private final InventoryService inventoryService;

    public CardController(CardService cardService, InventoryService inventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cardList = cardService.findAll();
        if (cardList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cardList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        Optional<Card> optionalCard = Optional.of(cardService.findById(id));
        return optionalCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        card.setCreationDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        cardService.save(card);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id,@RequestBody Card card) {
        Optional<Card> optionalCard = Optional.of(cardService.findById(id));
        Card updatedCard = optionalCard.get();
        updatedCard.setCardInventories(card.getCardInventories());
        updatedCard.setCreationDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        cardService.save(updatedCard);
        return ResponseEntity.ok(updatedCard);
    }

    @PutMapping("/{id}/{inventoryId}")
    public ResponseEntity<Card> updateCardInventories(@PathVariable Long id,@PathVariable Long inventoryId) {
        Optional<Card> optionalCard = Optional.of(cardService.findById(id));
        Card updatedCard = optionalCard.get();
        updatedCard.addInventory(inventoryService.findById(inventoryId).get());
        cardService.save(updatedCard);
        return ResponseEntity.ok(updatedCard);
    }



}
