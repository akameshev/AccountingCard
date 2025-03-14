package kz.csokamkor.AccountingCard.repository;

import kz.csokamkor.AccountingCard.model.entities.CardInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInventoryRepository extends JpaRepository<CardInventory, Long> {
}
