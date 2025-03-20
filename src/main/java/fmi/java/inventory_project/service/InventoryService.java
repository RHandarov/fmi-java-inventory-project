package fmi.java.inventory_project.service;

import fmi.java.inventory_project.model.InventoryItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InventoryService {
    List<InventoryItem> getAllItems();
    void addItem(String name, String description, int quantity, String unit, String category, boolean borrowable);
    Optional<InventoryItem> getItemById(Integer id);
    boolean deleteItemById(Integer id);
    List<InventoryItem> getLowStockItems(int threshold);
    boolean updateItem(InventoryItem updatedItem);
}
