import java.util.*;

public class Test {   //klasa Test e cila do therritet funksioni main dhe klasat e tjera

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("LinkPlusAl", 10.0, 5.0);   //krijoj nje klase Bank me konstruktor me argumenta

        while (true) {   //perdor nje while loop per te shfaqur cdo here menune sa here dal nga nje switch-case
            printMenu();   //nj emetode e cila do i shfaqi user nje liste me opsionet te cilat i ofron banka
            int choice = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (choice) {
                    case 1:
                        createAccount(scanner, bank);  //metode e cila krijon nje Account
                        break;
                    case 2:
                        deposit(scanner, bank);   //metode e cila depoziton para ne banke
                        break;
                    case 3:
                        withdraw(scanner, bank);  //metoda e cila nje user mund te terheqi para
                        break;
                    case 4:
                        performTransfer(scanner, bank);  //metode e cila nje user mund te kryej disa funksione
                        break;
                    case 5:
                        checkBalance(scanner, bank);    //metode e cila kontrollon gjendjen e llogarise se nje useri
                        break;
                    case 6:
                        listAccounts(bank);   //metode e cila liston Accounts te rregjistruar ne kete Banke
                        break;
                    case 7:
                        listTransactions(scanner, bank);  //metode e cila kthen listen e Transaksioneve
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {   //krijojme nje try-catch per te kapur nje error nese ndodh...
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Bank System ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Perform Transfer");
        System.out.println("5. Check Account Balance");
        System.out.println("6. List Accounts");
        System.out.println("7. List Transactions");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount(Scanner scanner, Bank bank) {
        /*Ne kete metode ne krijome nje Account nga Useri duke marr cdo argument te klases Account*/
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter account holder's name: ");
        String username = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(accountId, username, initialBalance);
        bank.addAccount(account);
        System.out.println("Account created successfully.");
    }

    private static void deposit(Scanner scanner, Bank bank) throws Exception {
        /*Ne kete metode ne kemi mundesi te depozitojme para ne llogari*/

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        bank.deposit(accountId, amount);
        System.out.println("Deposit successful.");
    }

    private static void withdraw(Scanner scanner, Bank bank) throws Exception{
        /*Ne kete metode ne kemi mundesi te terheqim para nga nje llogari e nje Account duke u bazuar te ajo ID*/
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        System.out.print("Use flat fee (true/false): ");
        boolean isFlatFee = scanner.nextBoolean();

        bank.withdraw(accountId, amount, isFlatFee);
        System.out.println("Withdrawal successful.");
    }

    private static void performTransfer(Scanner scanner, Bank bank) throws Exception {
        /*Ne kete metode ne mund te kryejme nje transaksion nga nje Account te nje tjeter*/
        System.out.print("Enter originating account ID: ");
        String originatingAccountId = scanner.nextLine();
        System.out.print("Enter resulting account ID: ");
        String resultingAccountId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        System.out.print("Use flat fee (true/false): ");
        boolean isFlatFee = scanner.nextBoolean();

        bank.performTransfer(originatingAccountId, resultingAccountId, amount, isFlatFee);
        System.out.println("Transfer successful.");


    }

    private static void checkBalance(Scanner scanner, Bank bank) throws Exception {
        /*Ne kete metode ne mund te kontrollojme balancen e nje Account Useri*/
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        double balance = bank.getAccountBalance(accountId);
        System.out.println("Account Balance: $" + balance);
    }

    private static void listAccounts(Bank bank) {
        /*Kjo metode kthen listen e Account te rregjistruar ne nje Bank*/
        List<Account> accounts = bank.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("--- List of Accounts ---");
            for (Account account : accounts) {
                System.out.println("ID: " + account.getAccountId() + ", Name: " + account.getUserName() + ", Balance: $" + account.getBalance());
            }
        }
    }
    private static void listTransactions(Scanner scanner, Bank bank) throws Exception {
        /*Ne kete metode*/

        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        List<String> transactions = bank.getTransactions(accountId);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
        } else {
            System.out.println("--- List of Transactions ---");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }

}
