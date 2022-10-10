package LeetCode;

public class Problem_1784_OneSegment {

    public boolean checkOnesSegment(String s) {
        int oneCnt = 0;
        int maxContOne = 0;
        int maxOne = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                oneCnt++;
                maxContOne++;
                maxOne = Math.max(maxOne, maxContOne);
            } else {
                maxContOne = 0;
            }
        }
        return oneCnt == maxOne;
    }

    public boolean checkOnesSegment1(String s) {
        int oneCnt = 0;
        int maxContOne = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                if (oneCnt != maxContOne) return false;
                oneCnt++;
                maxContOne++;
            } else {
                maxContOne = 0;
            }
        }
        return true;
    }

    // 利用条件 不含前导零
    public boolean checkOnesSegment2(String s) {
        for (int i = 1; i<s.length(); i++) {
            if (s.charAt(i) == '0') continue;
            // 到1了
            if (s.charAt(i-1) == '0') return false;
        }
        return true;
    }
}
