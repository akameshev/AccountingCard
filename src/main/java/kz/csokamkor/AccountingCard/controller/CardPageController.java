package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.service.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cards")
public class CardPageController {
    private final CardService cardService;

    public CardPageController(CardService cardService) {
        this.cardService = cardService;
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

}
