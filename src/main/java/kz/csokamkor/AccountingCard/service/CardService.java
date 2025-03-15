package kz.csokamkor.AccountingCard.service;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public Card update(Long id, Card card) {
        Card updatedCard = cardRepository.findById(id).orElse(null);
        if (updatedCard != null) {
            updatedCard.setUserName(card.getUserName());
            updatedCard.setCardInventories(card.getCardInventories());
            updatedCard.setCreationDate(card.getCreationDate());
            return cardRepository.save(updatedCard);
        }
        return null;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    public void deleteInventory(Long id, Long cardInventoryId) {
        Card card = cardRepository.findById(id).orElse(null);
        if (card != null) {
            card.getCardInventories().remove(cardInventoryId);
        }
    }
}
