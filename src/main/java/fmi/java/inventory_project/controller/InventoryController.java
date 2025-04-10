package fmi.java.inventory_project.controller;

import fmi.java.inventory_project.dto.InventoryItemDTO;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryItem>> displayAllItems() {
        List<InventoryItem> items = inventoryService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ofNullable(items);
        }
    }

    @GetMapping("/low-stock/{threshold}")
    public ResponseEntity<List<InventoryItem>> getLowStockItems(@PathVariable int threshold) {
        List<InventoryItem> lowStock = inventoryService.getLowStockItems(threshold);
        if (lowStock.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ofNullable(lowStock);
    }

    @PostMapping
    public ResponseEntity<InventoryItemDTO> addItem(@RequestBody InventoryItemDTO inventoryItemDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(inventoryService.addItem(inventoryItemDTO.getName(),
                            inventoryItemDTO.getDescription(),
                            inventoryItemDTO.getQuantity(),
                            inventoryItemDTO.getUnitOfMeasurement(),
                            inventoryItemDTO.getCategory(),
                            inventoryItemDTO.isBorrowable()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public void updateItem(Integer id, String name, String description, int quantity, String unit, String category, boolean borrowable) {
        if (inventoryService.updateItem(id, name, description, quantity, unit, category, borrowable)) {
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Update failed. Item not found.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getItemById(@PathVariable Integer id) {
        Optional<InventoryItem> item = inventoryService.getItemById(id);
        if (item.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(item);
    }
}
