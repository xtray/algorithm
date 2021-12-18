package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_400_FindNthDigit {

    // 1~9 : 9 * 10^(digit-1)
    // 10~99: 90 * 2
    // 100~999: 900 * 3
    //  100~199: 100
    //  200~299: 100
    //  300~399
    // 1000~9999: 9000 * 4
    // ...
    // 1000000000 ~2147483647 * 10
    public int findNthDigit(int n) {
        if (n <= 9) {
            return n;
        }

        // 先确定上限
        Map<Integer, Integer> map = getMaxDigit(n);
        // 找到 n 的 digit 位数再仔细查找
        // digit-1 不到
        int digit = map.size();
        int count = map.get(digit - 1);
        int first = 0;
        // 比如 n:1000, digit = 3
        // 100~999
        int leftCount = n - count;
        int slot = (int) Math.pow(10, digit - 1) * digit;
        first = (leftCount + slot) / slot;
        leftCount = leftCount % slot;

        int start = first * (int) Math.pow(10, digit - 1) + leftCount/digit;
        int pos = leftCount % digit;

        //如果 pos == 0, 就是
        if (pos ==0 ) return first;
        return ((start +1)% (int) Math.pow(10, digit - 1))/10 ;
    }

    private Map<Integer, Integer> getMaxDigit(int n) {
        int base = 1;
        int sum = 9;
        int digit = 1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 9);
        while (sum <= n) {
            base *= 10;
            digit++;
            sum += 9 * base * digit;
            map.put(digit, sum);
        }
        return map;
    }

    public int findNthDigit2(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        long s = (long) Math.pow(10, len - 1);
        s += n / len - 1;
        n -= len * (n / len);
        return n == 0 ? (int) (s % 10) : (int) ((s + 1) / Math.pow(10, len - n) % 10);
    }


    public static void main(String[] args) {
//        int n = 11;
        int n = 1004;
        var res = new Problem_400_FindNthDigit().findNthDigit(n);
        System.out.println(res);
    }
}
