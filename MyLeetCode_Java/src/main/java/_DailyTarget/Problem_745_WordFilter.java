package _DailyTarget;

import java.util.*;

public class Problem_745_WordFilter {

    class WordFilter {

        public class TrieNode {
            public TrieNode[] nexts;
            public boolean end;
            public List<Integer> idxList; // 下标是从小到大有序的

            public TrieNode() {
                nexts = new TrieNode[26];
                idxList = new ArrayList<>();
            }
        }

        private TrieNode trie1 = new TrieNode(); // 正向
        private TrieNode trie2 = new TrieNode(); // 反向

        public WordFilter(String[] words) {
            int N = words.length;
            for (int i = 0; i < N; i++) {
                addNode(trie1, words[i], i);
                addNodeReverse(trie2, words[i], i);
            }
        }

        private void addNode(TrieNode root, String s, int index) {
            TrieNode cur = root;
            int N = s.length();
            for (int i = 0; i < N; i++) {
                int path = s.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
                cur.idxList.add(index);
            }
            cur.end = true;
        }

        private void addNodeReverse(TrieNode root, String s, int index) {
            TrieNode cur = root;
            int N = s.length();
            for (int i = N - 1; i >= 0; i--) {
                int path = s.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
                cur.idxList.add(index);
            }
            cur.end = true;
        }

        public int f(String pref, String suff) {
            List<Integer> preList = getPreList(trie1, pref);
            List<Integer> backList = getBackList(trie2, suff);
            if (preList == null || backList == null) {
                return -1;
            }
            int ans = -1;
            int N = preList.size();
            int M = backList.size();
            for (int i = N - 1, j = M - 1; i >= 0 && j >= 0; ) {
                if (preList.get(i) > backList.get(j)) {
                    i--;
                } else if (preList.get(i) < backList.get(j)) {
                    j--;
                } else {
                    ans = preList.get(i);
                    break;
                }
            }
            return ans;
        }

        private List<Integer> getBackList(TrieNode root, String s) {
            TrieNode cur = root;
            int N = s.length();
            for (int i = N - 1; i >= 0; i--) {
                int path = s.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return null;
                }
                cur = cur.nexts[path];
            }
            return cur.idxList;
        }

        private List<Integer> getPreList(TrieNode root, String s) {
            TrieNode cur = root;
            int N = s.length();
            for (int i = 0; i < N; i++) {
                int path = s.charAt(i) - 'a';
                if (cur.nexts[path] == null) {
                    return null;
                }
                cur = cur.nexts[path];
            }
            return cur.idxList;
        }
    }

}
