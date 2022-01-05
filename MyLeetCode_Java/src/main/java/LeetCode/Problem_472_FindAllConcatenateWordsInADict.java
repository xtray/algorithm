package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_472_FindAllConcatenateWordsInADict {

    public static class TrieNode {
        public TrieNode[] nexts;
        public int pass;
        public int end;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = 0;
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length < 3) { // 按题意能分解至少有3个
            return ans;
        }
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        TrieNode head = new TrieNode();
        for (String word : words) {
            char[] str = word.toCharArray();
            if (str.length > 0 && canSplit(str, head, 0)) {
                ans.add(word);
            } else { // 不能分解的作为基础元件加入前缀树元件库
                addToTrie(head, str);
            }

        }
        return ans;
    }

    // 字符串 str 从 i 位置出发,能不能被前缀树分解
    private boolean canSplit(char[] str, TrieNode head, int i) {
        boolean ans = false;
        if (i == str.length) { // 没字符了
            ans = true;
        } else { // 还有字符
            TrieNode cur = head;
            // 尝试str里从 i 出发的每一个前缀看能不能被分解
            // str 从 i...end 做前缀, 看是不是元件
            for (int end = i; end < str.length; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    // 没有路了, 后面前缀都不用试了
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end > 0 && canSplit(str, head, end + 1)) {
                    ans = true;
                    break;
                }
            }
        }
        return ans;
    }

    private void addToTrie(TrieNode head, char[] str) {
        int index = 0;
        TrieNode node = head;
        for (char ch : str) {
            index = ch - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }


    public static int[] dp = new int[1000];

    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length < 3) { // 按题意能分解至少有3个
            return ans;
        }
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length());
        TrieNode head = new TrieNode();
        for (String word : words) {
            char[] str = word.toCharArray();
            // 0~N 都要, fill 函数 toIndex 是不包含的位置
            Arrays.fill(dp, 0, str.length + 1, 0);
            if (str.length > 0 && canSplit2(str, head, 0, dp)) {
                ans.add(word);
            } else { // 不能分解的作为基础元件加入前缀树元件库
                addToTrie(head, str);
            }

        }
        return ans;
    }

    private boolean canSplit2(char[] str, TrieNode head, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i] == 1;
        }
        boolean ans = false;
        if (i == str.length) { // 没字符了
            ans = true;
        } else { // 还有字符
            TrieNode cur = head;
            // 尝试str里从 i 出发的每一个前缀看能不能被分解
            // str 从 i...end 做前缀, 看是不是元件
            for (int end = i; end < str.length; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    // 没有路了, 后面前缀都不用试了
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end > 0 && canSplit(str, head, end + 1)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[i] = ans ? 1 : -1;
        return ans;
    }

}
