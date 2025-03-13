package repository;

import model.InventoryItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryItemRepository {
    private static final Map<Integer, InventoryItem> ITEM_TABLE = new HashMap<>();

    public static void addItem(InventoryItem item) {
        if (item == null) {
            throw new NullPointerException("Item shouldn't be null");
        }

        if (ITEM_TABLE.containsKey(item.getId())) {
            throw new IllegalArgumentException("This item already exists");
        }

        // TODO: Implement copy constructor
        ITEM_TABLE.put(item.getId(), item);
    }

    public static boolean deleteItemById(Integer id) {
        if (!ITEM_TABLE.containsKey(id)) {
            return false;
        }

        ITEM_TABLE.remove(id);
        return true;
    }

    public static Optional<InventoryItem> getItemById(Integer id) {
        if (!ITEM_TABLE.containsKey(id)) {
            return null;
        }

        return Optional.of(ITEM_TABLE.get(id));
    }
}
