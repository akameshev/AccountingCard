package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.model.entities.CardInventory;
import kz.csokamkor.AccountingCard.service.CardService;
import kz.csokamkor.AccountingCard.service.CardInventoryService;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * По началу я испытывал некоторые сложности,
 * по крайне мере при взаимодействии с front частью
 * Именно поэтому я и создавал контроллер который отдавал бы JSON ответы,
 * чтобы убедиться что функционал действительно работает.
 * А после этого уже подключать front часть через Thymeleaf
 * * */

@Controller
@RequestMapping("/cards/json")
public class CardController {

    private final CardService cardService;
    private final InventoryService inventoryService;
    private final CardInventoryService cardInventoryService;

    public CardController(CardService cardService, InventoryService inventoryService, CardInventoryService cardInventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
        this.cardInventoryService = cardInventoryService;
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

    /**
     * @param id - Идентификатор карточки
     * @param inventoryId - идентификатор инвентаря
     * @param quantity - количество инвентаря для добавления в карту
     * @return JSON-объект, для добавления
     * достаточно указать в запросе(помимо пути) "quantity": value(Например 20)
     * */
    @PutMapping("/{id}/{inventoryId}/{quantity}")
    public ResponseEntity<Card> addCardInventories(@PathVariable Long id,
                                                   @PathVariable Long inventoryId,
                                                   @PathVariable double quantity) {
        CardInventory cardInventory = new CardInventory();
        cardInventoryService.addCardInventory(cardInventory,inventoryId,quantity);
        Optional<Card> optionalCard = Optional.of(cardService.findById(id));
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            card.addCardInventory(cardInventory);
            cardService.save(card);
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }



}
