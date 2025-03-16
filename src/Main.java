import controller.InventoryController;
import controller.TransactionController;
import model.ClubMember;
import model.InventoryItem;
import service.InventoryService;
import service.InventoryServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;

public class Main {
    private static InventoryController inventoryController;
    private static TransactionController transactionController;

    public static void main(String[] args) {
        initApp();
        addSampleItems(inventoryController);

        System.out.println("All items:");
        inventoryController.showAllItems();

        System.out.println("All low stock items: ");
        inventoryController.showLowStockItems();

        InventoryItem sampleItem = inventoryController.getItemById(0);
        ClubMember sampleMember = new ClubMember("John", "Doe", "john.doe@sample.com");
        System.out.println("Item #1 quantity " + sampleItem.getQuantity());
        Integer transactionId = transactionController.borrowItem(sampleMember, sampleItem, 3);
        System.out.println("Item #1 quantity " + sampleItem.getQuantity());

        System.out.println("All transactions: ");
        transactionController.showAllTransactions();

        transactionController.returnItem(transactionId);

        System.out.println("Item #1 quantity " + sampleItem.getQuantity());
    }

    private static void initApp() {
        InventoryService inventoryService = new InventoryServiceImpl();
        inventoryController = new InventoryController(inventoryService);

        TransactionService transactionService = new TransactionServiceImpl(inventoryService);
        transactionController = new TransactionController(transactionService, inventoryService);
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