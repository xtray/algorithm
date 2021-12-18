package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_869_getAllNums {

    private static int getRealNum(int[] input) {
        int N = input.length;
        if (input[N-1]==0) {
            return -1;
        }
        int pre = 0;
        for(int i=N-1; i>=0; i--) {
            int cur = input[i];
            pre = pre * 10 + cur;
        }
        return pre;
    }

    // 得到所有位数的全排列
    // input 所有数字
    public static void getAllNums(int[] input, List<Integer> ans, int index) {

        // 每个位数填充 i 位置
        if (index == input.length) {
            int num = getRealNum(input);
            if ( num != -1) {
                ans.add(num);
            }
        } else {
            for (int i = index; i < input.length; i++) {
                swap(input, index, i);
                getAllNums(input, ans, index+1);
                swap(input, index, i);
            }
        }
    }

    public static void swap(int[] list, int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    public static void main(String[] args) {
        int[] list = {3,2,0,7};
        List<Integer> ans = new ArrayList<>();
        getAllNums(list, ans, 0);
        for(int num: ans) {
            System.out.println(num);
        }
    }
}
