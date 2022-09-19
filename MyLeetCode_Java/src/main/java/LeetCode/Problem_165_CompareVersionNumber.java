package LeetCode;

public class Problem_165_CompareVersionNumber {

    public int compareVersion(String version1, String version2) {
        String[] field1 = version1.split("\\.");
        String[] field2 = version2.split("\\.");
        int N = field1.length;
        int M = field2.length;
        int i = 0;
        int j = 0;
        int cur1 = 0;
        int cur2 = 0;
        while (i < N || j < M) {
            if (i < N && j < M) {
                cur1 = Integer.parseInt(field1[i]);
                cur2 = Integer.parseInt(field2[j]);
            } else if (i < N) {
                cur1 = Integer.parseInt(field1[i]);
                cur2 = 0;

            } else {
                cur1 = 0;
                cur2 = Integer.parseInt(field2[j]);
            }
            if (cur1 != cur2) {
                return cur1 < cur2 ? -1 : 1;
            }
            i++;
            j++;
        }
        return 0;
    }

    public static void main(String[] args) {
        String v1 = "0.1";
        String v2 = "1.1";
        var ans = new Problem_165_CompareVersionNumber().compareVersion(v1, v2);
        System.out.println(ans);
    }
}
