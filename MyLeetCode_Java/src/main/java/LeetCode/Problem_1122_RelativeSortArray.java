package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_1122_RelativeSortArray {

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

    // NOTE: 桶排序-计数排序
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {
        int maxVal = 0;
        for (int x : arr1) {
            maxVal = Math.max(maxVal, x);
        }
        int[] cnt = new int[maxVal + 1];
        for (int x : arr1) {
            ++cnt[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        // 按arr2的顺序输出
        for (int x : arr2) {
            for (int i = 0; i < cnt[x]; ++i) {
                ans[index++] = x;
            }
            cnt[x] = 0; // 不要往了置0
        }
        for (int x = 0; x <= maxVal; ++x) {
            for (int i = 0; i < cnt[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }

}
