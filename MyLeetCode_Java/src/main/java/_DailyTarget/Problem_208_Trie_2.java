package _DailyTarget;

public class Problem_208_Trie_2 {

    public class TriNode {
        public TriNode[] nexts;
        public boolean end;
        public int pass;

        public TriNode() {
            nexts = new TriNode[26];
        }
    }

    private TriNode root = new TriNode();

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        TriNode cur = root;
        cur.pass++;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.nexts[index] == null) {
                cur.nexts[index] = new TriNode();
            }
            cur = cur.nexts[index];
            cur.pass++;
        }
        cur.end = true;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        TriNode cur = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (cur.nexts[index] == null) {
                return false;
            }
            cur = cur.nexts[index];
        }
        return cur.end;
    }

    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        TriNode cur = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (cur.nexts[index] == null) {
                return false;
            }
            cur = cur.nexts[index];
        }
        return true;
    }

}
