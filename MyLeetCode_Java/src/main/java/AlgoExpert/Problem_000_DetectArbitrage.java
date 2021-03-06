package AlgoExpert;

import java.util.*;

public class Problem_000_DetectArbitrage {

    // 寻找图中的环路
    // 时间 O(N^3) S: O(N^2)
    public boolean detectArbitrage(ArrayList<ArrayList<Double>> exchangeRates) {
        ArrayList<ArrayList<Double>> logMatrix = convertToLogMatrix(exchangeRates);
        return foundNegativeCycle(logMatrix, 0);
    }

    // Bellman-Ford 算法检测负权环
    private boolean foundNegativeCycle(ArrayList<ArrayList<Double>> graph, int start) {
        double[] distanceFromStart = new double[graph.size()];
        Arrays.fill(distanceFromStart, Double.MAX_VALUE);
        distanceFromStart[start] = 0;

        // 先做 N-1 次松弛
        for (int i = 0; i < graph.size() - 1; i++) {
            // 如果没有更新, 代表没有负权环
            if (!relaxAndUpdate(graph, distanceFromStart)) {
                return false;
            }
        }
        // 做最后一次松弛
        return relaxAndUpdate(graph, distanceFromStart);
    }

    private boolean relaxAndUpdate(ArrayList<ArrayList<Double>> graph, double[] distances) {
        boolean updated = false;

        for (int sourceIdx = 0; sourceIdx < graph.size(); sourceIdx++) {
            for (int destIdx = 0; destIdx < graph.get(0).size(); destIdx++) {
                    double edgeWeight = graph.get(sourceIdx).get(destIdx);
                    double newDistance = distances[sourceIdx] + edgeWeight;
                if (newDistance < distances[destIdx]) {
                    updated = true;
                    distances[destIdx] = newDistance;
                }
            }
        }
        return updated;
    }

    private ArrayList<ArrayList<Double>> convertToLogMatrix(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<ArrayList<Double>> newMatrix = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            newMatrix.add(new ArrayList<>());
            for (int j = 0; j < matrix.get(i).size(); j++) {
                newMatrix.get(i).add(-Math.log10(matrix.get(i).get(j)));
            }
        }
        return newMatrix;
    }

    /////

    // Bellman ford对所有的边进行v-1（V顶点数）次松弛，松弛后如果还可以松弛则说明存在负环，
    //             松弛方法dist[i]+map[i][j]<dist[j];
    // SPFA：如果存在一个点的进队次数超过N次，则说明存在负环。

    // 检测负权环
    // 假设有路径 A->B->A 做货币兑换后 满足 a*b>1 (A->B 汇率 a, B->A汇率 b)就可以套利
    // log(a*b) > log1 --> loga + log b > 0
    // ==>
    // -(loga + logb) < 0 --> -loga + (-logb) < 0
    // 即检测是否有负权值的环就等同于发现套利, 所以可以采用 bellman ford算法
    // 进而可以采用 spfa 算法优化
    // https://www.jianshu.com/p/528db7238b2f
    // https://blog.csdn.net/qq_35644234/article/details/61614581
    public boolean checkArbitrage(double[][] exchangeRates) {
        // 货币编号 0,1,2,...N-1
        double[][] logMatrix = convertToLogMatrix(exchangeRates);
        return foundNegativeCycle(logMatrix, 0);
    }

    // 从 start 出发, 使用 spfa 算法检测负权环, 如果有返回 true
    private boolean foundNegativeCycle(double[][] exchangeRates, int from) {
        int N = exchangeRates.length;
        int M = exchangeRates[0].length;
        // 距离表
        HashMap<Integer, Double> dist = new HashMap<>();
        // 每个点进队列次数表
        HashMap<Integer, Integer> timesMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        visited.add(from);
        dist.put(from, 0.0);
        queue.add(from);
        timesMap.put(from, 1);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            visited.remove(cur);
            // 看当前货币跟其他货币的汇率
            for (int i = 0; i < M; i++) {
                if (dist.get(cur) + exchangeRates[cur][i] < dist.getOrDefault(i, Double.MAX_VALUE)) {
                    dist.put(i, dist.get(cur) + exchangeRates[cur][i]);
                    if (!visited.contains(i)) {
                        visited.add(i);
                        queue.addLast(i);
                        timesMap.put(i, timesMap.getOrDefault(i, 0) + 1);
                        if (timesMap.get(i) > N) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // 把汇率数组转换为-log 值, 方便检测负权环
    private double[][] convertToLogMatrix(double[][] exchangeRates) {
        int N = exchangeRates.length;
        int M = exchangeRates[0].length;
        double[][] logMatrix = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                logMatrix[i][j] = -Math.log10(exchangeRates[i][j]);
            }
        }
        return logMatrix;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> input = new ArrayList<>();
        input.add(new ArrayList<Double>(Arrays.asList(1.0, 0.8631, 0.5903)));
        input.add(new ArrayList<Double>(Arrays.asList(1.1586, 1.0, 0.6849)));
        input.add(new ArrayList<Double>(Arrays.asList(1.6939, 1.46, 1.0)));
        var res = new Problem_000_DetectArbitrage().detectArbitrage(input);
        System.out.println(res);
    }
}
