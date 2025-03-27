package fmi.java.inventory_project;

import fmi.java.inventory_project.controller.InventoryController;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.service.InventoryService;
import fmi.java.inventory_project.service.logger.ConsoleLogger;
import fmi.java.inventory_project.service.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InventoryProjectApplication implements CommandLineRunner {
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private InventoryController inventoryController;

	@Autowired
	private Logger logger;

	public static void main(String[] args) {
		SpringApplication.run(InventoryProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 Application started successfully!");
		logger.info("🚀 Application started successfully!");
		logger.debug("Some debugging message");
		logger.error("Some error message");

		inventoryService.addItem("RC Car", "High-speed remote control car", 5, "pcs", "Vehicles", true);
		inventoryService.addItem("Battery Pack", "Rechargeable battery", 10, "pcs", "Accessories", true);

		try {
			inventoryService.addItem("Battery Pack Duplicate Serial Number", "Rechargeable battery", 10, "pcs", "Accessories", true);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		}

		System.out.println("---------------------------------------");
		System.out.println("✅ Inventory items added successfully!");
		System.out.println("---------------------------------------");

		// Display All Items
		System.out.println("📌 Displaying all inventory items:");
		inventoryController.displayAllItems();
		System.out.println("---------------------------------------");

		System.out.println("🔄 Updating 'RC Car' quantity to 8...");
		inventoryController.updateItem(0, "RC Car", "High-speed remote control car", 8, "pcs",  "Vehicles", true);

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying updated inventory items:");
		inventoryController.displayAllItems();

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying all low stock items:");
		int threshold = 10;
		List<InventoryItem> lowCost = inventoryController.getLowStockItems(threshold);
		lowCost.stream().forEach(System.out::println);
		System.out.println("---------------------------------------");
	}
}
