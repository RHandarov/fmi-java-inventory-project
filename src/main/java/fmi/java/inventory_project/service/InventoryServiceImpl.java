package fmi.java.inventory_project.service;

import fmi.java.inventory_project.dto.InventoryItemDTO;
import fmi.java.inventory_project.mapper.InventoryItemMapper;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.ItemCategory;
import fmi.java.inventory_project.repository.InventoryItemRepository;
import fmi.java.inventory_project.repository.ItemCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final InventoryItemMapper inventoryItemMapper;

    @Override
    public List<InventoryItemDTO> getAllItems() {
        return itemRepository
                .getAllItems()
                .stream()
                .map(inventoryItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryItemDTO addItem(String name, String description, int quantity, String unit, String category, boolean borrowable) {
        ItemCategory itemCategory = itemCategoryRepository
                .getItemCategoryByName(category)
                .orElseGet(() -> {
                    ItemCategory newCategory = new ItemCategory(category);
                    itemCategoryRepository.addItemCategory(newCategory);
                    return newCategory;
                });

        InventoryItem newItem = new InventoryItem(name,
                description,
                quantity,
                "",
                unit,
                itemCategory,
                borrowable);

        itemRepository.addItem(newItem);
        return inventoryItemMapper.toDTO(newItem);
    }

    @Override
    public Optional<InventoryItemDTO> getItemById(Integer id) {
        return itemRepository
                .getItemById(id)
                .map(inventoryItemMapper::toDTO);
    }

    @Override
    public boolean deleteItemById(Integer id) {
        return itemRepository.deleteItemById(id);
    }

    @Override
    public List<InventoryItemDTO> getLowStockItems(int threshold) {
        return getAllItems()
                .stream()
                .filter(item -> item.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateItem(Integer id, String name, String description, int quantity, String unit, String category, boolean borrowable) {
        ItemCategory itemCategory = itemCategoryRepository
                .getItemCategoryByName(category)
                .orElseGet(() -> {
                    ItemCategory newCategory = new ItemCategory(category);
                    itemCategoryRepository.addItemCategory(newCategory);
                    return newCategory;
                });

        Optional<InventoryItem> existingItem = itemRepository.getItemById(id);
        if (existingItem.isPresent()) {
            InventoryItem item = existingItem.get();
            item.setName(name);
            item.setDescription(description);
            item.setQuantity(quantity);
            item.setCategory(itemCategory);
            item.setBorrowable(borrowable);
            item.setQuantity(quantity);

            itemRepository.updateItem(item);

            return true;
        }

        return false;
    }
}
