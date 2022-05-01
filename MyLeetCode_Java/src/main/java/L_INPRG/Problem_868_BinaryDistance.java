package L_INPRG;

public class Problem_868_BinaryDistance {

    public static int binaryGap(int n) {
        int ans = 0;
        for (int i = 0; i < 32; ) {
            // 跳过所有靠边的0
            while (i < 32 && (n & (1 << i)) == 0) {
                i++;
            }
            if (i == 32) break;
            // i 来到1的位置
            int j = i + 1;
            while (j < 32 && (n & (1 << j)) == 0) {
                j++;
            }
            if (j == 32) break;
            // j来到下一个1的位置
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
        // int num = 22;
        int num = 5;
        var ans = binaryGap(num);
        System.out.println(ans);
    }
}
