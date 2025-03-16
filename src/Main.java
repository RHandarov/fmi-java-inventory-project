import controller.InventoryController;
import service.InventoryService;
import service.InventoryServiceImpl;

public class Main {
    public static void main(String[] args) {
        InventoryController inventoryController = getInventoryController();
        addSampleItems(inventoryController);

        System.out.println("All items:");
        inventoryController.showAllItems();

        System.out.println("All low stock items: ");
        inventoryController.showLowStockItems();
    }

    private static InventoryController getInventoryController() {
        InventoryService inventoryService = new InventoryServiceImpl();
        return new InventoryController(inventoryService);
    }

    private static void addSampleItems(InventoryController inventoryController) {
        inventoryController.addItem("Item #1",
                "some desc",
                20,
                "kg",
                "Cat #1",
                true);

        inventoryController.addItem("Item #2",
                "desc",
                25,
                "milliliters",
                "Cat #2",
                false);

        inventoryController.addItem("Item #3",
                "",
                5,
                "pcs",
                "Cat #2",
                true);
    }
}