package fmi.java.inventory_project.model;

import java.time.Instant;

public class Transaction {
    private static final int DAY_IN_SECONDS = 86400;

    private static int nextId = 0;

    private static void validateDates(Instant borrowDate, Instant returnDate) {
        if (returnDate.isBefore(borrowDate)) {
            throw new IllegalArgumentException("Return date shouldn't be before borrow date");
        }
    }

    private int id;
    private ClubMember clubMember;
    private InventoryItem inventoryItem;
    private TransactionType type;
    private Instant borrowDate;
    private Instant returnDate;
    private boolean returned;

    public Transaction(ClubMember member,
                       InventoryItem item,
                       TransactionType type,
                       Instant borrowDate,
                       int days) {
        this(member,
                item,
                type,
                borrowDate,
                borrowDate.plusSeconds((long) days * DAY_IN_SECONDS));
    }

    public Transaction(ClubMember member,
                       InventoryItem item,
                       TransactionType type,
                       Instant borrowDate,
                       Instant returnDate) {
        validateDates(borrowDate, returnDate);

        id = nextId++;
        this.clubMember = member;
        this.inventoryItem = item;
        this.type = type;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public ClubMember getClubMember() {
        return clubMember;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public TransactionType getType() {
        return type;
    }

    public Instant getBorrowDate() {
        return borrowDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void setReturnDate(Instant returnDate) {
        validateDates(borrowDate, returnDate);
        this.returnDate = returnDate;
    }


}
