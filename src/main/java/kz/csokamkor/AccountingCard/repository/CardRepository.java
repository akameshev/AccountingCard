package kz.csokamkor.AccountingCard.repository;

import kz.csokamkor.AccountingCard.model.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
