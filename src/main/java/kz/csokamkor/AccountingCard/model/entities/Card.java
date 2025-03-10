package kz.csokamkor.AccountingCard.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    @ManyToMany
    private List<Inventory> cardInventories = new ArrayList<>();

    private LocalDate creationDate;

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


    public List<Inventory> getCardInventories() {
        return cardInventories;
    }

    public void setCardInventories(List<Inventory> cardInventories) {
        this.cardInventories = cardInventories;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Inventory> addInventory(Inventory inventory) {
        this.getCardInventories().add(inventory);
        return cardInventories;
    }
}
