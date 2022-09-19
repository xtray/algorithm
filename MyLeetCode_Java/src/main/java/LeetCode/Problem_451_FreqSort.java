package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_451_FreqSort {

    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] cnt = new char[256];
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 48; i < 256; i++) {
            if (cnt[i] != 0) {
                list.add(new int[]{i, cnt[i]});
            }
        }
        list.sort((o1, o2) -> o2[1] - o1[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] item : list) {
            char cur = (char) item[0];
            int times = item[1];
            sb.append(String.valueOf(cur).repeat(times));
        }
        return sb.toString();
    }
}
