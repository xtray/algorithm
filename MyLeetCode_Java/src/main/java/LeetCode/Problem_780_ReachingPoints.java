package LeetCode;

// IMP: 特殊的技巧, 回看!!

public class Problem_780_ReachingPoints {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            // 大模小去替换大
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        return (tx == sx && ty >= sy && (ty - sy) % sx == 0) ||
                (ty == sy && tx >= sx && (tx - sx) % sy == 0);
    }
}
