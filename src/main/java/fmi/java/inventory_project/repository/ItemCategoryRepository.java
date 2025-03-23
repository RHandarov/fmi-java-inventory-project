package fmi.java.inventory_project.repository;

import fmi.java.inventory_project.model.ItemCategory;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@NoArgsConstructor
public class ItemCategoryRepository {
    private static Map<Integer, ItemCategory> ITEM_CATEGORY_TABLE = new HashMap<>();

    public void addItemCategory(ItemCategory itemCategory) {
        if (itemCategory == null) {
            throw new NullPointerException("Item category shouldn't be null");
        }

        if (ITEM_CATEGORY_TABLE.containsKey(itemCategory.getId())) {
            throw new IllegalArgumentException(
                    String.format("Item category with id %d already exists",
                            itemCategory.getId())
            );
        }

        ITEM_CATEGORY_TABLE.put(itemCategory.getId(), itemCategory);
    }

    public boolean deleteItemCategoryById(Integer id) {
        if (!ITEM_CATEGORY_TABLE.containsKey(id)) {
            return false;
        }

        ITEM_CATEGORY_TABLE.remove(id);
        return true;
    }

    public Optional<ItemCategory> getItemCategoryById(Integer id) {
        if (!ITEM_CATEGORY_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(ITEM_CATEGORY_TABLE.get(id));
    }

    public Optional<ItemCategory> getItemCategoryByName(String name) {
        return ITEM_CATEGORY_TABLE.values()
                .stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();
    }

    public List<ItemCategory> getAllItemCategories() {
        return ITEM_CATEGORY_TABLE.values().stream().toList();
    }
}
