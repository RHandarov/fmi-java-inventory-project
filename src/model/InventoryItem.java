package model;

import java.time.Instant;

public class InventoryItem {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_DESCRIPTION_LENGTH = 255;

    private static int NEXT_ID = 0;

    private static void validateName(String name) {
        if (name == null) {
            throw new NullPointerException("Item name shouldn't be null");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name length should not exceed " + MAX_NAME_LENGTH);
        }
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > 255) {
            throw new IllegalArgumentException("Description length should not exceed " + MAX_NAME_LENGTH);
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity should be non-negative");
        }
    }

    private int id;
    private String name;
    private String description;
    private int quantity;
    private String serialNumber;
    private UnitOfMeasurement unitOfMeasurement;
    private ItemCategory category;
    private boolean borrowable;
    private Instant addedDate;

    public InventoryItem(String name,
                         String description,
                         int quantity,
                         String serialNumber,
                         UnitOfMeasurement unitOfMeasurement,
                         ItemCategory itemCategory,
                         boolean borrowable) {
        validateName(name);
        validateDescription(description);
        validateQuantity(quantity);

        id = NEXT_ID++;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.serialNumber = serialNumber;
        this.unitOfMeasurement = unitOfMeasurement;
        category = itemCategory;
        this.borrowable = borrowable;
        addedDate = Instant.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public boolean isBorrowable() {
        return borrowable;
    }

    public Instant getAddedDate() {
        return addedDate;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    public void setQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }
}
