package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Problem_9_Palindrome {

    public boolean isPalindrome(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        if (x < 0) {
            return false;
        }
        List<Integer> list = getNumList(x);
        int L = 0;
        int R = list.size() - 1;
        while (L <= R) {
            if (Objects.equals(list.get(L), list.get(R))) {
                L++;
                R--;
            } else {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getNumList(int x) {
        List<Integer> ans = new ArrayList<>();
        while (x != 0) {
            ans.add(x % 10);
            x /= 10;
        }
        return ans;
    }

    // 翻转一半的做法
    // 如何知道反转数字的位数已经达到原始数字位数的一半?
    // 当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了
    public boolean isPalindrome2(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        // 最后一位是0的数不是回文数
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == reverse || x == reverse / 10;
    }
}
