package AlgoExpert;

import java.util.*;

public class Problem_128_LargestRange {

    // 最长连续序列
    public static int[] largestRange(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        Arrays.sort(arr);
        int range = 1;
        int start = arr[0];
        int maxRange = 1;
        int maxStart = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                continue;
            }
            if (arr[i] == arr[i - 1] + 1) {
                range++;
            } else {
                if (range > maxRange) {
                    maxRange = range;
                    maxStart = start;
                }
                range = 1;
                start = arr[i];
            }
        }
        if (range > maxRange) {
            maxRange = range;
            maxStart = start;
        }
        return new int[]{maxStart, maxStart + maxRange - 1};
    }

    public static int[] largestRange2(int[] arr) {
        Map<Integer, Integer> head = new HashMap<>();
        Map<Integer, Integer> tail = new HashMap<>();
        Set<Integer> set = new HashSet<>(); // 必须要去重, 进过的不能再次处理!
        for (int num : arr) {
            if (set.contains(num)) continue;
            head.put(num, 1);
            tail.put(num, 1);
            set.add(num);
            // ...num-1]  num  [num+1...
            // 建立了num~num这个连续区间的头和尾
            // 查询有没有某个连续区间以num-1结尾
            if (tail.containsKey(num - 1)) {
                int preLen = tail.get(num - 1);
                // (num-1) - preLen + 1 ~ num : preLen + 1
                // num - preLen ~ num : preLen + 1
                int preStart = num - preLen;
                head.put(preStart, preLen + 1);
                tail.put(num, preLen + 1);
                head.remove(num);
                tail.remove(num - 1);
            }
            // 查询有没有某个连续区间以num+1开头的
            if (head.containsKey(num + 1)) {
                int postLen = head.get(num + 1);
                // num ~ num + 1 + postLen -1 : postLen + 1
                // num ~ num + postLen
                int postEnd = num + postLen;
                int preLen = tail.get(num);
                int preStart = num - preLen + 1;
                int totalLen = preLen + postLen;

                head.put(preStart, totalLen);
                tail.put(postEnd, totalLen);

                head.remove(num + 1);
                tail.remove(num);
            }
        }
        int maxLen = Integer.MIN_VALUE;
        int start = -1;
        for (Map.Entry<Integer, Integer> entry : head.entrySet()) {
            if (entry.getValue() > maxLen) {
                start = entry.getKey();
                maxLen = entry.getValue();
            }
        }
        return new int[]{start, start + maxLen - 1};
    }

    public static int[] largestRange3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        int[] ans = new int[2];
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int all = preLen + posLen + 1;
                map.put(num - preLen, all);
                map.put(num + posLen, all);
                if (all > len) {
                    len = all;
                    ans[0] = num - preLen;
                    ans[1] = num + posLen;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6};
        int[] arr = new int[]{0, 9, 19, -1, 18, 17, 2, 10, 3, 12, 5, 16, 4, 11, 8, 7, 6, 15, 12, 12, 2, 1, 6, 13, 14};
        // int[] arr = new int[]{4,2,1,3};
        // int[] arr = new int[]{1, 2};
        var ans = largestRange(arr);
        System.out.println(ans[0] + " " + ans[1]);
        System.out.println("==============");
        var ans2 = largestRange2(arr);
        System.out.println(ans2[0] + " " + ans2[1]);
        System.out.println("==============");
        var ans3 = largestRange3(arr);
        System.out.println(ans3[0] + " " + ans3[1]);
    }
}
