package _aTemplate;


import org.testng.Assert;

import java.util.*;

import static org.testng.AssertJUnit.assertEquals;

// 找到一个数所有的因子
public class Divisors {

    // IMP: 推荐!!
    public static List<Integer> divisors(int num) {

        List<Integer> res = new ArrayList<>();
        // 因数检查范围 (1, √num]
        for (int j = 1; j * j <= num; j++) { // j是现在试的因子
            if (num % j == 0) { // num含有j的因子
                if (j != 1) { // 这个因子不是1
                    // j, 当前数是含有j因子的第一个数
                    res.add(j);
                }
                int other = num / j; // other * j == num
                if (other != 1) { // num含有other的因子
                    res.add(other);
                }
            }
        }
        return res;
    }

    // 求一个数所有的因子, 一次求一对, 一定要从1开始
    // 最后防止重复, 比如limit*limit = num
    public static List<Integer> divisors1(int num) {

        List<Integer> res = new ArrayList<>();
        int limit = (int) Math.sqrt(num); // 1 ~ 根号num
        for (int j = 1; j < limit; j++) { // j是现在试的因子
            if (num % j == 0) { // num含有j的因子
                if (j != 1) { // 这个因子不是1
                    // j, 当前数是含有j因子的第一个数
                    res.add(j);
                }
                int other = num / j; // other * j == num
                if (other != 1) { // num含有other的因子
                    res.add(other);
                }
            }
        }
        if (num % limit == 0 && limit != 1) {
            res.add(limit);
        }

        return res;
    }


    public static void main(String[] args) {
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int num = (int) (Math.random() * Integer.MAX_VALUE);
            var ans1 = divisors(num);
            var ans2 = divisors1(num);
            assertEquals(ans1, ans2);
        }
        System.out.println("Test Done.");
    }

}
