package service;

import model.ClubMember;
import model.InventoryItem;
import model.Transaction;
import model.TransactionType;
import repository.TransactionRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {
    private InventoryService inventoryService;

    public TransactionServiceImpl(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void borrowItem(ClubMember member, InventoryItem item, int days) {
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
