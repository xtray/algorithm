package LeetCode.Contest.W286;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_5236_BeautyArray {

    public int minDeletionBao(int[] nums) {
        int count = 0;
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int i = 0;
        while (i < list.size() - 1) {
            if (list.get(i).equals(list.get(i + 1)) ) {
                list.remove(i);
                count++;
                i = 0;
                continue;
            }
            i = i + 2;
        }
        // if(i == list.size() -1) {
        //     count++;
        // }
        if (list.size() % 2 != 0) {
            count++;
        }
        return count;
    }

    public int minDeletion(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        return process(nums, 0);
    }

    // 从左往右的尝试模型
    // 当前尝试到index位置, 返回从index ... N-1 让数组ok的最小删除数
    // isEven: 是不是从偶数index开始判断
    private int process(int[] nums, int index) {
        int N = nums.length;
        if (index == N) {
            return 0;
        }
        if (index == N - 1) {
            // 剩最后一个数要处理
            return 1;
        }
        // 后面至少有两个数
        // 1. 不删除
        int p1 = Integer.MAX_VALUE;
        if (nums[index] != nums[index + 1]) {
            p1 = process(nums, index + 2);
        }
        // 2. 删除index的数
        int p2 = Integer.MAX_VALUE;
        if (nums[index] == nums[index + 1]) {
            p2 = 1 + process(nums, index + 1);
        }
        return Math.min(p1, p2);
    }

    public int minDeletion2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length < 2) {
            return 1;
        }
        int N = nums.length;
        int[] dp = new int[N + 1];
        dp[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            // 1. 不删除
            int p1 = Integer.MAX_VALUE;
            if (nums[i] != nums[i + 1]) {
                p1 = dp[i + 2];
            }
            // 2. 删除index的数
            int p2 = Integer.MAX_VALUE;
            if (nums[i] == nums[i + 1]) {
                p2 = 1 + dp[i + 1];
            }
            dp[i] = Math.min(p1, p2);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        // // int[] nums = {1, 1, 2, 3, 5}; // 1
        // int[] nums = {1, 1, 2, 2, 3, 3}; // 2
        // // int[] nums = {1, 1, 2, 2, 3, 3, 3};//3

        int[] nums = {808,808};
        var bao = new Problem_5236_BeautyArray().minDeletionBao(nums);
        System.out.println(bao);
        // System.out.println();
        // var ans = new Problem_5236_BeautyArray().minDeletion(nums);
        // System.out.println(ans);
        // var dp = new Problem_5236_BeautyArray().minDeletion2(nums);
        // System.out.println(dp);

        // // int maxInt = (int)1e5;
        // int maxInt = 1000;
        // // int maxSize = (int)1e5;
        // int maxSize = 3;
        // int times = 10000;
        // System.out.println("测试开始...");
        // for(int i = 0; i< times; i++) {
        //     int[] arr = getRandomArry(maxInt, maxSize);
        //     int ans1 = new Problem_5236_BeautyArray().minDeletionBao(arr);
        //     int ans2 = new Problem_5236_BeautyArray().minDeletion2(arr);
        //     if(ans1!=ans2) {
        //         System.out.println("Oppps!");
        //         printArray(arr);
        //         System.out.println("an1:" + ans1);
        //         System.out.println("an2:" + ans2);
        //         break;
        //     }
        // }
        // System.out.println("测试结束");
    }

    private static void printArray(int[] arr) {
        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int[] getRandomArry(int maxInt, int maxSize) {
        int size = (int)(Math.random()*maxSize) + 1;
        int[] arr = new int[size];
        for(int i = 0; i< size; i++) {
            arr[i]= (int)(Math.random()*(maxInt + 1));
        }
        return arr;
    }
}
