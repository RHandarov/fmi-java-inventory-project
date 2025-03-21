package service;

import model.ClubMember;
import model.InventoryItem;
import model.Transaction;

import java.util.List;

public interface TransactionService {
    Integer borrowItem(ClubMember member, InventoryItem item, int days);
    List<Transaction> getAllTransactions();
    boolean returnItem(Integer transactionId);
    List<Transaction> getOverdueTransactions();
    boolean updateTransaction(Transaction updatedTransaction);
}
