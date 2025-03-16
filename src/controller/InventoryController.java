package controller;

import model.InventoryItem;
import service.InventoryService;

import java.util.List;
import java.util.Optional;

public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void showAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        items.forEach(item -> System.out.println(item.getName()));
    }

    public void showLowStockItems() {
        final int LOW_STOCK_THRESHOLD = 10;
        List<InventoryItem> items = inventoryService.getLowStockItems(LOW_STOCK_THRESHOLD);
        items.forEach(item -> System.out.println(item.getName()));
    }

    public void addItem(String name, String description, int quantity, String unit, String category, boolean borrowable) {
        try {
            inventoryService.addItem(name, description, quantity, unit, category, borrowable);
        } catch (Exception e) {
            System.out.println("Failed to add item: " + e.getMessage());
            return;
        }

        System.out.println("Item has been added successfully");
    }

    public InventoryItem getItemById(Integer itemId) {
        Optional<InventoryItem> itemOptional = inventoryService.getItemById(itemId);
        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("Item with id " + itemId + " doesn't exists");
        }

        return itemOptional.get();
    }
}
