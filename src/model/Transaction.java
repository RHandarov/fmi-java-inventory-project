package model;

import java.time.Instant;

public class Transaction {
    private static int NEXT_ID = 0;

    private static void validateQuantityUsed(int quantityUsed) {
        if (quantityUsed <= 0) {
            throw new IllegalArgumentException("Used quantity should be positive");
        }
    }

    private static void validateDates(Instant borrowDate, Instant returnDate) {
        if (returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date shouldn't be before borrow date");
        }
    }

    private int id;
    private int memberId;
    private int itemId;
    private TransactionType type;
    private int quantityUsed;
    private Instant borrowDate;
    private Instant returnDate;

    public Transaction(int memberId,
                       int itemId,
                       TransactionType type,
                       int quantityUsed,
                       Instant borrowDate,
                       Instant returnDate) {
        validateQuantityUsed(quantityUsed);
        validateDates(borrowDate, returnDate);

        id = NEXT_ID++;
        this.memberId = memberId;
        this.itemId = itemId;
        this.type = type;
        this.quantityUsed = quantityUsed;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getItemId() {
        return itemId;
    }

    public TransactionType getType() {
        return type;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public Instant getBorrowDate() {
        return borrowDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }
}
