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

		inventoryService.addItem("RC Car", "High-speed remote control car", 5, "pcs", "Vehicles", true);
		logger.info("RC Car added to inventory");

		inventoryService.addItem("Battery Pack", "Rechargeable battery", 10, "pcs", "Accessories", true);
		logger.info("Battery Pack added to inventory");

		try {
			inventoryService.addItem("Battery Pack Duplicate Serial Number", "Rechargeable battery", 10, "pcs", "Accessories", true);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
		}

		System.out.println("---------------------------------------");
		System.out.println("✅ Inventory items added successfully!");
		System.out.println("---------------------------------------");

		// Display All Items
		System.out.println("📌 Displaying all inventory items:");
		logger.info("Triggering displayAllItems() method");
		inventoryController.displayAllItems();
		System.out.println("---------------------------------------");

		System.out.println("🔄 Updating 'RC Car' quantity to 8...");
		logger.info("Triggering updateItem() method on RC Car");
		inventoryController.updateItem(0, "RC Car", "High-speed remote control car", 8, "pcs",  "Vehicles", true);
		logger.info("RC Car updated successfully!");

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying updated inventory items:");
		logger.info("Triggering displayAllItems() method");
		inventoryController.displayAllItems();

		System.out.println("---------------------------------------");
		System.out.println("📌 Displaying all low stock items:");
		int threshold = 10;
		logger.info("Displaying all low stock items with threshold " + threshold);
		List<InventoryItem> lowCost = inventoryController.getLowStockItems(threshold);
		lowCost.stream().forEach(System.out::println);
		System.out.println("---------------------------------------");
	}
}
