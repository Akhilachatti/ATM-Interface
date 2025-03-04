import javax.swing.*;

public class BankController {
    private BankService bankService = new BankService();
    double balance;
    public void start() {
        while (true) {
            String[] options = {"Create Account", "Check Balance", "Deposit", "Withdraw", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to the ATM", "ATM",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null, options, options[0]);

            switch (choice) {
                case 0:
                    createAccount();
                    break;
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    private void createAccount() {
        String name = JOptionPane.showInputDialog("Enter customer name:");
        String customerId = JOptionPane.showInputDialog("Enter customer ID:");
        Customer customer = new Customer(name, customerId);

        String accountNumber = JOptionPane.showInputDialog("Enter account number:");
        String initialBalanceStr = JOptionPane.showInputDialog("Enter initial balance:");
        double initialBalance = Double.parseDouble(initialBalanceStr);
        bankService.createAccount(accountNumber, initialBalance, customer);
        JOptionPane.showMessageDialog(null, "Account created successfully.");
    }
    private void checkBalance() {
        String accountNumber = JOptionPane.showInputDialog("Enter account number:");
        double balance = bankService.getBalance(accountNumber);
        JOptionPane.showMessageDialog(null, "Current balance: " + balance);
    }
    private void deposit() {
        String accountNumber = JOptionPane.showInputDialog("Enter account number:");
        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        double amount = Double.parseDouble(amountStr);
        Transaction transaction = bankService.deposit(accountNumber, amount);
        JOptionPane.showMessageDialog(null, "Deposit successful.");
        printReceipt(transaction);
    }
    private void withdraw() {
        String accountNumber = JOptionPane.showInputDialog("Enter account number:");

        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        double amount = Double.parseDouble(amountStr);
        double currentBalance = bankService.getBalance(accountNumber);
        if(amount<currentBalance)
        {
        Transaction transaction = bankService.withdraw(accountNumber, amount);
        JOptionPane.showMessageDialog(null, "Withdrawal successful.");
        printReceipt(transaction);
        }
        else{
            JOptionPane.showMessageDialog(null, "Insufficient Balance. Please try again.");
        }
    }

    private void printReceipt(Transaction transaction) {
        int choice = JOptionPane.showConfirmDialog(null, "Would you like a receipt?", "Receipt",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, transaction);
        }
    }
}
