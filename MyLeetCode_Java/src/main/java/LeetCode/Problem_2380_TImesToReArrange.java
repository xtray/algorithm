package LeetCode;

public class Problem_2380_TImesToReArrange {

    public int secondsToRemoveOccurrences(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int time = 0;

        int oneCnt = 0;
        for (char ch : str) {
            oneCnt += ch - '0';
        }
        int cnt = getCurCnt(str);
        if (cnt == oneCnt) {
            return 0;
        }
        do {
            time++;
            for (int j = 0; j < N; ) {
                if (j + 1 < N && str[j] == '0' && str[j + 1] == '1') {
                    str[j] = '1';
                    str[j + 1] = '0';
                    j += 2;
                } else if (str[j] == '1') {
                    j++;
                } else {
                    j++;
                }
            }
            // check
        } while (getCurCnt(str) != oneCnt);
        return time;
    }

    private int getCurCnt(char[] str) {
        int N = str.length;
        int i = 0;
        int cnt = 0;
        while (i < N && str[i] == '1') {
            cnt++;
            i++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        String s = "0110101";
        var ans = new Problem_2380_TImesToReArrange().secondsToRemoveOccurrences(s);
        System.out.println(ans);

    }
}
