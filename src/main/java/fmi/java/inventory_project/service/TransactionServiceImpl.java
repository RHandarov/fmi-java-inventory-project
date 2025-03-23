package fmi.java.inventory_project.service;

import fmi.java.inventory_project.model.ClubMember;
import fmi.java.inventory_project.model.InventoryItem;
import fmi.java.inventory_project.model.Transaction;
import fmi.java.inventory_project.model.TransactionType;
import fmi.java.inventory_project.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private InventoryService inventoryService;
    private TransactionRepository transactionRepository;

    @Override
    public Integer borrowItem(ClubMember member, InventoryItem item, int days) {
        if (item.getQuantity() == 0) {
            throw new IllegalArgumentException("Item quantity is 0");
        }

        if (!item.isBorrowable()) {
            throw new IllegalArgumentException("Item should be borrowable");
        }

        Instant borrowDate = Instant.now();
        Transaction newTransaction = new Transaction(member,
                item,
                TransactionType.BORROW,
                borrowDate,
                days);
        transactionRepository.addTransaction(newTransaction);
        item.setQuantity(item.getQuantity() - 1);
        inventoryService.updateItem(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getQuantity() - 1,
                item.getUnitOfMeasurement(),
                item.getCategory().getName(),
                item.isBorrowable());

        return newTransaction.getId();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    @Override
    public boolean returnItem(Integer transactionId) {
        Optional<Transaction> transactionOptional =
                transactionRepository.getTransactionById(transactionId);

        if (transactionOptional.isEmpty()) {
            return false;
        }

        Transaction transaction = transactionOptional.get();
        InventoryItem item = inventoryService.getItemById(transaction.getId()).get();

        if (!item.isBorrowable()) {
            return false;
        }

        updateTransaction(transactionId,
                transaction.getReturnDate(),
                true);
        return inventoryService.updateItem(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getQuantity() - 1,
                item.getUnitOfMeasurement(),
                item.getCategory().getName(),
                item.isBorrowable());
    }

    @Override
    public List<Transaction> getOverdueTransactions() {
        return transactionRepository.getOverdueTransactions();
    }

    @Override
    public boolean updateTransaction(Integer id,
                                     Instant returnDate,
                                     boolean returned) {
        Optional<Transaction> existingTransaction = transactionRepository.getTransactionById(id);
        if (existingTransaction.isPresent()) {
            Transaction transaction = existingTransaction.get();
            transaction.setReturned(returned);
            transaction.setReturnDate(returnDate);

            transactionRepository.updateTransaction(transaction);

            return true;
        }

        return false;
    }
}
