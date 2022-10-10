package LeetCode;

import java.util.*;

public class Problem_927_ThreeEqualParts {


    // https://leetcode.cn/problems/three-equal-parts/solution/by-lcbin-7ir1/

    public int[] threeEqualParts(int[] arr) {
        int N = arr.length;
        int oneCnt = 0;
        for (int num : arr) {
            oneCnt += num;
        }
        if (oneCnt % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (oneCnt == 0) {
            return new int[]{0, N - 1}; // 都是0, 随便返回
        }
        oneCnt /= 3;
        // IMP: 找到三段, i,j,k 各部分第一个1开始的位置
        int i = findFistOne(arr, 1);
        int j = findFistOne(arr, oneCnt + 1);
        int k = findFistOne(arr, oneCnt << 1 | 1);
        while (k < N && arr[i] == arr[j] && arr[j] == arr[k]) {
            i++;
            j++;
            k++;
        }
        return k == N ? new int[]{i - 1, j} : new int[]{-1, -1};
    }

    private int findFistOne(int[] arr, int cnt) {
        int N = arr.length;
        int curCnt = 0;
        for (int i = 0; i < N; i++) {
            curCnt += arr[i];
            if (curCnt == cnt) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 1, 0};
        var ans = new Problem_927_ThreeEqualParts().threeEqualParts(arr);
        System.out.println(Arrays.toString(ans));
    }
}
