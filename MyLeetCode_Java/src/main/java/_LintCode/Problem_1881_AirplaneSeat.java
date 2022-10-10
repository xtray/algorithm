package _LintCode;

import java.util.HashMap;
import java.util.*;

public class Problem_1881_AirplaneSeat {

    public int solution(int n, String s) {
        char[] str = s.toCharArray();
        Map<Integer, Integer> map = new HashMap<>();
        int curNum = 0;
        int seat = 0;
        for (char ch : str ) {
            if (ch == ' ')  {
                map.put(curNum, map.getOrDefault(curNum, 0) | (1 << seat));
                curNum = 0;
                seat = 0;
            } else if (ch >= '0' && ch <= '9') {
                curNum = curNum*10 + ch - '0';
            } else {
                seat = ch - 'A';
            }
        }
        if (curNum != 0) {
            map.put(curNum, map.getOrDefault(curNum, 0) | (1 << seat));
        }

        int cnt = 0;

        for (int k = 1; k <= n; k++) {
            int status = map.getOrDefault(k, 0);
            int b = ((status >> 1) & 1) == 0 ? 1 : 0;
            int c = ((status >> 2) & 1) == 0 ? 1 : 0;
            int d = ((status >> 3) & 1) == 0 ? 1 : 0;
            int e = ((status >> 4) & 1) == 0 ? 1 : 0;
            int f = ((status >> 5) & 1) == 0 ? 1 : 0;
            int g = ((status >> 6) & 1) == 0 ? 1 : 0;
            int h = ((status >> 7) & 1) == 0 ? 1 : 0;
            int i = ((status >> 8) & 1) == 0 ? 1 : 0;
            if (b + c + d + e == 4 && f + g + h + i == 4) {
                cnt += 2;
            } else if (b + c + d + e == 4 || d + e + f + g == 4 || f + g + h + i == 4) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int n = 2;
        // String s = "1A 2F 1C";
        // int n = 10;
        // String s = "10A ";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i<=50; i++) {
            for (int j = 0; j<26; j++) {
                sb.append(i).append((char)('A' + j)).append(' ');
            }
        }
        var ans = new Problem_1881_AirplaneSeat().solution(50, sb.toString());
        // var ans = new Problem_1881_AirplaneSeat().solution(n, s);
        System.out.println(ans);
    }
}
