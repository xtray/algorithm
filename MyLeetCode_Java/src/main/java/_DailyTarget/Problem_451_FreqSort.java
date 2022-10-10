package _DailyTarget;


import java.util.*;

public class Problem_451_FreqSort {

    // TLE
    public String frequencySort(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] cnt = new int[128];
        for (char ch : str) cnt[ch]++;
        // 0..0有序, 1...N-1插入排序
        Arrays.sort(str); // 要求相同字符挨着
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0 && cnt[str[j + 1]] > cnt[str[j]]; j--) {
                swap(str, j, j + 1);
            }
        }
        return String.valueOf(str);
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    // TLE
    public String frequencySort1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] cnt = new int[128];
        for (char ch : str) cnt[ch]++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            if (cnt[i] != 0) {
                sb.append(String.valueOf((char) (i)).repeat(cnt[i]));
            }
        }
        str = sb.toString().toCharArray();
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0 && cnt[str[j + 1]] > cnt[str[j]]; j--) {
                swap(str, j, j + 1);
            }
        }
        return String.valueOf(str);
    }

    // TLE
    public String frequencySort2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] cnt = new int[128];
        for (char ch : str) cnt[ch]++;
        // 0..0有序, 1...N-1插入排序
        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (cnt[str[j + 1]] > cnt[str[j]]) {
                    swap(str, j, j + 1);
                } else if (cnt[str[j + 1]] == cnt[str[j]] && str[j + 1] - str[j] < 0) {
                    swap(str, j, j + 1);
                }

            }
        }
        return String.valueOf(str);
    }

    // 99%
    public String frequencySort3(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] cnt = new int[128];
        for (char ch : str) cnt[ch]++;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < 128; i++) {
            if (cnt[i] != 0) {
                list.add(new int[]{i, cnt[i]});
            }
        }
        list.sort((o1, o2) -> o2[1] - o1[1]);
        StringBuilder sb = new StringBuilder();
        for (int[] l : list) {
            sb.append(String.valueOf((char) (l[0])).repeat(l[1]));
        }
        return sb.toString();
    }

    // 桶排序
    public String frequencySort4(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] cnt = new int[128];
        int maxCnt = 0;
        for (char ch : str) {
            cnt[ch]++;
            maxCnt = Math.max(maxCnt, cnt[ch]);
        }
        // 1~maxCnt
        Map<Integer, List<Character>> bucket = new HashMap<>();
        for (int i = 0; i < 128; i++) {
            if (cnt[i] != 0) {
                bucket.computeIfAbsent(cnt[i], k->new ArrayList<>()).add((char)(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for ( int i = maxCnt; i>=1; i--) {
           for (char ch : bucket.getOrDefault(i, new ArrayList<>())) {
               sb.append(String.valueOf(ch).repeat(i));
           }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s= "tree";
        var ans = new Problem_451_FreqSort().frequencySort4(s);
        System.out.println(ans);
    }


}
