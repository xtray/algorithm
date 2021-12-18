package LeetCode;

public class Problem_299_GetHint {

    public String getHint(String secret, String guess) {
        char[] se = secret.toCharArray();
        char[] gu = guess.toCharArray();
        // 统计 secret 中的字符频次
        int[] count = new int[10];
        for (char ch : se) {
            count[ch - '0']++;
        }
        int A = 0, B = 0;
        // 检查 guess 数组
        for (int i = 0; i < gu.length; i++) {
            int index = gu[i] - '0';
            if (count[index] > 0) { //字符存在
                B++;  // B 目前代表存在的字符数
                count[index]--;
            }
            if (se[i] == gu[i]) {
                A++;
            }

        }
        return String.format("%dA%dB", A, B - A);
    }
}
