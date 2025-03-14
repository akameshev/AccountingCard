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
		inventory1.setDescription("Трусы мужские 50% хлопокб 50% полиэстер");
		inventory1.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory1.setQuantity(50.0);

		Inventory inventory2 = new Inventory();
		inventory2.setName("Трусы женские");
		inventory2.setDescription("Трусы мужские 50% хлопокб 50% полиэстер");
		inventory2.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory2.setQuantity(50.0);

		Inventory inventory3 = new Inventory();
		inventory3.setName("Шапка зимняя");
		inventory3.setDescription("Шапка зимняя 80% хлопок 20% полиэстер");
		inventory3.setUnitOfMeasurement(UnitOfMeasurement.PIECE);
		inventory3.setQuantity(10.0);

		InventoryService inventoryService = context.getBean(InventoryService.class);

		inventoryService.save(inventory1);
		inventoryService.save(inventory2);
		inventoryService.save(inventory3);

		Card akameshev = new Card();
		akameshev.setUserName("akameshev");
		akameshev.setCreationDate(LocalDate.now());

		Card ekirillova = new Card();
		ekirillova.setUserName("ekirillova");
		ekirillova.setCreationDate(LocalDate.now());

		CardService cardService = context.getBean(CardService.class);
		cardService.save(akameshev);
		cardService.save(ekirillova);


	}

}
