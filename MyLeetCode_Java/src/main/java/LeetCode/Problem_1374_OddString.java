package LeetCode;

public class Problem_1374_OddString {

    public String generateTheString(int n) {
        if (n <= 0) return "";
        StringBuilder sb = new StringBuilder();
        if ((n & 1) != 0) {
            sb.append("a".repeat(n));
        } else {
            sb.append("a".repeat(n - 1));
            sb.append('b');
        }
        return sb.toString();
    }
}
