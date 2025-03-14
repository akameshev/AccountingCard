package kz.csokamkor.AccountingCard.controller;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.model.entities.CardInventory;
import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.service.CardInventoryService;
import kz.csokamkor.AccountingCard.service.CardService;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cards")
public class CardPageController {
    private final CardService cardService;
    private final InventoryService inventoryService;
    CardInventoryService cardInventoryService;

    public CardPageController(CardService cardService,
                              InventoryService inventoryService,
                              CardInventoryService cardInventoryService) {
        this.cardService = cardService;
        this.inventoryService = inventoryService;
        this.cardInventoryService = cardInventoryService;
    }

    /**
     * @return общий список сущетсвующих карточек учета инвентаря
     */
//    @GetMapping("/all")
//    public String getAllCards(Model model) {
//        model.addAttribute("cards", cardService.findAll());
//        return "cards/cards-all";
//    }


    @GetMapping("/all")
    public String getAllCards(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                              Model model) {
        List<Card> allCards = cardService.findAll();

        // Фильтрация по дате
        List<Card> filteredCards = allCards.stream()
                .filter(card -> (dateFrom == null || !card.getCreationDate().isBefore(dateFrom)) &&
                        (dateTo == null || !card.getCreationDate().isAfter(dateTo)))
                .collect(Collectors.toList());

        model.addAttribute("cards", filteredCards);
        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateTo", dateTo);
        return "cards/cards-all";
    }

    /**
     * @param id - Идентификатор карточки
     * @return более детализированную информацию по карточке,
     * с возможностью добавления новых позиций инвентаря
     * */
    @GetMapping("/detail/{id}")
    public String getCardById(@PathVariable Long id, Model model) {
        model.addAttribute("basket", cardService.findById(id).getCardInventories());
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
        return "redirect:/cards/all";
    }

    /**
     * @param id - Идентификатор карточки
     * @return страницу с общим перечнем инвентаря,
     * при удалении позиция просто исчезает
     */
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

// region PutMethod_Old
//    @PutMapping("/{id}/add-inventory/{InventoryId}/{amount}")
//    public String addInventory(@PathVariable Long id,
//                               @PathVariable Long inventoryId,
//                               @PathVariable double amount) {
//        CardInventory cardInventory = new CardInventory();
//        cardInventoryService.addCardInventory(cardInventory, inventoryId, amount);
//        Optional<Card> cardOptional = Optional.ofNullable(cardService.findById(id));
//        if (cardOptional.isPresent()) {
//            Card card = cardOptional.get();
//            card.addCardInventory(cardInventory);
//            cardService.save(card);
//            return "cards/" + id;
//        }
//        return "not-found";
//    }
    //endregion

    /**
     * @param id - Идентификатор карточки
     * @param inventoryId - идентификатор инвентаря
     * @param amount - количество инвентаря для добавления в карту
     * @return страницу с детализацией по карте, с добавленным инвентарем,
     * при добавлении инвентаря в карту, количество со склада автоматически списывается
     */
    @PostMapping("/{id}/add-inventory")
    public String addInventory(@PathVariable Long id,
                               @RequestParam Long inventoryId,
                               @RequestParam double amount) {
        Optional<Card> cardOptional = Optional.of(cardService.findById(id));
        if (cardOptional.isPresent()) {
            if (cardService.findById(id)
                    .findCardInventoryByName(inventoryService
                            .findById(inventoryId)
                            .get()
                            .getName()) != null) {
                CardInventory cardInventory = cardOptional
                        .get()
                        .findCardInventoryByName(inventoryService.findById(inventoryId)
                                .get()
                                .getName());
                cardInventory.setQuantity(cardInventory.getQuantity() + amount);
                cardInventoryService.save(cardInventory);
                Inventory inventory = inventoryService.findById(inventoryId).get();
                inventory.changeQuantity(amount);
                inventoryService.save(inventory);
                return "redirect:/cards/detail/" + id.toString();

            } else {
                CardInventory cardInventory = new CardInventory();
                cardInventoryService.addCardInventory(cardInventory, inventoryId, amount);
                Card card = cardOptional.get();
                card.addCardInventory(cardInventory);
                cardService.save(card);
                return "redirect:/cards/detail/" + id.toString();
            }
        }
        return "not-found";
    }


}
