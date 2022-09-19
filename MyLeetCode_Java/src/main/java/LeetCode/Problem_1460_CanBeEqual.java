package LeetCode;

import java.util.*;

public class Problem_1460_CanBeEqual {


    // 冒泡排序的原理
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i = 0; i < target.length; i++) {
            if (target[i] != arr[i]) {
                return false;
            }
        }
        return true;
    }


    // 当两数组词频相同，且翻转次数不受限制时，我们至少能通过「逐个调整」将一数组变为另一数组
    public boolean canBeEqual1(int[] target, int[] arr) {
        int N = arr.length;
        int[] cnt = new int[1001];
        for (int num : target) {
            cnt[num]++;
        }
        for (int num : arr) {
            if (--cnt[num] < 0) {
                return false;
            }
        }
        return true;
    }

    // 抓住0->1, 1->0的这个特殊点, 做统计, 最后一定是0
    public boolean canBeEqual2(int[] target, int[] arr) {
        int N = arr.length;
        int changes = 0;
        int[] cnt = new int[1001];
        for (int i = 0; i < N; i++) {
            if (++cnt[target[i]] == 1) changes++;
            if (--cnt[arr[i]] == 0) changes--;
        }
        return changes == 0;
    }


    public static void main(String[] args) {
        int[] target = {1, 2, 3, 4};
        int[] arr = {2, 4, 1, 3};
        var ans = new Problem_1460_CanBeEqual().canBeEqual(target, arr);
        System.out.println(ans);
    }
}
