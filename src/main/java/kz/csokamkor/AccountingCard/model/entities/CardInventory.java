package kz.csokamkor.AccountingCard.model.entities;

import jakarta.persistence.*;
import kz.csokamkor.AccountingCard.model.enums.UnitOfMeasurement;

import java.util.UUID;

/**
 * Эта сущность по сути является оберткой над Inventory.
 * Данное решение было принято по причине необходимости добавления инвентаря
 * в карточку учета
 */
@Entity
@Table(name = "cardInventories")
public class CardInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private UUID cardInventoryNumber;
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

    public UUID getCardInventoryNumber() {
        return cardInventoryNumber;
    }


    public void setCardInventoryNumber(UUID cardInventoryNumber) {
        this.cardInventoryNumber = cardInventoryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
