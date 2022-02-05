package LeetCode;

public class Problem_1629_SlowestKey {

    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int N = keysPressed.length();

        int pre = releaseTimes[0];
        char[] str = keysPressed.toCharArray();
        char res = str[0];
        int max = pre;
        for(int i = 1;i<N;i++) {
            int cur = releaseTimes[i] - pre;
            if(cur > max) {
                res = str[i];
                max = cur;
            } else if(cur == max) {
                res = str[i] - res > 0 ? str[i] : res;
            }
            pre = releaseTimes[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] releaseTimes = new int[]{23,34,43,59,62,80,83,92,97};
        String pressed = "qgkzzihfc"; //q
        var ans = new Problem_1629_SlowestKey().slowestKey(releaseTimes, pressed);
        System.out.println(ans);
    }
}
