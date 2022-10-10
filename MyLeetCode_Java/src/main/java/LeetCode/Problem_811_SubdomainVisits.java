package LeetCode;

import java.util.*;

public class Problem_811_SubdomainVisits {


    public static class TrieNode {
        public int sum;
        public boolean end;
        public Map<String, TrieNode> nexts;

        public TrieNode() {
            sum = 0;
            end = false;
            nexts = new HashMap<>();
        }
    }

    private TrieNode root = new TrieNode();

    public List<String> subdomainVisits(String[] cpdomains) {

        for (String cp : cpdomains) {
            String[] ss = cp.split(" ");
            int cnt = Integer.parseInt(ss[0]);
            String[] domains = ss[1].split("\\.");
            add(domains, cnt);
        }

        List<String> ans = query();
        return ans;
    }

    private List<String> query() {
        List<String> ans = new LinkedList<>();
        process(root, new LinkedList<>(), ans);

        return ans;
    }

    private void process(TrieNode root, LinkedList<String> path, List<String> ans) {
        if (root == null) {
            return;
        }
        Map<String, TrieNode> map = root.nexts;
        for (String next : map.keySet()) {
            int cnt = map.get(next).sum;
            // String curDomain = next;
            path.add(0, next);
            genAns(cnt, path, ans);
            process(map.get(next), path, ans);
            path.remove(0);
        }

    }

    private void genAns(int cnt, LinkedList<String> path, List<String> ans) {
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append(" ");
        for (int i = 0; i < path.size() - 1; i++) {
            sb.append(path.get(i)).append(".");
        }
        sb.append(path.get(path.size() - 1));
        ans.add(sb.toString());
    }

    private void add(String[] domains, int cnt) {
        TrieNode cur = root;
        int N = domains.length;
        for (int i = N - 1; i >= 0; i--) {
            String s = domains[i];
            if (!cur.nexts.containsKey(s)) {
                cur.nexts.put(s, new TrieNode());
            }
            cur = cur.nexts.get(s);
            cur.sum += cnt;
        }
        cur.end = true;
    }

    public static void main(String[] args) {
        // String[] cpdomains = {"9001 discuss.leetcode.com"};
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        var ans = new Problem_811_SubdomainVisits().subdomainVisits(cpdomains);
        System.out.println(ans);
    }
}
