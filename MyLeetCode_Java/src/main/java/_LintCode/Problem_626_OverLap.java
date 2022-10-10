package _LintCode;

public class Problem_626_OverLap {

    static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        // (l1.x, l1.y) (r1.x, r1.y)
        //一个矩形的右边界小于另一个矩形的左边界
        if ((l2.x > r1.x) || (l1.x > r2.x)) {
            return false;
        }
        //一个矩形的上边界小于另一个矩形的下边界
        if ((r2.y > l1.y) || (r1.y > l2.y)) {
            return false;
        }
        return true;
    }
}
