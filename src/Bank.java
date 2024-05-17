import java.util.*;
public class Bank{
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFee;
    private double totalTransferAmount;
    private double flatFee;
    private double percentFee;


    public Bank(String bankName, double flatFee, double percentFee) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFee = 0.0;
        this.totalTransferAmount = 0.0;
        this.flatFee = flatFee;
        this.percentFee = percentFee;
    }


    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountId) throws Exception {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        throw new Exception("Account not found");
    }
    public void performTransfer(String originatingAccountId, String resultingAccountId, double amount, boolean isFlatFee) throws Exception {
        Account origin = getAccount(originatingAccountId);
        Account result = getAccount(resultingAccountId);
        double fee = isFlatFee ? flatFee : amount * percentFee / 100;

        if (origin.getBalance() < amount + fee) {
            throw new Exception("Insufficient funds in originating account: Available balance is " + origin.getBalance() + ", but tried to transfer " + amount + " with a fee of " + fee);
        }

        origin.withdraw(amount + fee);
        result.deposit(amount);

        totalTransactionFee += fee;
        totalTransferAmount += amount;
    }
    public void withdraw(String accountId, double amount, boolean isFlatFee) throws Exception {
        Account account = getAccount(accountId);
        double fee = isFlatFee ? flatFee : amount * percentFee / 100;

        if (account.getBalance() < amount + fee) {
            throw new Exception("Insufficient funds: Available balance is " + account.getBalance() + ", but tried to withdraw " + amount + " with a fee of " + fee);
        }

        account.withdraw(amount + fee);
        totalTransactionFee += fee;
    }
    public void deposit(String accountId, double amount) throws Exception {
        Account account = getAccount(accountId);
        account.deposit(amount);
    }
    public List<Account> getAccounts() {
        return accounts;
    }

    public List<String> getTransactions(String accountId) throws Exception {
        Account account = getAccount(accountId);
        return account.getTransactionHistory();
    }

    public double getAccountBalance(String accountId) throws Exception {
        return getAccount(accountId).getBalance();
    }

    public double getTotalTransactionFee() {
        return totalTransactionFee;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

}
