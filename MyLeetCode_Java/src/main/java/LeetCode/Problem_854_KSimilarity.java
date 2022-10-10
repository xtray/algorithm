package LeetCode;

import java.util.*;

public class Problem_854_KSimilarity {

    // BFS
    public int kSimilarity(String s1, String s2) {

        // [key, key交换一次能到达的串]
        Map<String, List<String>> graph = new HashMap<>();
        char[] str = s1.toCharArray();
        int step = 0;
        ArrayDeque<String> queue = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        queue.add(s1);
        set.add(s1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.pollFirst();
                if (cur.equals(s2)) {
                    return step;
                }
                for (String next : getNext(cur.toCharArray(), s2.toCharArray())) {
                    if (set.contains(next)) continue;
                    set.add(next);
                    queue.addLast(next);
                }
            }
            step++;
        }
        return step;
    }

    // private List<String> getNext(char[] str) {
    //     List<String> list = new ArrayList<>();
    //     int N = str.length;
    //     for (int i = 0; i < N; i++) {
    //         Set<Character> set = new HashSet<>();
    //         for (int j = i + 1; j < N; j++) {
    //             if (set.contains(str[j])) continue;
    //             set.add(str[j]);
    //             swap(str, i, j);
    //             list.add(String.valueOf(str));
    //             swap(str, i, j);
    //         }
    //     }
    //     return list;
    // }


    private List<String> getNext(char[] str1, char[] str2) {
        List<String> list = new ArrayList<>();
        int N = str1.length;
        int i = 0;
        // 跳过相等的
        while (i < N && str1[i] == str2[i]) {
            i++;
        }
        for (int j = i + 1; j < N; j++) {
            // j --> i
            if (str1[j] == str1[i] || str1[j] != str2[i] || str1[j] == str2[j]) continue;
            swap(str1, i, j);
            list.add(String.valueOf(str1));
            swap(str1, i, j);
        }
        return list;
    }

    public void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
