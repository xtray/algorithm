package LeetCode;

import java.util.ArrayList;
import java.util.List;

// ref: Problem_31_NextPermutation
// IMP: 重要基础题

public class Problem_556_NextGreatNum {
    public int nextGreaterElement(int n) {
        if (n == Integer.MAX_VALUE) {
            return -1;
        }
        List<Integer> list = getDigits(n);
        // if (list.size() <= 1) {
        //     return -1;
        // }
        // 从右往左第一次降序的位置
        int firstLess = -1;
        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) < list.get(i + 1)) {
                firstLess = i;
                break;
            }
        }
        if (firstLess < 0) { // 没有找到降序, 全部升序
            return -1;
        }
        int rightBig = -1;
        // 找最靠右的、同时比nums[firstLess]大的数，位置在哪
        // 这里其实也可以用二分优化，但是这种优化无关紧要了
        for (int i = list.size() - 1; i > firstLess; i--) {
            if (list.get(i) > list.get(firstLess)) {
                rightBig = i;
                break;
            }
        }
        swap(list, firstLess, rightBig);
        reverse(list, firstLess + 1, list.size() - 1);// 由最大变成最小
        return getNum(list);
    }

    private int getNum(List<Integer> list) {
        long res = 0;
        int idx = 0;
        while (idx < list.size()) {
            res = res * 10 + list.get(idx++);
        }
        return res > Integer.MAX_VALUE ? -1 : (int) res;
    }

    public static void reverse(List<Integer> list, int L, int R) {
        while (L < R) {
            swap(list, L++, R--);
        }
    }

    public static void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    // 0 位置是最大的数
    private List<Integer> getDigits(int n) {
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(0, n % 10);
            n /= 10;
        }
        return list;
    }

    public static void main(String[] args) {
        // int num = 2147483644;
        int num = 2;
        var ans = new Problem_556_NextGreatNum().nextGreaterElement(num);
        System.out.println(ans);
    }
}
