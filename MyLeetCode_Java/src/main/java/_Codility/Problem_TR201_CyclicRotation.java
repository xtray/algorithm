package _Codility;

import java.util.Arrays;

/**
 * 1. CyclicRotation
 * Rotate an array to the right by a given number of steps.
 * https://app.codility.com/demo/results/training9S83UR-EDE/
 */

public class Problem_TR201_CyclicRotation {

    // 左边逆序, 右边逆序, 整体逆序
    public int[] solution(int[] A, int K) {
        if (A == null || A.length == 0 || K == 0 || K % A.length == 0) {
            return A;
        }
        int N = A.length;
        K = K % N;
        reverse(A, 0, N - K - 1);
        reverse(A, N - K, N - 1);
        reverse(A, 0, N - 1);
        return A;
    }

    private void reverse(int[] num, int i, int j) {
        while (i < j) {
            int tmp = num[i];
            num[i++] = num[j];
            num[j--] = tmp;
        }
    }

    // [-1000, 1000]
    //            [31,30][29...20][19...10][9..0]
    //              符号             老值     新值
    // 符号位: 30 -> 0x40000000
    // 老值 num<<10
    // 如果A的值在2^10以内
    public int[] solution2(int[] A, int K) {
        if (A == null || A.length == 0 || K == 0 || K % A.length == 0) {
            return A;
        }
        int N = A.length;
        // K = K % N;
        // []->[+/-][v]<-[]
        for (int i = 0; i < N; i++) {
            if (A[i] < 0) {
                int tmp = -A[i];
                A[i] = 0;
                A[i] |= tmp << 10;
                A[i] |= 0x40000000; // 备份符号位
            } else {
                A[i] |= A[i] << 10;
                A[i] &= 0xffc00; // 抹掉初始值, 清零
            }
        }
        for (int i = 0; i < N; i++) {
            // i--> i+K
            boolean negative = (A[i] & 0x40000000) != 0;
            A[(i + K) % N] |= ((A[i] >>> 10) & 0x3ff);
            if (negative) A[(i + K) % N] |= 0x80000000;
        }
        for (int i = 0; i < N; i++) {
            if (A[i] < 0) {
                A[i] &= 0x3ff;
                A[i] = -A[i];
            } else {
                A[i] &= 0x3ff;
            }
        }
        return A;
    }


    // 如果A的值在2^8-1以内
    public int[] solution1(int[] A, int K) {
        if (A == null || A.length == 0 || K == 0 || K % A.length == 0) {
            return A;
        }
        int N = A.length;
        K = K % N;
        // []->[+/-][v]<-[]
        for (int i = 0; i < N; i++) {
            if (A[i] < 0) {
                int tmp = -A[i];
                A[i] = 0;
                A[i] |= tmp << 8;
                A[i] |= 0x800000;
            } else {
                A[i] |= A[i] << 8;
                A[i] &= 0xff00;
            }
        }
        for (int i = 0; i < N; i++) {
            // i--> i+K
            boolean negative = (A[i] & 0x800000) != 0;
            A[(i + K) % N] |= ((A[i] >>> 8) & 0xff);
            if (negative) A[(i + K) % N] |= 0x80000000;
        }
        for (int i = 0; i < N; i++) {
            if (A[i] < 0) {
                A[i] &= 0xff;
                A[i] = -A[i];
            } else {
                A[i] &= 0xff;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] nums = {-1000, -8, 9, 7, 6};
        int k = 4;
        var ans = new Problem_TR201_CyclicRotation().solution(nums, k);
        System.out.println(Arrays.toString(ans));

        // int num = -3;
        // System.out.println(Integer.toBinaryString(-3));
        // System.out.println(num << 1);
    }
}
