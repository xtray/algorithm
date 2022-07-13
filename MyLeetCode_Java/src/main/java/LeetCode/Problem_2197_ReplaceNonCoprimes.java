package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_2197_ReplaceNonCoprimes {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(nums[0]);
        for(int i = 1; i< nums.length; i++) {
            int num = nums[i];
            while (!stack.isEmpty()) {
                int peek = stack.peekLast();
                int gcd = gcd(num, peek);
                if(gcd > 1) {
                    num = lcm(num, peek);
                    stack.pollLast();
                } else {
                    break;
                }
            }
            stack.addLast(num);
        }
        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add(0, stack.pollLast());
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    // 求最小公倍数
    private static int lcm(int m, int n) {
        return m /gcd(m,n) * n;
    }

    public static void main(String[] args) {
        // int[] nums = {6,4,3,2,7,6,2};
        // int[] nums = {31,97561,97561,97561,97561,97561,97561,97561,97561};
        // int[] nums = {287,41,49,287,899,23,23,20677,5,825};
        int[] nums = {899,23,20677};
        var ans = new Problem_2197_ReplaceNonCoprimes().replaceNonCoprimes(nums);
        for(var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
