package fmi.java.inventory_project.model;

import java.time.Instant;
import java.util.Arrays;

public class InventoryItem {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_DESCRIPTION_LENGTH = 255;
    private static final String[] ALLOWED_UNITS = {
            "pcs",
            "kg",
            "milliliters"
    };

    private static int nextId = 0;

    private static void validateName(String name) {
        if (name == null) {
            throw new NullPointerException("Item name shouldn't be null");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name length should not exceed " + MAX_NAME_LENGTH);
        }
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Description length should not exceed " + MAX_NAME_LENGTH);
        }
    }

    private static void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity should be non-negative");
        }
    }

    private static void validateUnitOfMeasurement(String unitOfMeasurement) {
        if (Arrays.stream(ALLOWED_UNITS).noneMatch(allowedUnit -> allowedUnit.equals(unitOfMeasurement))) {
            throw new IllegalArgumentException(String.format("Unit %s is not allowed", unitOfMeasurement));
        }
    }

    private int id;
    private String name;
    private String description;
    private int quantity;
    private String serialNumber;
    private String unitOfMeasurement;
    private ItemCategory category;
    private boolean borrowable;
    private Instant addedDate;

    public InventoryItem(String name,
                         String description,
                         int quantity,
                         String serialNumber,
                         String unitOfMeasurement,
                         ItemCategory itemCategory,
                         boolean borrowable) {
        unitOfMeasurement = unitOfMeasurement.toLowerCase();

        validateName(name);
        validateDescription(description);
        validateQuantity(quantity);
        validateUnitOfMeasurement(unitOfMeasurement);

        id = nextId++;
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

    public String getUnitOfMeasurement() {
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
