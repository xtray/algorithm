package LeetCode.MianshiJindian;

public class Problem_01_09_FlipedString {

    public boolean isFlipedString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        return (s1 + s1).contains(s2);
    }
}
