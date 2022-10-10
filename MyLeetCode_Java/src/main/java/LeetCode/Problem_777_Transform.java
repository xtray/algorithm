package LeetCode;

public class Problem_777_Transform {

    // XL-->LX: L向左移动, s中L的下标>=e中L的下标
    // RX-->XR: R向右移动, s中R的下标<=e中R的下标
    public boolean canTransform(String s, String e) {
        int N = s.length();
        char[] str1 = s.toCharArray();
        char[] str2 = e.toCharArray();
        int i = 0;
        int j = 0;
        while (i < N || j < N) {
            while (i < N && str1[i] == 'X') {
                i++;
            }
            while (j < N && str2[j] == 'X') {
                j++;
            }
            if (i == N || j == N) return i == j;
            if (str1[i] != str2[j]) return false;
            // i,j相等, 遇到同一个L/R
            if (str1[i] == 'L' && i < j) return false;
            if (str1[i] == 'R' && i > j) return false;
            i++;
            j++;
        }
        return true;
    }
}
