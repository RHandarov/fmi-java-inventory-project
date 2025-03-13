package model;

public class ItemCategory {
    private static int NEXT_ID = 0;

    private int id;
    private String name;
    private String description;

    public ItemCategory(String name) {
        this(name, "");
    }

    public ItemCategory(String name, String description) {
        id = NEXT_ID++;
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
