package kz.csoqamqor.AccountingCard.repository;

import kz.csoqamqor.AccountingCard.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Inventory findByName(String name);
}
