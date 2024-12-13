public record BankAccount(int accountNumber, int balance) {
    public BankAccount(int accountNumber) {
        this(accountNumber,0);
    }
}
