package kz.csokamkor.AccountingCard.repository;

import kz.csokamkor.AccountingCard.model.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByInventoryNumber(UUID inventoryNumber);
}
