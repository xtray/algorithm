package BigComQues;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 大厂 42
public class Problem_000_RestoreArray {


    public int[] restoreArray(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        Arrays.sort(array);
        int[] res = new int[array.length >> 1];
        Map<Integer, Integer> map = new HashMap<>(); // 等待收集的数字
        int index = array.length >> 1;
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i];
            if (!map.containsKey(num)) {
                // map 等待的数没有这个数, 说明是翻倍的
                map.put(num >> 1, 1);
            } else {
                res[--index] = num;
                map.remove(num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{1, 2, 4, 2, 4, 8};
        // int[] arr = new int[]{1, 2, 6, 7, 3, 14};
        int[] arr = new int[]{1, 2, 3, 5, 6, 10};
        var res = new Problem_000_RestoreArray().restoreArray(arr);
        for (int num : res) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
