package LeetCode.Contest.DW76;

import java.util.Arrays;

public class Problem_6062_ATM {

    private long[] counts;
    private final int[] value = {500, 200, 100, 50, 20};
    private long total;

    public Problem_6062_ATM() {
        counts = new long[5]; // 500, 200, 100, 50, 20个数
    }

    public void deposit(int[] banknotesCount) {
        int N = banknotesCount.length;
        for (int i = N - 1; i >= 0; i--) {
            counts[N - 1 - i] += banknotesCount[i];
            total += (long) banknotesCount[i] * value[N - 1 - i];
        }
    }

    public int[] withdraw(int amount) {
        if (amount > total) {
            return new int[]{-1};
        }
        int[] ans;
        if (check(amount)) {
            ans = new int[5];
            checkout(amount, ans);
        } else {
            return new int[]{-1};
        }
        return ans;
    }

    private void checkout(int amount, int[] ans) {
        int N = 5;
        for (int i = 0; i < N; i++) {
            if (counts[i] != 0) {
                if (counts[i] * value[i] >= amount) {
                    counts[i] -= (amount) / value[i];
                    ans[N - 1 - i] = (amount) / value[i];
                    total -= amount;
                    amount = 0;
                    break;
                } else {
                    amount -= counts[i] * value[i];
                    ans[N - 1 - i] = (int) counts[i];
                    total -= counts[i] * value[i];
                    counts[i] = 0;
                }
            }
        }
    }


    private boolean check(int amount) {
        long[] dup = Arrays.copyOf(counts, counts.length);
        for (int i = 0; i < 5; i++) {
            if (dup[i] != 0) {
                if (dup[i] * value[i] >= amount) {
                    dup[i] -= (amount) / value[i];
                    amount = 0;
                    break;
                } else {
                    amount -= dup[i] * value[i];
                    dup[i] = 0;
                }
            }
        }
        return amount == 0;
    }

    public static void main(String[] args) {

        Problem_6062_ATM sl = new Problem_6062_ATM();
        int[] bankNotesCount = {0, 0, 1, 2, 1};
        sl.deposit(bankNotesCount);
        var ans = sl.withdraw(600);
        printArr(ans);
    }

    private static void printArr(int[] ans) {
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
