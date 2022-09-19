package _Contest.Atcoder.ARC143;

import java.util.ArrayList;
import java.util.List;

public class Problem_A_ThreeIntegers {

    public static long minTimes(long a, long b, long c) {
        if (a == b && b == c) {
            return a;
        }
        return process(a, b, c);
    }

    private static long process(long a, long b, long c) {
        if (a == 0 && b == 0 && c == 0) {
            return 0;
        }
        if ((a == 0 && b == 0) || (a == 0 && c == 0) || (b == 0 && c == 0)) {
            return -1;
        }
        if (a < 0 || b < 0 || c < 0) {
            return -1;
        }
        // a,b  a,c  b,c
        long p1 = process(a - 1, b - 1, c);
        long p2 = process(a - 1, b, c - 1);
        long p3 = process(a, b - 1, c - 1);
        long p4 = process(a - 1, b - 1, c - 1);
        long ans = Integer.MAX_VALUE;
        if (p1 != -1) {
            ans = Math.min(ans, p1);
        }
        if (p2 != -1) {
            ans = Math.min(ans, p2);
        }
        if (p3 != -1) {
            ans = Math.min(ans, p3);
        }
        if (p4 != -1) {
            ans = Math.min(ans, p4);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans + 1;
    }

    public static long minTimes1(long a, long b, long c) {
        if (a == b && b == c) {
            return a;
        }
        return process1(a, b, c);
    }

    private static long process1(long a, long b, long c) {
        if (a == 0 && b == 0 && c == 0) {
            return 0;
        }
        if ((a == 0 && b == 0) || (a == 0 && c == 0) || (b == 0 && c == 0)) {
            return -1;
        }
        if (a < 0 || b < 0 || c < 0) {
            return -1;
        }
        List<Long> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.sort(Long::compareTo);
        // if ((a + 1 == b && b + 1 == c) || b == c) {
        //     return c;
        // }
        // return -1;
        return c;
    }

    // private void solve(final FastIO io, final String[] args) {
    //     io.setAutoFlush(false);
    //     io.setAutoOutFlush(false);
    //     /*
    //      * author: 31536000
    //      * AtCoder Regular Contest 143 A問題
    //      * 考察メモ
    //      * できることを考える
    //      * ある一つの要素が突出して高いとき、どうするか？
    //      * 2倍の速度で減らすことはできる、それが限界
    //      * つまり、maxがその他の和より大きいと不可
    //      * そうでない時は、maxとmidを減らす戦略を考える
    //      * するとあるタイミングでmax,min,minになって、後は頑張ると全部同じになるか(2,1,1減らせるので)
    //      *
    //      * さて、この戦略を取ったときの差について考える
    //      * max-mid, max-minのどちらかが1ずつ縮まり、最終的に0になる
    //      * この間は操作1しかできないね
    //      * で、後は操作2
    //      */
    //     long A = io.nextLong(), B = io.nextLong(), C = io.nextLong();
    //     long sum = A + B + C;
    //     long max = ACL.MathLib.max(A, B, C);
    //     io.println(sum >= 2 * max ? max : -1);
    // }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // long A = sc.nextLong();
        // long B = sc.nextLong();
        // long C = sc.nextLong();
        //
        // sc.close();

        // long A = 2;
        // long B = 2;
        // long C = 3;
        // long A = 2;
        // long B = 11;
        // long C = 11;
        // var ans = minTimes(A, B, C);
        // System.out.println(ans);
        // var ans1 = minTimes1(A, B, C);
        // System.out.println(ans1);

        int times = 10;
        int maxVal = 10;
        System.out.println("test start");
        for (int i = 0; i < times; i++) {
            long aa = (long) (Math.random() * maxVal) + 1;
            long bb = (long) (Math.random() * maxVal) + 1;
            long cc = (long) (Math.random() * maxVal) + 1;
            var ans1 = minTimes(aa, bb, cc);
            var ans2 = minTimes1(aa, bb, cc);
            if(ans1 != ans2) {
                System.out.println("ooops!!");
                System.out.printf("a:%d, b:%d, c:%d. \n", aa, bb, cc);
                System.out.printf("ans1:%d, ans2:%d. \n", ans1, ans2);
                break;
            }
        }
        System.out.println("test end");
    }
}
