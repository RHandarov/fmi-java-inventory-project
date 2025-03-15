package service;

import model.InventoryItem;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<InventoryItem> getAllItems();
    void addItem(String name, String description, int quantity, String unit, String category, boolean borrowable);
    Optional<InventoryItem> getItemById(Integer id);
    boolean deleteItemById(Integer id);
    List<InventoryItem> getLowStockItems(int threshold);
    boolean updateItem(InventoryItem updatedItem);
}
