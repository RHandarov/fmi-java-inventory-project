package service;

import model.InventoryItem;
import model.ItemCategory;
import repository.InventoryItemRepository;
import repository.ItemCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public List<InventoryItem> getAllItems() {
        return InventoryItemRepository.getAllItems();
    }

    @Override
    public void addItem(String name, String description, int quantity, String unit, String category, boolean borrowable) {
        ItemCategory itemCategory = ItemCategoryRepository
                .getItemCategoryByName(category)
                .orElseGet(() -> {
                    ItemCategory newCategory = new ItemCategory(category);
                    ItemCategoryRepository.addItemCategory(newCategory);
                    return newCategory;
                });

        InventoryItemRepository.addItem(new InventoryItem(name,
                description,
                quantity,
                "",
                category,
                itemCategory,
                borrowable));
    }

    @Override
    public Optional<InventoryItem> getItemById(Integer id) {
        return InventoryItemRepository.getItemById(id);
    }

    @Override
    public boolean deleteItemById(Integer id) {
        return InventoryItemRepository.deleteItemById(id);
    }

    @Override
    public List<InventoryItem> getLowStockItems(int threshold) {
        return getAllItems()
                .stream()
                .filter(item -> item.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateItem(InventoryItem updatedItem) {
        if (!InventoryItemRepository.deleteItemById(updatedItem.getId())) {
            return false;
        }

        InventoryItemRepository.addItem(updatedItem);
        return true;
    }
}
