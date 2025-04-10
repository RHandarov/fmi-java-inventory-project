package fmi.java.inventory_project.controller;

import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<InventoryItem>> displayAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
//            System.out.println("No inventory items available.");
        } else {
            return ResponseEntity.ofNullable(items);
//            items.forEach(item -> System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity()));
        }
    }

    public List<InventoryItem> getLowStockItems(int threshold) {
        return inventoryService.getLowStockItems(threshold);
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

    public void updateItem(Integer id, String name, String description, int quantity, String unit, String category, boolean borrowable) {
        if (inventoryService.updateItem(id, name, description, quantity, unit, category, borrowable)) {
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Update failed. Item not found.");
        }
    }

    public InventoryItem getItemById(Integer itemId) {
        Optional<InventoryItem> itemOptional = inventoryService.getItemById(itemId);
        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("Item with id " + itemId + " doesn't exists");
        }

        return itemOptional.get();
    }
}
