package fmi.java.inventory_project.mapper;

import fmi.java.inventory_project.dto.InventoryItemDTO;
import fmi.java.inventory_project.model.InventoryItem;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemMapper {
    public InventoryItemDTO toDTO(InventoryItem inventoryItem) {
        return new InventoryItemDTO(inventoryItem.getId(),
                inventoryItem.getName(),
                inventoryItem.getDescription(),
                inventoryItem.getQuantity(),
                inventoryItem.getSerialNumber(),
                inventoryItem.getUnitOfMeasurement(),
                inventoryItem.getCategory().getName(),
                inventoryItem.isBorrowable(),
                inventoryItem.getAddedDate());
    }
}
