package LeetCode;

public class Problem_836_OverlapRect {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0];
        int x2 = rec1[2];
        int x3 = rec2[0];
        int x4 = rec2[2];
        int y1 = rec1[1];
        int y2 = rec1[3];
        int y3 = rec2[1];
        int y4 = rec2[3];
        // x方向检测
        if (!checkOverlap(x1, x2, x3, x4)) return false;
        if (!checkOverlap(y1, y2, y3, y4)) return false;
        return true;
    }

    private boolean checkOverlap(int a, int b, int c, int d) {
        if (a < b) {
            // a....b
            // c....d, d....c
            if (c < d) {
                return ((c <= a && d > a) || (c > a && c < b));
            } else {
                return ((d <= a && c > a) || (d > a && d < b));
            }
        } else {
            // b.....a
            // c....d, d....c
            if (c < d) {
                return ((c <= b && d > b) || (c > b && c < a));
            } else {
                return ((d <= b && c > b) || (d > b && d < a));
            }
        }
    }

    public static void main(String[] args) {
        int[] rec1 = {5, 15, 8, 18};
        int[] rec2 = {0, 3, 7, 9}; // false
        var ans = new Problem_836_OverlapRect().isRectangleOverlap(rec1, rec2);
        System.out.println(ans);
    }
}
