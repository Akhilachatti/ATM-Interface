public class Account {
    private String accountNumber;
    private double balance;
    private Customer customer;

    public Account(String accountNumber, double initialBalance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.customer = customer;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Customer getCustomer() {
        return customer;
    }
}
