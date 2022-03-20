package LeetCode.Contest.W282;

public class Problem_6008_PrefixCount {


    public static class TrieNode {
        public int pass;
        public boolean end;
        public TrieNode[] nexts;

        public TrieNode() {
            nexts = new TrieNode[26];
            pass = 0;
            end = false;
        }
    }


    public int prefixCount(String[] words, String pref) {
        TrieNode head = new TrieNode();
        createTrie(head, words);
        return getCount(head, pref);
    }

    private int getCount(TrieNode head, String pref) {
        if (pref == null || pref.length() == 0) {
            return 0;
        }
        char[] str = pref.toCharArray();
        for (char c : str) {
            int index = c - 'a';
            if (head.nexts[index] == null) {
                return 0;
            }
            head = head.nexts[index];
        }
        return head.pass;
    }

    // 根据 words 生成 trie 树
    private void createTrie(TrieNode head, String[] words) {
        for (String word : words) {
            fillWord(head, word);
        }
    }

    private void fillWord(TrieNode head, String word) {
        head.pass++;
        char[] chs = word.toCharArray();
        int index = 0;
        TrieNode node = head;
        for (int i = 0; i < chs.length; i++) {
            index = chs[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end = true;
    }

    public static void main(String[] args) {
        String[] words = {"faeu", "fauq", "fanc", "fap", "favkigbbsk", "faex", "fag", "faltrf", "fabeckg", "faem", "fahh", "nyyqmdv", "faaei", "fah", "fayr", "fazon", "fairpv", "fanz", "fap", "fanfxo", "fadzmrtjv", "famf", "faom"};
        String prefix = "fa";
        var ans = new Problem_6008_PrefixCount().prefixCount(words, prefix);
        System.out.println(ans);

    }
}
