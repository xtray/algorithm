package _aTemplate;

import java.util.Arrays;
import java.util.HashMap;

// IMP: 怎么做离散化, 非常非常重要!!

public class Discretization {

    // [45,6,89] -> [1,0,2]
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

    public static void main(String[] args) {
        int[] arr = new int[]{45, 6, 89};
        change(arr);

    }
}
