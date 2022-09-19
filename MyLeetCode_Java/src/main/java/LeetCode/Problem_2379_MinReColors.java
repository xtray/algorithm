package LeetCode;

public class Problem_2379_MinReColors {

    public int minimumRecolors(String blocks, int k) {
        char[] str = blocks.toCharArray();
        int N = str.length;
        int minOps = N;
        int wcnt = 0;
        int L = 0;
        int R = 0;
        while (R < N) {
            if (str[R] == 'W') {
                wcnt++;
            }
            if (R - L + 1 == k) {
                minOps = Math.min(minOps, wcnt);
                wcnt += str[L] == 'W' ? -1 : 0;
                L++;
            }
            R++;
        }
        return minOps;
    }

    public static void main(String[] args) {
        String blocks = "WBBWWBBWBW";
        int k = 7;
        var ans = new Problem_2379_MinReColors().minimumRecolors(blocks, k);
        System.out.println(ans);
    }


}
