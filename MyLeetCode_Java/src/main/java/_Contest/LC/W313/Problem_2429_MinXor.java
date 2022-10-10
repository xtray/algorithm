package _Contest.LC.W313;

public class Problem_2429_MinXor {

    public int minimizeXor(int num1, int num2) {

        int hasOneCnt = Integer.bitCount(num2); //

        int firstOneCnt = Integer.bitCount(num1);

        if (hasOneCnt == firstOneCnt) return num1;

        // 优先消耗num1里的1
        // 1. 拥有1 > num1的里1的个数, 消掉num1里的, 剩下往前
        // 2. 拥有1 < num1里的1个个数, 从高位消1
        // 3.相等返回0
        int ans = 0;
        int cnt = 0;
        String s1 = Integer.toBinaryString(num1);
        char[] str = s1.toCharArray();
        if (hasOneCnt > firstOneCnt) {
            cnt = hasOneCnt - firstOneCnt;
            ans = num1;
            int len = str.length;
            for (int i = len - 1; i >= 0 && cnt != 0; i--) {
                if (str[i] == '0') {
                    ans |= 1 << (len - 1 - i);
                    cnt--;
                }
            }
            if (cnt != 0) {
                for (int i = len; i < len + cnt; i++) {
                    ans |= 1 << i;
                }
            }

        } else { // hasOneCnt < firstOneCnt
            cnt = hasOneCnt;
            for (int i = 0; i < str.length && cnt != 0; i++) {
                if (str[i] == '1') {
                    ans |= 1 << str.length - i - 1;
                    cnt--;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        // int num1 = 3, num2 = 5; // 3
        // int num1 = 1, num2 = 12; // 3
        int num1 = 3, num2 = 7; // 7
        // int num1 = 7, num2 = 3; // 6
        // int num1 = 25, num2 = 72; // 24
        // int num1 = 65, num2 = 84; // 67
        var ans = new Problem_2429_MinXor().minimizeXor(num1, num2);
        System.out.println(ans);
    }
}
