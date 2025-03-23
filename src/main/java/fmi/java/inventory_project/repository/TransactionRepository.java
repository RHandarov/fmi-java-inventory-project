package fmi.java.inventory_project.repository;

import fmi.java.inventory_project.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private static Map<Integer, Transaction> TRANSACTION_TABLE = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("Transaction shouldn't be null");
        }

        if (TRANSACTION_TABLE.containsKey(transaction.getId())) {
            throw new IllegalArgumentException(
                    String.format("Transaction with id %d already exists",
                            transaction.getId())
            );
        }

        TRANSACTION_TABLE.put(transaction.getId(), transaction);
    }

    public boolean deleteTransactionById(Integer id) {
        if (!TRANSACTION_TABLE.containsKey(id)) {
            return false;
        }

        TRANSACTION_TABLE.remove(id);
        return true;
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        if (!TRANSACTION_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(TRANSACTION_TABLE.get(id));
    }

    public List<Transaction> getAllTransactions() {
        return TRANSACTION_TABLE.values().stream().toList();
    }

    public List<Transaction> getOverdueTransactions() {
        return TRANSACTION_TABLE.values()
                .stream()
                .filter(transaction -> !transaction.isReturned() && transaction.getReturnDate().isBefore(Instant.now()))
                .collect(Collectors.toList());
    }

    public boolean updateTransaction(Transaction transaction) {
        if (TRANSACTION_TABLE.containsKey(transaction.getId())) {
            TRANSACTION_TABLE.put(transaction.getId(), transaction);
            return true;
        }

        return false;
    }
}
