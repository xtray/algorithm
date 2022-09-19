package _aTemplate;

import java.util.*;

// IMP: 怎么做离散化, 非常非常重要!!

public class Discretization {

    // [45,6,89] -> [1,0,2]
    //  排序前没有去重, map去重, 相同的数会被分配到下一个位置, 最后离散化的数不是连续的0~N-1
    public static void change(int[] arr) {
        // [45,6,89] -> [45,6,89]
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy); // [6, 45, 89]
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < copy.length; i++) {
            map.put(copy[i], i);
        }
        // 6 ->0
        // 45 -> 1
        // 89 -> 2
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }
    }


    // positions
    // [2,7] -> 2 , 8
    // [3, 10] -> 3, 12
    // 排序前去重, 最后的结果是连续的0~N-1
    public static HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> pos = new TreeSet<>();
        for (int[] arr : positions) {
            pos.add(arr[0]);
            pos.add(arr[0] + arr[1] - 1);
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : pos) {
            map.put(index, ++count);
        }
        return map;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{45, 6, 6, 89, 89};
        change(arr);
        System.out.println(Arrays.toString(arr));

    }
}
