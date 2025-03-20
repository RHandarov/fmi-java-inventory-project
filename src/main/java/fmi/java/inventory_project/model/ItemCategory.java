package fmi.java.inventory_project.model;

public class ItemCategory {
    private static int nextId = 0;

    private int id;
    private String name;
    private String description;

    public ItemCategory(String name) {
        this(name, "");
    }

    public ItemCategory(String name, String description) {
        id = nextId++;
        this.name = name;
        this.description = description;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
