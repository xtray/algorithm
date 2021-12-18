package AlgoExpert;

public class Problem_000_WaterfallStreams {

    public double[] waterfallStreams(double[][] arr, int source) {
        if (arr == null || arr.length == 0 || arr[0] == null || arr[0].length == 0) {
            return new double[]{};
        }
        int N = arr.length;
        int M = arr[0].length;
        double[] pre = new double[M];
        pre[source] = -100; // 用负值区分有没有石头跟有没有水
        for (int i = 1; i < N; i++) {
            double[] cur = arr[i];
            for (int j = 0; j < M; j++) { //当前处理 j 位置
                boolean hasWaterAbove = pre[j] < 0; //当前格子上方有没有水
                boolean hasBlock = cur[j] == 1.0; //当前格子有没有石头
                if (!hasWaterAbove) {
                    continue; // 没有水当前位置不用处理
                }
                if (!hasBlock) { //有水, 没有石头, 直接获得上方水量
                    cur[j] += pre[j];
                    continue;
                }
                //上方有水+当前行有石头
                double splitWater = pre[j] / 2;
                //可能性 1: 右行
                int rightIndex = j;
                while (rightIndex + 1 < M) { // 可以右行, 不撞墙
                    rightIndex++;
                    if (pre[rightIndex] == 1.0) { // 碰到石头, 水分损失
                        break;
                    }// 没有石头阻挡, 可以往右
                    if (cur[rightIndex] != 1.0) { // 当前位置可以落水
                        cur[rightIndex] += splitWater;
                        break; // 找到第一个空缺水就落下来
                    }
                } // 撞墙, 水分损失
                //可能性 2: 左行
                int leftIndex = j;
                while (leftIndex - 1 >= 0) { // 可以左行, 不撞墙
                    leftIndex--;
                    if (pre[leftIndex] == 1.0) { // 碰到石头, 水分损失
                        break;
                    }
                    if (cur[leftIndex] != 1.0) { // 没有石头, 可以往左
                        cur[leftIndex] += splitWater;
                        break; // 找到第一个空缺水就落下来
                    }
                }
            }
            pre = cur;
        }
        for (int i = 0; i < pre.length; i++) {
            if (pre[i] < 0) {
                pre[i] = -pre[i];
            }
            ;
        }
        return pre;
    }

    public static void main(String[] args) {

        Problem_000_WaterfallStreams sl = new Problem_000_WaterfallStreams();
        double[][] array =
                new double[][]{
                        {0, 0, 0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 0, 0, 0},
                        {0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {1, 1, 1, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0}
                };
//        new double[][] {
//                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
//                {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
//                {0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0},
//                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
//                {1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0},
//                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0},
//                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
//        };

        var source = 3;
        var res = sl.waterfallStreams(array, source);
        for (var p : res) {
            System.out.print(p + " ");
        }
        System.out.println();

    }
}
