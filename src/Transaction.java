import java.util.*;
public  class Transaction {
    protected double amount;
    protected String originatingAccountId;
    protected String resultingAccountId;
    protected String transactionReason;

    public Transaction(double amount, String originatingAccountId, String resultingAccountId, String transactionReason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }
    public Transaction(double amout,String transactionReason){
        this.amount=amout;
        this.transactionReason=transactionReason;
    }
    public String getTransactionReason(){
        return transactionReason;
    }

}
