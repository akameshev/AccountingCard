package kz.csoqamqor.AccountingCard.controller;

import kz.csoqamqor.AccountingCard.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v3")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public String getAllCards(Model model) {
        model.addAttribute("cards", cardService.getAllCards());
        return "cards-all.html";
    }
}
