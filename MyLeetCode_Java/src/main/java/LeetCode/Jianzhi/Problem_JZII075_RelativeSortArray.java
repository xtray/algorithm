package LeetCode.Jianzhi;

import java.util.*;

public class Problem_JZII075_RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int num : arr2) {
            map.put(num, idx++);
        }
        // 对arr1插入排序
        List<Integer> inMap = new ArrayList<>();
        List<Integer> outMap = new ArrayList<>();
        for (int num : arr1) {
            if (map.containsKey(num)) {
                inMap.add(num);
            } else {
                outMap.add(num);
            }
        }
        inMap.sort((o1, o2) -> map.get(o1) - map.get(o2));
        outMap.sort((o1, o2) -> o1 - o2);
        idx = 0;
        for (int num : inMap) {
            arr1[idx++] = num;
        }
        for (int num : outMap) {
            arr1[idx++] = num;
        }
        return arr1;
    }

}
