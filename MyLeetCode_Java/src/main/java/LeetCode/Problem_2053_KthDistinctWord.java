package LeetCode;

import java.util.*;

public class Problem_2053_KthDistinctWord {

    public String kthDistinct(String[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return "";
        }
        Map<String, Integer> map = new HashMap<>();

        for (String str : arr) {
           map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int order = 0;
        for (String str :  arr) {
            if(map.get(str) == 1) {
                order++;
                if(order == k) {
                    return str;
                }
            }
        }
        return "";
    }
}
