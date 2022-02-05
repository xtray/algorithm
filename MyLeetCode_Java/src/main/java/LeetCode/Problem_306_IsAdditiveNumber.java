package LeetCode;

public class Problem_306_IsAdditiveNumber {


    public boolean isAdditiveNumber(String num) {
        char[] str = num.toCharArray();
        return process(str);
    }

    // 尝试每一个, 两个, 直到一半剩余长度做为第一个数
    // 尝试每一个, 两个, 直到比第一个数多一个长度的数, 99,99,198
    // 不会出现前导 0，但是，0 可以单独做为一个数来使用
    // 两个大整数相加的和, 做为找寻的第三个数
    // 当前处理到 index 位置, 前面处理两个数累加和是 sum
    // 问从 index...是不是一个有效累加序列
    // 一个有效的 累加序列 必须 至少 包含 3 个数。
    // 只需要检查第一组的数的非法 0 开头,后面组的合法性由加法匹配保证
    private boolean process(char[] str) {
        int N = str.length;
        // 枚举第二个数的开始结束
        for (int i = 1; i < N - 1; i++) {
            // 第一个数 0 ~i-1
            if (str[0] == '0' && i != 1) {
                break;
            } // 不合法
            // 枚举第二个数的结尾
            for (int j = i; j < i + (N - i) / 2; j++) {
                if (str[i] == '0' && j != i) { // 0 开头, 长度不为 1, 不合法
                    break;
                } // 除了长度为 1的, 其他都不合法
                // 验证在第一个, 第二个数确定的情况下, 第三个确定的数是否合法
                if (valid(str, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 验证在第二个数第一部分确定的情况下, 到字符串末尾的可能性
    private boolean valid(char[] str, int i, int j) {
        int N = str.length;
        // 第一个数 0 ~i-1
        int firstStart = 0;
        int firstEnd = i - 1;
        while (j <= N - 1) { // while (j < i + (N-i)/2) {
            String a = getStringNum(str, firstStart, firstEnd);
            String b = getStringNum(str, i, j);
            String c = addTwoNumber(a, b);
            int thirdStart = j + 1;
            int thirdEnd = thirdStart + c.length() - 1;
            // 第三块越界了, 返回 false
            if (thirdEnd >= N || String.valueOf(str, thirdStart, c.length()).compareTo(c) != 0) {
                return false;
            }
            if (thirdEnd == N - 1) { // !!第三块到结尾了, 返回 true
                return true;
            }
            firstStart = i;
            firstEnd = j;
            i = j + 1;
            j = j + c.length();
        }
        return false;
    }

    // 获取字符数字
    private String getStringNum(char[] str, int L, int R) {
        StringBuilder num = new StringBuilder();
        for (int i = L; i <= R; i++) {
            num.append(str[i]);
        }
        return num.toString();
    }

    private String addTwoNumber(String a, String b) {
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int i = str1.length - 1;
        int j = str2.length - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int add1 = i < 0 ? 0 : (str1[i] - '0');
            int add2 = j < 0 ? 0 : (str2[j] - '0');
            int sum = add1 + add2 + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            i--;
            j--;
        }
        sb.reverse();
        return sb.toString();
    }


    // https://leetcode-cn.com/problems/additive-number/solution/tong-ge-lai-shua-ti-la-dfs-jian-zhi-by-t-jxsf/
    public boolean isAdditiveNumber2(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    // 一组三个数是一层
    private boolean dfs(String num, int index, int count, long pre1, long pre2) {
        if (index >= num.length()) {
            return count > 2;
        }
        long current = 0; // 第三个数, 前面两个是 pre1, pre2
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);
            if (num.charAt(index) == '0' && i > index) {
                // 剪枝1：不能做为前导0，但是它自己是可以单独做为0来使用的
                return false;
            }
            current = current * 10 + c - '0';
            if (count >= 2) { // 第三个数的长度, 至少是 2
                long sum = pre1 + pre2;
                if (current > sum) {
                    // 剪枝2：如果当前数比之前两数的和大了，说明不合适
                    return false;
                }
                if (current < sum) {
                    // 剪枝3：如果当前数比之前两数的和小了，说明还不够，可以继续添加新的字符进来
                    continue;
                }
            }
            // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, pre2, current)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String num = "10102003";
        var ans = new Problem_306_IsAdditiveNumber().isAdditiveNumber(num);
        System.out.println(ans);
    }
}
