package fmi.java.inventory_project.service;

import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.ItemCategory;
import fmi.java.inventory_project.repository.InventoryItemRepository;
import fmi.java.inventory_project.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryItemRepository itemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public List<InventoryItem> getAllItems() {
        return itemRepository.getAllItems();
    }

    @Override
    public void addItem(String name, String description, int quantity, String unit, String category, boolean borrowable) {
        ItemCategory itemCategory = itemCategoryRepository
                .getItemCategoryByName(category)
                .orElseGet(() -> {
                    ItemCategory newCategory = new ItemCategory(category);
                    itemCategoryRepository.addItemCategory(newCategory);
                    return newCategory;
                });
        itemRepository.addItem(new InventoryItem(name,
                description,
                quantity,
                "",
                unit,
                itemCategory,
                borrowable));
    }

    @Override
    public Optional<InventoryItem> getItemById(Integer id) {
        return itemRepository.getItemById(id);
    }

    @Override
    public boolean deleteItemById(Integer id) {
        return itemRepository.deleteItemById(id);
    }

    @Override
    public List<InventoryItem> getLowStockItems(int threshold) {
        return getAllItems()
                .stream()
                .filter(item -> item.getQuantity() < threshold)
                .collect(Collectors.toList());
    }

//    @Override
//    public boolean updateItem(InventoryItem updatedItem) {
//        if (!InventoryItemRepository.deleteItemById(updatedItem.getId())) {
//            return false;
//        }
//
//        InventoryItemRepository.addItem(updatedItem);
//        return true;
//    }

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
