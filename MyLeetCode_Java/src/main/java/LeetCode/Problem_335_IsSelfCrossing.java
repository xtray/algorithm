package LeetCode;

public class Problem_335_IsSelfCrossing {

    // i 位置的线依次判断 是否跟之前的0~i-1 的线是否相撞
    public boolean isSelfCrossing(int[] distance) {
        if (distance == null || distance.length < 4) {
            // 不可能相撞
            return false;
        }
        // >=4条线
        // 大情况 1:
        // 3 位置线跟 0 位置线相撞
        if (distance[2] <= distance[0] && distance[3] >= distance[1]) {
            return true;
        }
        // >=5 条线
        // 大情况 2:
        // 4 位置线跟 0 位置线相撞
        // 4 位置线跟 1 位置线相撞
        if (distance.length > 4 &&
                (distance[3] == distance[1] && distance[0] + distance[4] >= distance[2] ||
                        distance[3] <= distance[1] && distance[4] >= distance[2])) {
            return true;
        }
        // >=6 条线
        // 大情况 3:
        // 当前到了第 i 跟线, 是否跟之前的0~i-1 位置的所有线相撞, 再往后的情况就重复了
        // 5 位置线跟 0 位置线相撞
        // 5 位置线跟 1 位置线相撞
        // 5 位置线跟 2 位置线相撞
        for (int i = 5; i < distance.length; i++) {
            if (distance[i - 1] <= distance[i - 3] && ((distance[i] >= distance[i - 2]) || // 撞 i-3, 2 位置
                    (distance[i - 2] >= distance[i - 4] && distance[i - 5] + distance[i - 1] >= distance[i - 3] &&
                            distance[i - 4] + distance[i] >= distance[i - 2]))) {
                return true;
            }
        }
        return false;

    }

}
