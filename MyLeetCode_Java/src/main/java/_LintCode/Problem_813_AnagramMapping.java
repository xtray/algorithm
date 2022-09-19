package _LintCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_813_AnagramMapping {

    public int[] anagramMappings(int[] a, int[] b) {
        int N = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(b[i], i);
        }
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = map.get(a[i]);
        }
        return ans;
    }
}
