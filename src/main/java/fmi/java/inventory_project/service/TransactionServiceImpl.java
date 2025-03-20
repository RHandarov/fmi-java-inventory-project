package fmi.java.inventory_project.service;

import fmi.java.inventory_project.model.ClubMember;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.Transaction;
import fmi.java.inventory_project.model.TransactionType;
import fmi.java.inventory_project.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private InventoryService inventoryService;

    public TransactionServiceImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public Integer borrowItem(ClubMember member, InventoryItem item, int days) {
        if (item.getQuantity() == 0) {
            throw new IllegalArgumentException("Item quantity is 0");
        }

        if (!item.isBorrowable()) {
            throw new IllegalArgumentException("Item should be borrowable");
        }

        Instant borrowDate = Instant.now();
        Transaction newTransaction = new Transaction(member.getId(),
                item.getId(),
                TransactionType.BORROW,
                1,
                borrowDate,
                borrowDate.plusSeconds(days * 86400L));
        TransactionRepository.addTransaction(newTransaction);
        item.setQuantity(item.getQuantity() - 1);
        inventoryService.updateItem(item);

        return newTransaction.getId();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return TransactionRepository.getAllTransactions();
    }

    @Override
    public boolean returnItem(Integer transactionId) {
        Optional<Transaction> transactionOptional =
                TransactionRepository.getTransactionById(transactionId);

        if (transactionOptional.isEmpty()) {
            return false;
        }

        Transaction transaction = transactionOptional.get();
        InventoryItem item = inventoryService.getItemById(transaction.getId()).get();

        if (!item.isBorrowable()) {
            return false;
        }

        transaction.setReturned(true);
        item.setQuantity(item.getQuantity() + 1);
        updateTransaction(transaction);
        inventoryService.updateItem(item);
        return true;
    }

    @Override
    public List<Transaction> getOverdueTransactions() {
        return getAllTransactions()
                .stream()
                .filter(transaction -> {
                    return !transaction.isReturned() && Instant.now().isAfter(transaction.getReturnDate());
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateTransaction(Transaction updatedTransaction) {
        if (!TransactionRepository.deleteTransactionById(updatedTransaction.getId())) {
            return false;
        }

        TransactionRepository.addTransaction(updatedTransaction);
        return true;
    }
}
