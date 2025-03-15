package kz.csokamkor.AccountingCard.model.entities;

import jakarta.persistence.*;
import kz.csokamkor.AccountingCard.model.enums.UnitOfMeasurement;

import java.util.UUID;

/**
 * Эта сущность предназначена для хранения инвентаря
 */
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID inventoryNumber = UUID.randomUUID();
    private String name;
    private String description;
    private UnitOfMeasurement unitOfMeasurement;
    private Double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(UUID inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public void changeQuantity(Double subtractible) {
        this.quantity -= subtractible;
    }
}
