package kz.csoqamqor.AccountingCard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    private Double quantity;

    public Position(Long number, Inventory inventory, Double quantity) {
        this.number = number;
        this.inventory = inventory;
        this.quantity = quantity;
    }

    public Position() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
