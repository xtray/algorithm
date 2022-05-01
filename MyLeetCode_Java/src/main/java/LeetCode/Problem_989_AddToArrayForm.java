package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_989_AddToArrayForm {

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        if (num == null || num.length == 0) {
            return ans;
        }
        int N = num.length;
        int carry = 0;
        int i = N - 1;
        while (i >= 0 || k != 0 || carry != 0) {
            int num1 = i >= 0 ? num[i] : 0;
            int num2 = k != 0 ? k % 10 : 0;
            int sum = num1 + num2 + carry;
            if (sum >= 10) {
                ans.add(0, sum % 10);
                carry = 1;
            } else {
                ans.add(0, sum);
                carry = 0;
            }
            i--;
            k /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {2, 1, 5};
        int k = 806;
        var ans = new Problem_989_AddToArrayForm().addToArrayForm(num, k);
        System.out.println(ans);
    }
}
