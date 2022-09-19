package _Contest.LC.W289;

public class Problem_2243_DigitSum {

    public String digitSum(String s, int k) {
        if(s == null || s.length() ==0 || s.length() <= k) {
            return s;
        }

        while (s.length()>k) {
            s = getNextStr(s, k);
        }
        return s;
    }

    // s中的字符, k个分组, 计算和值后, 拼成字符串返回
    private String getNextStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        int N = str.length;
        int sum = 0;
        int cnt = 0;
        for(int i = 0; i< N; i++) {
            int val = str[i] - '0';
            sum += val;
            cnt++;
            if(cnt == k) {
                sb.append(String.valueOf(sum));
                sum = 0;
                cnt = 0;
            }
        }
        if(cnt != 0) {
            sb.append(String.valueOf(sum));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // String s = "11111222223";
        // int k = 3;
        String s = "00000000";
        int k = 2;
        var ans = new Problem_2243_DigitSum().digitSum(s, k);
        System.out.println(ans);
    }
}
