package LeetCode;

public class Problem_1417_ReformatStr {

    public String reformat(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int N = s.length();
        char[] num = new char[N];
        char[] str = new char[N];
        int numSize = 0;
        int charSize = 0;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                num[numSize++] = ch;
            } else {
                str[charSize++] = ch;
            }
        }
        if (numSize != charSize && Math.abs(numSize - charSize) != 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = Math.min(numSize, charSize);
        char[] small = numSize == len ? num : str;
        char[] big = small == num ? str : num;
        for (int i = 0; i < len; i++) {
            sb.append(big[i]).append(small[i]);
        }
        if (numSize != charSize) {
            sb.append(big[len]);
        }
        return sb.toString();
    }

    public String reformat1(String s) {
        int numCnt = 0;
        int charCnt = 0;
        char[] str = s.toCharArray();
        for (char ch : str) {
            if (ch >= '0' && ch <= '9') {
                numCnt++;
            } else {
                charCnt++;
            }
        }
        if (Math.abs(numCnt - charCnt) > 1) {
            return "";
        }
        // num1, num2: 两个存储指针
        if (numCnt > charCnt) {
            numCnt = 0;
            charCnt = 1;
        } else {
            numCnt = 1;
            charCnt = 0;
        }
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                str[numCnt] = c;
                numCnt += 2;
            } else {
                str[charCnt] = c;
                charCnt += 2;
            }
        }
        return String.valueOf(str);
    }


    public static void main(String[] args) {
        String s = "a0b1c2";
        var ans = new Problem_1417_ReformatStr().reformat(s);
        System.out.println(ans);
    }
}
