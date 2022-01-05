package LeetCode;


import java.util.HashSet;
import java.util.Set;

public class Problem_507_CheckPerfectNumber {

    // 从 1 开始一直找到 n/2
    // 2147483647
    // 0100000000
    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }

        int ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                if (!set.contains(i)) {
                    ans += i;
                    set.add(i);
                }
                if (!set.contains(num / i)) {
                    ans += num / i;
                    set.add(num / i);
                }
            }
        }
        return ans == num;
    }

    // 从 1~根号 num 即可
    public boolean checkPerfectNumber2(int num) {
        if (num <= 1) {
            return false;
        }

        int ans = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                ans += i;
                if (i * i < num) { // i*i==num, 两个因子只算一个
                    ans += num / i;
                }
            }
        }
        return ans == num;
    }

    // 根据1~10^8打表结果的算法
    public boolean checkPerfectNumber3(int num) {
        return num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336;
    }

    public static void main(String[] args) {
        int num = 1;
        var ans = new Problem_507_CheckPerfectNumber().checkPerfectNumber2(num);
        System.out.println(ans);
        int maxNum = 100000000; // 打表找规律
        for(int i = 1;i<=maxNum;i++) {
            var res = new Problem_507_CheckPerfectNumber().checkPerfectNumber2(i);
            if(res) {
                System.out.println(i);
            }
        }
        System.out.println("FIN");
    }
}
