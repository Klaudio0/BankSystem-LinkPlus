import java.util.*;
public class Account {
    private String accountId;
    private String userName;
    private double balance;
    private List<String> transactionHistory;


    public Account(String accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();

    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (balance < amount) {
            throw new Exception("Insufficient funds");
        }
        balance -= amount;

    }
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
