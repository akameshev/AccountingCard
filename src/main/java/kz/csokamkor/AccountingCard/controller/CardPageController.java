package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.service.CardService;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cards")
public class CardPageController {
    private final CardService cardService;
    private final InventoryService inventoryService;

    public CardPageController(CardService cardService, InventoryService inventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String getAllCards(Model model) {
        model.addAttribute("cards", cardService.findAll());
        return "cards/cards-all";
    }

    @GetMapping("/{id}")
    public String getCardById(@PathVariable Long id, Model model) {
        model.addAttribute("basket",cardService.findById(id).getCardInventories());
        model.addAttribute("card", cardService.findById(id));
        List<Inventory> inventories = inventoryService.findAll().get();
        model.addAttribute("inventories", inventories);
        return "cards/card-detail";
    }

    @GetMapping("/add")
    public String addCard() {
        return "cards/card-add";
    }

    @PostMapping("/add")
    public String createCard(@ModelAttribute("card") Card card) {
        cardService.save(card);
        return "redirect:/cards";
    }

    @DeleteMapping("/{id}")
    public String deleteCard(@RequestParam("id") Long id) {
        cardService.deleteById(id);
        return "redirect:/cards";
    }

    @PutMapping("/{id}")
    public String updateCard(@ModelAttribute("card") Card card, @PathVariable Long id) {
        Optional<Card> cardOptional = Optional.of(cardService.findById(id));
        Card cardToUpdate = cardOptional.get();
        cardToUpdate.setCardInventories(card.getCardInventories());
        cardService.save(cardToUpdate);
        return "redirect:/cards";
    }

    @GetMapping("/{id}/add-inventory")
    public String addInventory(@PathVariable Long id, Model model) {
        Card card = cardService.findById(id);
        List<Inventory> inventories = inventoryService.findAll().get();
        model.addAttribute("card", card);
        model.addAttribute("inventories",inventories);
        return "cards/card-inventory-add";
    }


}
