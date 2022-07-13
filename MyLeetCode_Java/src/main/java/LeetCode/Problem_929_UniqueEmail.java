package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_929_UniqueEmail {

    public int numUniqueEmails(String[] emails) {
        if (emails == null || emails.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String str = getNetAddr(email);
            set.add(str);
        }
        return set.size();
    }

    private String getNetAddr(String s) {
        String[] strs = s.split("@");
        String pre = strs[0];
        String domain = strs[1];
        String validStr = pre.substring(0, pre.contains("+") ? pre.indexOf("+") : pre.length());
        StringBuilder sb = new StringBuilder();
        for (char ch : validStr.toCharArray()) {
            if (ch != '.') {
                sb.append(ch);
            }
        }
        sb.append("@").append(domain); // 不能省掉@
        return sb.toString();
    }

    public static void main(String[] args) {
        // String[] emails = {"a@leetcode.com","b@leetcode.com","c@leetcode.com"};
        String[] emails = {"test.email+alex@leetcode.com", "test.email.leet+alex@code.com"};
        var ans = new Problem_929_UniqueEmail().numUniqueEmails(emails);
        System.out.println(ans);
    }
}
