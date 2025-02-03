package kz.csoqamqor.AccountingCard.controller;

import kz.csoqamqor.AccountingCard.model.Card;
import kz.csoqamqor.AccountingCard.service.CardService;
import kz.csoqamqor.AccountingCard.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v3")
public class CardController {
    private final CardService cardService;
    @Autowired
    private final InventoryService inventoryService;

    public CardController(CardService cardService, InventoryService inventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/all")
    public String getAllCards(Model model) {
        List<Card> cards = cardService.getAllCards().get();
        model.addAttribute("cards", cards);
        return "all-cards"; // Шаблон для отображения всех карт
    }

    @GetMapping("/create")
    public String createCardForm(Model model) {
        model.addAttribute("card", new Card());
        return "create-card"; // Шаблон для создания карты
    }

    @PostMapping("/create")
    public String createCard(@ModelAttribute Card card) {
        cardService.addCard(card);
        return "redirect:/all"; // Перенаправление на страницу всех карт
    }

    // Страница для добавления позиций в карту
    @GetMapping("/{cardId}/add-position")
    public String addPositionForm(@PathVariable Long cardId, Model model) {
        Optional<Card> cardOpt = cardService.getCardById(cardId);
        if (cardOpt.isPresent()) {
            model.addAttribute("card", cardOpt.get());
            model.addAttribute("inventories", inventoryService.getAllInventory().get());
            return "card/add-position"; // Шаблон для добавления позиций в карту
        }
        return "redirect:/card/all";
    }

    @PostMapping("/{cardId}/add-position")
    public String addPositionToCard(@PathVariable Long cardId,
                                    @RequestParam Long inventoryId,
                                    @RequestParam Double quantity) {
        cardService.addInventoryToCard(cardId, inventoryId, quantity);
        return "redirect:/card/all"; // Перенаправление на страницу всех карт
    }

    @GetMapping("/delete/{id}")
    public String deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return "redirect:/card/all"; // Перенаправление на страницу всех карт
    }
}
