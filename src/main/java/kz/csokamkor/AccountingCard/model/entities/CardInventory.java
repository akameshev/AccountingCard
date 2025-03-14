package kz.csokamkor.AccountingCard.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "inventoryItems")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    private Double amount;

    public InventoryItem(Inventory inventory, Double amount) {
        this.inventory = inventory;
        this.amount = amount;
    }

    public InventoryItem() {

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
