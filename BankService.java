import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    public void createAccount(String accountNumber, double initialBalance, Customer customer) {
        Account account = new Account(accountNumber, initialBalance, customer);
        accounts.put(accountNumber, account);
    }
    public double getBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) 
            return account.getBalance();
        else 
            throw new IllegalArgumentException("Account not found");

    }
    public Transaction deposit(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return new Transaction(accountNumber, amount, "deposit");
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
    public Transaction withdraw(String accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            double newBalance = account.getBalance() - amount;
            if (newBalance >= 0) {
                account.setBalance(newBalance);
                return new Transaction(accountNumber, amount, "withdraw");
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
}
