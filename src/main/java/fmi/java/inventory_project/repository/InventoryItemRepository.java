package fmi.java.inventory_project.repository;

import fmi.java.inventory_project.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InventoryItemRepository {
    private static final Map<Integer, InventoryItem> ITEM_TABLE = new HashMap<>();

    public void addItem(InventoryItem item) {
        if (item == null) {
            throw new NullPointerException("Item shouldn't be null");
        }

        if (ITEM_TABLE.containsKey(item.getId())) {
            throw new IllegalArgumentException(
                    String.format("Item with id %d already exists", item.getId())
            );
        }

        ITEM_TABLE.put(item.getId(), item);
    }

    public boolean deleteItemById(Integer id) {
        if (!ITEM_TABLE.containsKey(id)) {
            return false;
        }

        ITEM_TABLE.remove(id);
        return true;
    }

    public Optional<InventoryItem> getItemById(Integer id) {
        if (!ITEM_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(ITEM_TABLE.get(id));
    }

    public List<InventoryItem> getAllItems() {
        return ITEM_TABLE.values().stream().toList();
    }

    public boolean updateItem(InventoryItem newItem) {
        if (ITEM_TABLE.containsKey(newItem.getId())) {
            ITEM_TABLE.put(newItem.getId(), newItem);
            return true;
        }

        return false;
    }
}
