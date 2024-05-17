import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;
    private Account acc1;
    private Account acc2;

    @BeforeEach
    void setUp() {
        bank = new Bank("LinkPlusAl", 10, 5.0);
        acc1 = new Account("1", "Klaudio", 100.0);
        acc2 = new Account("2", "Kociu", 200.0);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
    }


    @Test
    void testCreateAccount() {
        Account account = new Account("3", "New_user", 1800.0);
        bank.addAccount(account);
        assertEquals(3, bank.getAccounts().size());
    }

    @Test
    void testDeposit() throws Exception {
        bank.deposit("1", 50.0);
        assertEquals(150.0, acc1.getBalance());
    }


    @Test
    void testWithdrawWithFlatFee() throws Exception {
        bank.withdraw("1", 50.0, true);
        assertEquals(40.0, acc1.getBalance());
    }

    @Test
    void testWithdrawWithPercentFee() throws Exception {
        bank.withdraw("1", 50.0, false);
        assertEquals(47.5, acc1.getBalance());
    }

    @Test
    void testPerformTransferWithFlatFee() throws Exception {
        bank.performTransfer("1", "2", 50.0, true);
        assertEquals(40.0, acc1.getBalance());
        assertEquals(250.0, acc2.getBalance());
    }

    @Test
    void testPerformTransferWithPercentFee() throws Exception {
        bank.performTransfer("1", "2", 50.0, false);
        assertEquals(47.5, acc1.getBalance());
        assertEquals(250.0, acc2.getBalance());
    }

    @Test
    void testPerformTransferInsufficientFunds() {
        Exception exception = assertThrows(Exception.class, () -> {
            bank.performTransfer("1", "2", 100.0, true);
        });
        assertEquals("Insufficient funds in originating account: Available balance is 100.0, but tried to transfer 100.0 with a fee of 10.0", exception.getMessage());
    }


    @Test
    void testGetAccountBalance() throws Exception {
        double balance = bank.getAccountBalance("1");
        assertEquals(100.0, balance);
    }

    @Test
    void testGetAccountBalanceAccountNotFound() {
        Exception exception = assertThrows(Exception.class, () -> {
            bank.getAccountBalance("999");
        });
        assertEquals("Account not found", exception.getMessage());
    }

    @Test
    void testListAccounts() {
        assertEquals(2, bank.getAccounts().size());
    }

    @Test
    void testListTransactions() {
        bank.addTransaction(new Transaction(50.0, "Test Transfer"));
        assertEquals(1, bank.getTransactions().size());
    }
}
