package LeetCode;

import java.util.Arrays;

public class Problem_1089_DupZeros {

    public static void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int N = arr.length;
        int j = 0; // 双倍填充0的下标, 当j越界时候, 复制停止
        int i = 0; // 待检查的, 用来记录原数组保留的最后下标位置
        while (j < N) {
            if (arr[i] == 0) {
                j++;
            }
            j++;
            i++;
        }
        // i 落在被截断位置的下一位
        i--;
        j--;
        while (i >= 0) {
            // 若j大于等于数组长度, 说明最后一个是0, 复制两倍会越界, 不用复制这个0
            if (j < N) { // 最后一个0要不要扩展
                arr[j] = arr[i];
            }
            // if (arr[i] == 0 && --j >= 0) {
            if (arr[i] == 0) {
                arr[--j] = 0;
            }
            i--;
            j--;
        }
    }

    // IMP: 位运算,难理解, 多看!!
    public static void duplicateZeros2(int[] arr) {
        int N = arr.length;
        int j = 0; // 快指针,指向下一个等待填充的位置, 每个数该去的位置
        for (int i = 0; i < N; i++) {
            // if (arr[i] == 0) {  // 这样是错的!!, 例子: {0, 5, 0, 4, 5}; // [0,0,5,0,0]
            if ((arr[i] & 15) == 0) { // 真实数值在低4位(1111->15)
                j++; // 多走一步
            } else { // 当前arr[i] != 0
                if (j < N) { // 还有位置
                    // idx: 指向非零的数该去的位置
                    arr[j] |= (arr[i] & 15) << 4; // 存在未来的位置上
                }
                // j>=N的非零数都被挤掉了, 丢弃
            }
            arr[i] >>>= 4; // i位置已经填充过了, 恢复
            j++;
        }
    }


    public static void duplicateZeros3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int N = arr.length;
        int i = 0; // 待检查的, 用来记录原数组保留的最后下标位置
        int j = N - 1; // 待丢弃的位置, 最后有效的截止位置
        while (i < j) {
            if (arr[i] == 0) {
                j--;
            }
            i++;
        }
        // i>= j
        // 出来的时候
        if (i == j && arr[j] == 0) {
            // 如果i == j, 且位置是0, 这个位置的0没有办法双倍, 单独处理
            j = N - 1;
            arr[j--] = 0;
            i--;
        } else {
            i = j;
            j = N - 1;
        }
        while (j >= 0) {
            arr[j] = arr[i];
            if (arr[i] == 0) {
                arr[--j] = 0;
            }
            i--;
            j--;
        }
    }

    private static int[] generateRandomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int cur = (int) (Math.random() * 10);
            if (Math.random() < 0.5) {
                cur = 0;
            }
            ans[i] = cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 5, 0, 4, 5}; // [0,0,5,0,0]
        // // int[] nums = {1, 0, 2, 3, 0, 4, 5, 0};
        // // int[] nums = {8, 4, 5, 0, 0, 0, 0, 7};
        // // int[] nums = {1, 5, 2, 0, 6, 8, 0, 6, 0};
        // // int[] nums = {8, 4, 5, 0, 0, 2, 0, 7};
        duplicateZeros2(nums);
        System.out.println(Arrays.toString(nums));

        // int times = 10000;
        // // int maxLen = 10000;
        // int maxLen = 5;
        // System.out.println("Test Start");
        // for (int i = 0; i < times; i++) {
        //     int len = (int) (Math.random() * maxLen) + 1;
        //     int[] arr = generateRandomArray(len);
        //     int[] arr1 = Arrays.copyOf(arr, arr.length);
        //     int[] arr2 = Arrays.copyOf(arr, arr.length);
        //     duplicateZeros(arr1);
        //     duplicateZeros2(arr2);
        //     if (!Arrays.equals(arr1, arr2)) {
        //         System.out.println("Oppps!!");
        //         System.out.println("Len: " + len);
        //         System.out.println(Arrays.toString(arr));
        //         System.out.println(Arrays.toString(arr1));
        //         System.out.println(Arrays.toString(arr2));
        //         break;
        //     }
        // }
        // System.out.println("Test Fin");

    }
}
