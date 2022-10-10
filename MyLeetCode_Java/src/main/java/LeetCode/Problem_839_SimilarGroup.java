package LeetCode;

import java.util.*;


// TLE

public class Problem_839_SimilarGroup {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int setSize;

        public UnionFind(int N) {
            setSize = N;
            parent = new int[N];
            size = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                // 小挂大
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                setSize--;
            }
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public int getSetSize() {
            return setSize;
        }

    }

    public int numSimilarGroups(String[] strs) {

        Set<String> set = new HashSet<>(List.of(strs));
        Map<String, Set<String>> map = new HashMap<>();
        for (String s : strs) {
            Set<String> nexts = getNexts(s, set);
            map.put(s, nexts);
        }
        int N = strs.length;

        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            Set<String> nexts = map.get(strs[i]);
            for (int j = i + 1; j < N; j++) {
                if (uf.isSameSet(i, j)) continue;
                if (nexts.contains(strs[j]) || strs[i].equals(strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getSetSize();
    }

    private Set<String> getNexts(String s, Set<String> set) {
        char[] str = s.toCharArray();
        int N = str.length;
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                swap(str, i, j);
                String tmp = String.valueOf(str);
                if (set.contains(tmp)) {
                    ans.add(tmp);
                }
                swap(str, i, j);
            }
        }
        return ans;
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        // String[] ss = {"zkhnmefhyr","ykznfhehmr","mkhnyefrzh","zkhnyefrmh","zkmnhefhyr","ykznhfehmr","zkynhfehmr","mkhnhefrzy","zkhnmefryh","zkmnhfehyr"};
        String[] ss = {"qihcochwmglyiggvsqqfgjjxu", "gcgqxiysqfqugmjgwclhjhovi", "gjhoggxvcqlcsyifmqgqujwhi", "wqoijxciuqlyghcvjhgsqfmgg", "qshcoghwmglygqgviiqfjcjxu", "jgcxqfqhuyimjglgihvcqsgow", "qshcoghwmggylqgviiqfjcjxu", "wcoijxqiuqlyghcvjhgsqgmgf", "qshcoghwmglyiqgvigqfjcjxu", "qgsjggjuiyihlqcxfovchqmwg", "wcoijxjiuqlyghcvqhgsqgmgf", "sijgumvhqwqioclcggxgyhfjq", "lhogcgfqqihjuqsyicxgwmvgj", "ijhoggxvcqlcsygfmqgqujwhi", "qshcojhwmglyiqgvigqfgcjxu", "wcoijxqiuqlyghcvjhgsqfmgg", "qshcojhwmglyiggviqqfgcjxu", "lhogcgqqfihjuqsyicxgwmvgj", "xscjjyfiuglqigmgqwqghcvho", "lhggcgfqqihjuqsyicxgwmvoj", "lhgocgfqqihjuqsyicxgwmvgj", "qihcojhwmglyiggvsqqfgcjxu", "ojjycmqshgglwicfqguxvihgq", "sijvumghqwqioclcggxgyhfjq", "gglhhifwvqgqcoyumcgjjisqx"};
        var ans = new Problem_839_SimilarGroup().numSimilarGroups(ss);
        System.out.println(ans);
    }
}
