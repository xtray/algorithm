package _Contest.CF;

/**
 * 假定有一些青蛙已经分布在一些石头上, 尽量让靠前的青蛙先跳 (队列)
 * 初始分布,假设青蛙无限多, 让L以内所有能到的位置都充满容量
 * <p>
 * 先跳的青蛙尽量跳到更远的位置(有序表)上(消耗更远位置上的容量, 直到减到0(从有序表删除)), 然后尝试下一个稍微近的更远的
 * 直到队列里所有能跳出去并匹配到位置的青蛙都处理完, 剩下的都作废
 * 用一个临时队列收集本次处理中所有能跳出去的青蛙到达的位置及数量
 */


import java.util.*;

//https://codeforces.com/problemset/problem/965/D

public class Problem_965_D {


    int maxNumber(int W, int Len, int[] arr) {
        int ans = Integer.MAX_VALUE;
        int N = arr.length;
        int L = 0;
        int R = 0;
        int sum = 0;
        while (R < N) {
            sum += arr[R];
            if (L == R - Len) {
                sum -= arr[L++];
            }
            if (R + 1 - Len >= 0) {
                ans = Math.min(ans, sum);
            }
            R++;
        }
        return ans;
    }

    //
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int W = sc.nextInt();
    //     int L = sc.nextInt();
    //     int[] arr = new int[W - 1];
    //     for (int i = 0; i < W - 1; i++) {
    //         arr[i] = sc.nextInt();
    //     }
    //     int cnt = new Problem_965_D().maxNumber(W, L, arr);
    //     System.out.println(cnt);
    //     sc.close();
    // }

    public static void main(String[] args) {
        // int W = 10;
        // int L = 5;
        // int[] arr = {0, 0, 1, 0, 2, 0, 0, 1, 0}; // 3
        int W = 10;
        int L = 3;
        int[] arr = {1, 1, 1, 1, 2, 1, 1, 1, 1};//3
        int cnt = new Problem_965_D().maxNumber(W, L, arr);
        System.out.println(cnt);
    }
}
