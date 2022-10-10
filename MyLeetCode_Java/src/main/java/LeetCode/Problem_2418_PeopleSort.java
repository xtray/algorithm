package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2418_PeopleSort {

    public class Pair {
        public String name;
        public int height;
        public Pair(String n, int h) {
            name = n;
            height = h;
        }
    }

    public String[] sortPeople(String[] names, int[] heights) {
        int N = names.length;
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Pair(names[i], heights[i]));
        }
        list.sort((o1, o2) -> o2.height - o1.height);
        String[] ans = new String[N];
        int idx = 0;
        for (Pair p : list) {
            ans[idx++] = p.name;
        }
        return ans;
    }
}
