package fmi.java.inventory_project.controller;

import fmi.java.inventory_project.model.ClubMember;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.Transaction;
import fmi.java.inventory_project.service.InventoryService;
import fmi.java.inventory_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final InventoryService inventoryService;

    @Autowired
    public TransactionController(TransactionService transactionService,
                                 InventoryService inventoryService) {
        this.transactionService = transactionService;
        this.inventoryService = inventoryService;
    }

    public void showAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        transactions.forEach(transaction -> System.out.println(transaction.getId()));
    }

    public void showAllOverdueTransactions() {
        List<Transaction> transactions = transactionService.getOverdueTransactions();
        transactions.forEach(transaction -> System.out.println(transaction.getId()));
    }

    public Integer borrowItem(ClubMember member, InventoryItem item, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days should be positive");
        }

        if (inventoryService.getItemById(item.getId()).isEmpty()) {
            throw new IllegalArgumentException("Item doesn't exists");
        }

        return transactionService.borrowItem(member, item, days);
    }

    public void returnItem(Integer transactionId) {
        boolean success = transactionService.returnItem(transactionId);
        String message;
        if (success) {
            message =  "Item has been returned successfully";
        } else {
            message = "Item failed to return";
        }

        System.out.println(message);
    }
}
