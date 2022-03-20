package LeetCode;

public class Problem_2043_Bank {

    private long[] balance;

    public Problem_2043_Bank(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (!checkAccount(account1) || !checkAccount(account2)) {
            return false;
        }
        if(balance[account1-1] >= money) {
            balance[account1-1] -= money;
            balance[account2-1] += money;
            return true;
        }
        return false;
    }

    private boolean checkAccount(int account) {
        return account >= 1 && account <= balance.length;
    }

    public boolean deposit(int account, long money) {
        if(!checkAccount(account)) {
            return false;
        }
        balance[account-1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if(!checkAccount(account)) {
            return false;
        }
        if(balance[account-1] >= money) {
            balance[account-1]-=money;
            return true;
        }
        return false;
    }
}
