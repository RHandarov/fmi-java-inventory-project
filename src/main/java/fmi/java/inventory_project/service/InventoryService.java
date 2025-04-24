package fmi.java.inventory_project.service;

import fmi.java.inventory_project.dto.InventoryItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InventoryService {
    List<InventoryItemDTO> getAllItems();
    InventoryItemDTO addItem(String name, String description, int quantity, String unit, String category, boolean borrowable);
    Optional<InventoryItemDTO> getItemById(Integer id);
    boolean deleteItemById(Integer id);
    List<InventoryItemDTO> getLowStockItems(int threshold);
    boolean updateItem(Integer id, String name, String description, int quantity, String unit, String category, boolean borrowable);
}
