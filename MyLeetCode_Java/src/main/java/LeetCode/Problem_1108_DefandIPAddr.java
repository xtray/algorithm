package LeetCode;

public class Problem_1108_DefandIPAddr {

    public String defangIPaddr(String address) {
        if (address == null || address.length() == 0) {
            return address;
        }
        char[] str = address.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : str) {
            if (ch == '.') {
                sb.append("[.]");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
