package _DailyTarget;

public class Problem_JZII091_PaintHouses_2 {

    // 只需要维持第一小, 第二小滚动下去即可
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 ||
                costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int N = costs.length;
        int K = costs[0].length; // 每行, 可选择的颜色价位
        int preMin1 = 0; // 第一小
        int preMin2 = 0; // 第二小
        int preColor1 = -1;
        int preColor2 = -1;
        for (int i = 0; i < N; i++) {
            int curMin1 = Integer.MAX_VALUE;
            int curMin2 = Integer.MAX_VALUE;
            int curColor1 = -1;
            int curColor2 = -1;
            // 尝试用每一个颜色的报价去更新当前第一小, 第二小
            for (int j = 0; j < K; j++) {
                if (j != preColor1) { // 当前选择颜色j, 计算花费
                    if (preMin1 + costs[i][j] < curMin1) {
                        curMin2 = curMin1;
                        curMin1 = preMin1 + costs[i][j];
                        curColor2 = curColor1;
                        curColor1 = j;
                    } else if (preMin1 + costs[i][j] < curMin2) {
                        curMin2 = preMin1 + costs[i][j];
                        curColor2 = j;
                    }
                } else if (j != preColor2) { // 第一小撞色, 只能尝试用第二小去更新第一小, 第二小
                    if (preMin2 + costs[i][j] < curMin1) {
                        curMin2 = curMin1;
                        curMin1 = preMin2 + costs[i][j];
                        curColor2 = curColor1;
                        curColor1 = j;
                    } else if (preMin2 + costs[i][j] < curMin2) {
                        curMin2 = preMin2 + costs[i][j];
                        curColor2 = j;
                    }
                }
            }
            preMin1 = curMin1;
            preColor1 = curColor1;
            preMin2 = curMin2;
            preColor2 = curColor2;
        }
        return preMin1;
    }


}
