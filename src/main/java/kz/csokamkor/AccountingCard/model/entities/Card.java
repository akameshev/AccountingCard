package kz.csokamkor.AccountingCard.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Эта сущность предназначена для хранения карточки учета
 */
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    @OneToMany
    private List<CardInventory> cardInventories = new ArrayList<>();

    private LocalDate creationDate;

    public boolean hasItem;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<CardInventory> getCardInventories() {
        if (cardInventories != null) {
            hasItem = true;
        } else {
            hasItem = false;
        }
        return cardInventories;
    }

    public void setCardInventories(List<CardInventory> cardInventories) {
        if (cardInventories != null) {
            hasItem = true;
        } else {
            hasItem = false;
        }
        this.cardInventories = cardInventories;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isHasItem() {
        return hasItem;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    public List<CardInventory> addCardInventory(CardInventory cardInventory) {
        cardInventories.add(cardInventory);
        hasItem = true;
        return cardInventories;
    }

    public CardInventory findCardInventoryByName(String name) {
        for (CardInventory cardInventory : cardInventories) {
            if (cardInventory.getName().equals(name)) {
                hasItem = true;
                return cardInventory;
            }

        }
        return null;
    }
}
