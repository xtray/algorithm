package LeetCode;

import java.util.ArrayDeque;

public class Problem_2375_SmallestPatternNumber {

    /**
     * 把整个字符串按照(I..D..) 分组
     * 每一段先递增后递减, 如果长度为N, 分配N+1的空间填数字
     * 从最小的开始填,D的那一段填完后逆序
     */
    public String smallestNumber(String s) {
        int size = s.length();
        char[] nums = new char[size + 1];
        char cur = '1';
        nums[0] = cur; // 0 位置先垫一个1

        char[] str = s.toCharArray();
        int i = 0; // pattern数组下标
        int j = -1; // 记录每组D出现位置, 逆序用

        while (i < size) {
            if (str[i] == 'I') {
                if (j != -1) {
                    // 有前面积攒的D..段, 从 j...i翻转
                    swap(nums, j, i);
                }
                j = -1;
                nums[i + 1] = ++cur;
            } else { // 'D'
                if (j == -1) {
                    j = i;
                }
                nums[i + 1] = ++cur;
            }
            i++;
        }
        // 有前面积攒的D..段, 从 j...i翻转
        if (j != -1) {
            swap(nums, j, i);
        }
        return String.valueOf(nums);
    }

    private void swap(char[] nums, int L, int R) {
        while (L < R) {
            char tmp = nums[R];
            nums[R--] = nums[L];
            nums[L++] = tmp;
        }
    }

    // https://leetcode.cn/problems/construct-smallest-number-from-di-string/solution/-by-kayleh-f0z4/
    // 倒着的时候利用栈
    // 贪心：尽可能让较小的数放在前面
    public String smallestNumber1(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int num = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                sb.append(num);
                // 倒出栈里的东西
                while (!stack.isEmpty()) {
                    sb.append(stack.pollLast());
                }
            } else {
                // 如果为下降，则将当前数字压入栈中
                stack.addLast(num);
            }
            num++;
        }
        // stack.addLast(num);
        // 最后一个是D的时候相当于栈顶的数
        // 最后一个是I的时候本来也应该是它
        sb.append(num);
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }


    // https://leetcode.cn/problems/construct-smallest-number-from-di-string/solution/java1hao-miao-di-gui-by-zhy-ipq1m/
    // 递归设计, 不符合规则时, 返回-1, 回溯
    public String smallestNumber2(String pattern) {
        int size = pattern.length();
        int N = size + 1;
        // 可用数字1~N
        char[] nums = new char[N]; // 长度比pattern大1
        char[] p = pattern.toCharArray();
        boolean[] set = new boolean[10];
        // 尝试 0 位置填充每一个i作为开始
        for (int i = 1; i <= N; i++) {
            nums[0] = (char)(i + '0');
            set[i] = true;
            int ret = process(p, 0, nums, set);
            if (ret != -1) {
                return String.valueOf(nums);
            }
            set[i] = false;
        }
        return "";
    }

    // index: 当前使用到的pattern位置, 填充的nums数字在 index + 1 位置
    private int process(char[] p, int index, char[] nums, boolean[] set) {
        if (index == p.length) {
            return 0;
        }
        int N = nums.length;
        // 尝试 1~N 每一个可用的数字, 填充
        for (int i = 1; i <= N; i++) {
            if (set[i]) continue;
            nums[index + 1] = (char)(i + '0');
            set[i] = true;
            if (check(nums[index], nums[index + 1], p[index])) {
                int ret = process(p, index + 1, nums, set);
                if (ret != -1) {
                    return 0;
                }
            }
            set[i] = false;
        }
        return -1;
    }

    private boolean check(int pre, int cur, char ch) {
        if (ch == 'I') {
            return cur > pre;
        } else {
            return pre > cur;
        }
    }

    public static void main(String[] args) {
        String s = "IIIDIDDD";
        var ans = new Problem_2375_SmallestPatternNumber().smallestNumber(s);
        System.out.println(ans);

        var ans1 = new Problem_2375_SmallestPatternNumber().smallestNumber1(s);
        System.out.println(ans1);

        var ans2 = new Problem_2375_SmallestPatternNumber().smallestNumber2(s);
        System.out.println(ans2);
    }

}
