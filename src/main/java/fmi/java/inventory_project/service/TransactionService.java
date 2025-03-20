package fmi.java.inventory_project.service;

import fmi.java.inventory_project.model.ClubMember;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    Integer borrowItem(ClubMember member, InventoryItem item, int days);
    List<Transaction> getAllTransactions();
    boolean returnItem(Integer transactionId);
    List<Transaction> getOverdueTransactions();
    boolean updateTransaction(Transaction updatedTransaction);
}
