package fmi.java.inventory_project.repository;

import fmi.java.inventory_project.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TransactionRepository {
    private static Map<Integer, Transaction> TRANSACTION_TABLE = new HashMap<>();

    public static void addTransaction(Transaction transaction) {
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

    public static boolean deleteTransactionById(Integer id) {
        if (!TRANSACTION_TABLE.containsKey(id)) {
            return false;
        }

        TRANSACTION_TABLE.remove(id);
        return true;
    }

    public static Optional<Transaction> getTransactionById(Integer id) {
        if (!TRANSACTION_TABLE.containsKey(id)) {
            return Optional.empty();
        }

        return Optional.of(TRANSACTION_TABLE.get(id));
    }

    public static List<Transaction> getAllTransactions() {
        return TRANSACTION_TABLE.values().stream().toList();
    }
}
