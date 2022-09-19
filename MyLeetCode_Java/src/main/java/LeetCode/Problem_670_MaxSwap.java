package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_670_MaxSwap {


    // 从右往左, 找到当前最大数字, 并从该位置往左找比当前最大数小的数做交换
    public int maximumSwap(int num) {
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int maxIdx = N - 1;
        int idx1 = -1, idx2 = -1; // 待交换的两个位置
        // 从右往左, 找到低位上的最大数跟高位上比他小的数
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] > str[maxIdx]) {
                maxIdx = i;
            } else if (str[i] < str[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(str, idx1, idx2);
            return Integer.parseInt(new String(str));
        } else {
            return num;
        }
    }

    public void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    // 暴力两两交换找最大的数
    public int maximumSwap2(int num) {
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int maxNum = num;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                swap(str, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(str)));
                swap(str, i, j);
            }
        }
        return maxNum;
    }


    public static void main(String[] args) {
        // int num = 7326;
        // int num = 99;
        int num = 98368;
        // int num = 1993;
        var ans = new Problem_670_MaxSwap().maximumSwap(num);
        System.out.println(ans);
    }
}
