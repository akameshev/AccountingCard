package kz.csokamkor.AccountingCard;

import kz.csokamkor.AccountingCard.model.entities.Card;
import kz.csokamkor.AccountingCard.model.entities.Inventory;
import kz.csokamkor.AccountingCard.model.enums.UnitOfMeasurement;
import kz.csokamkor.AccountingCard.service.CardService;
import kz.csokamkor.AccountingCard.service.InventoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class AccountingCardApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AccountingCardApplication.class, args);

		Inventory inventory1 = new Inventory();
		inventory1.setName("Трусы мужские");
		inventory1.setDescription("Трусы мужские 50% хлопок 50% полиэстер");
		inventory1.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory1.setQuantity(50.0);

		Inventory inventory2 = new Inventory();
		inventory2.setName("Трусы женские");
		inventory2.setDescription("Трусы женские 50% хлопок 50% полиэстер");
		inventory2.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory2.setQuantity(50.0);

		Inventory inventory3 = new Inventory();
		inventory3.setName("Шапка зимняя");
		inventory3.setDescription("Шапка зимняя 80% хлопок 20% полиэстер");
		inventory3.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory3.setQuantity(50.0);

		Inventory inventory4 = new Inventory();
		inventory4.setName("Станок для бритья");
		inventory4.setDescription("Станок для бритья Gillete, пластиковый корпус");
		inventory4.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory4.setQuantity(100.0);

		InventoryService inventoryService = context.getBean(InventoryService.class);

		inventoryService.save(inventory1);
		inventoryService.save(inventory2);
		inventoryService.save(inventory3);
		inventoryService.save(inventory4);

		Card recepient1 = new Card();
		recepient1.setUserName("Векшин Валентин Владимирович");
		recepient1.setCreationDate(LocalDate.of(2024,4,15));

		Card recepient2 = new Card();
		recepient2.setUserName("Згонник Анна Петровна");
		recepient2.setCreationDate(LocalDate.of(2025,1,9));

		CardService cardService = context.getBean(CardService.class);
		cardService.save(recepient1);
		cardService.save(recepient2);


	}

}
