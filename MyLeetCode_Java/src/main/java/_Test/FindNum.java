package _Test;

import java.util.Arrays;

public class FindNum {

    // 返回>=num, 最左位置
    public static int rank(int[] sorted, int num) {
        int l = 0;
        int r = sorted.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,7, 2, 5, 9, 1, 4};
        Arrays.sort(arr);
        int pos = rank(arr, 3);
        System.out.println(pos);
    }

}
