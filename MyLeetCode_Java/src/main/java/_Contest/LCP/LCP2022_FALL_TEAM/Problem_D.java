package _Contest.LCP.LCP2022_FALL_TEAM;

import java.util.*;

public class Problem_D {

    /**
     * ["axer","qsuec","rg","cod","lauefxbv","oexyzjr","yefttp","gbnpaccl","lj","kineyykk","esecokfl","qlf","wuxahozg","z","py","ohqpea","nwrtt","ixmvpbsw","jixygsly","cqiudy"]
     * <p>
     * ["axer","qsuec","rg","cod","lauefxbv","oexyzjr","yefttp","gbnpaccl","lj","kineyykk","esecokfl","qlf","wuxahozg","z","py","ohqpea","nwrtt","ixmvpbsw","jixygsly","cqiudy"]
     */

    private static final String motto = "helloleetcode";

    public int Leetcode(String[] words) {

        // Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        // Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());

        int[] cnt0 = new int[26];
        boolean[] need0 = new boolean[26];

        int[] remain = {13};
        for (char ch : motto.toCharArray()) {
            cnt0[ch - 'a']++;
            need0[ch - 'a'] = true;
            // check[ch - 'a'] = true;
        }
        int res = Integer.MAX_VALUE;
        int totalCost = 0;
        List<String> list = new ArrayList<>();
        int N = words.length;
        // 尝试每一个做第一个
        for (int k = 0; k < N; k++) {
            String ttmp = words[0];
            words[0] = words[k];
            words[k] = ttmp;
            int[] cnt = Arrays.copyOf(cnt0, 26);
            boolean[] need = Arrays.copyOf(need0, 26);

            for (String s : words) {
                char[] str = s.toCharArray();
                String net = getOutter(str, cnt, need, remain);
                if (!net.isEmpty()) list.add(net);
            }
            // List<String> tmpList = new ArrayList<>();
            // for (String s : list) {
            //     char[] str = s.toCharArray();
            //     String net = cleanOutter(str, check);
            //     if (!net.isEmpty()) tmpList.add(net);
            // }
            // list = tmpList;


            if (remain[0] == 0) return 0;
            // [left* right, from]
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] * o1[1] - o2[0] * o2[1]);
            int idx = 0;
            for (String s : list) {
                char[] str = s.toCharArray();
                for (int i = 0; i < str.length; i++) {
                    if (need[str[i] - 'a']) {
                        pq.add(new int[]{i, str.length - 1 - i, idx});
                    }
                }
                idx++;
            }

            while (remain[0] != 0 && !pq.isEmpty()) {
                int[] top = pq.poll();
                int from = top[2];
                String topStr = list.get(from);
                char keyChar = topStr.charAt(top[0]);
                if (!need[keyChar - 'a']) continue;
                totalCost += top[0] * top[1];
                PriorityQueue<int[]> tmp = new PriorityQueue<>((o1, o2) -> o1[0] * o1[1] - o2[0] * o2[1]);
                while (!pq.isEmpty()) {
                    int[] p = pq.poll();
                    if (p[2] != from) {
                        tmp.add(p);
                    }
                }

                if (--cnt[topStr.charAt(top[0]) - 'a'] == 0) {
                    remain[0]--;
                    need[topStr.charAt(top[0]) - 'a'] = false;
                }

                String tmpStr = topStr.substring(0, top[0]) + topStr.substring(top[0] + 1);
                list.set(from, tmpStr);
                char[] str = tmpStr.toCharArray();
                for (int i = 0; i < str.length; i++) {
                    if (need[str[i] - 'a']) {
                        tmp.add(new int[]{i, str.length - 1 - i, from});
                    }
                }
                pq = tmp;
            }
            res = Math.min(res, totalCost);
        }

        return res;
    }

    private String cleanOutter(char[] str, boolean[] need) {
        int L = 0;
        int R = str.length - 1;
        StringBuilder sb = new StringBuilder();
        boolean change = false;
        while (L <= R) {
            if (str[L] != 0 && need[str[L] - 'a']) {
                str[L++] = 0;
                change = true;
            }
            if (str[R] != 0 && need[str[R] - 'a']) {
                str[R--] = 0;
                change = true;
            }
            if (!change) {
                break;
            } else {
                change = false;
            }
        }
        boolean exist = false;
        for (char ch : str) {
            if (ch != 0) {
                sb.append(ch);
                if (need[ch - 'a']) {
                    exist = true;
                }
            }
        }
        return exist ? sb.toString() : "";
    }

    private String getOutter(char[] str, int[] cnt, boolean[] need, int[] remain) {
        int L = 0;
        int R = str.length - 1;
        StringBuilder sb = new StringBuilder();
        boolean change = false;
        while (L <= R) {
            if (str[L] != 0 && need[str[L] - 'a']) {
                if (--cnt[str[L] - 'a'] == 0) {
                    need[str[L] - 'a'] = false;
                }
                str[L++] = 0;
                remain[0]--;
                change = true;
            }
            if (str[R] != 0 && need[str[R] - 'a']) {
                if (--cnt[str[R] - 'a'] == 0) {
                    need[str[R] - 'a'] = false;
                }
                str[R--] = 0;
                remain[0]--;
                change = true;
            }
            if (!change) {
                break;
            } else {
                change = false;
            }
        }
        boolean exist = false;
        for (char ch : str) {
            if (ch != 0) {
                sb.append(ch);
                if (need[ch - 'a']) {
                    exist = true;
                }
            }
        }
        return exist ? sb.toString() : "";
    }

    public static void main(String[] args) {
        // String[] words = {"hello", "leetcode"};
        // String[] words = {"hold", "engineer", "cost", "level"};
        // 2
        String[] words = {"axer", "qsuec", "rg", "cod", "lauefxbv", "oexyzjr", "yefttp", "gbnpaccl", "lj", "kineyykk", "esecokfl", "qlf", "wuxahozg", "z", "py", "ohqpea", "nwrtt", "ixmvpbsw", "jixygsly", "cqiudy"};
        var ans = new Problem_D().Leetcode(words);
        System.out.println(ans);
    }
}
