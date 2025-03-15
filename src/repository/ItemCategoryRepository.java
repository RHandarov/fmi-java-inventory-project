package repository;

import model.ItemCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemCategoryRepository {
    private static Map<Integer, ItemCategory> ITEM_CATEGORY_TABLE = new HashMap<>();

    public static void addItemCategory(ItemCategory itemCategory) {
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

    public static boolean deleteItemCategoryById(Integer id) {
        if (!ITEM_CATEGORY_TABLE.containsKey(id)) {
            return false;
        }

        ITEM_CATEGORY_TABLE.remove(id);
        return true;
    }

    public static Optional<ItemCategory> getItemCategoryById(Integer id) {
        if (!ITEM_CATEGORY_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(ITEM_CATEGORY_TABLE.get(id));
    }

    public static List<ItemCategory> getAllItemCategories() {
        return ITEM_CATEGORY_TABLE.values().stream().toList();
    }
}
