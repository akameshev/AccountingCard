package kz.csokamkor.AccountingCard.repository;

import kz.csokamkor.AccountingCard.model.entities.CardInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardInventoryRepository extends JpaRepository<CardInventory, Long> {
    Optional<CardInventory> findByName(String name);
    Optional<CardInventory> findByCardInventoryNumber(UUID cardInventoryNumber);
}
