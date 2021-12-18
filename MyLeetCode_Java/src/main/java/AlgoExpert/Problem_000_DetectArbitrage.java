package AlgoExpert;

import java.util.ArrayList;
import java.util.Arrays;

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

    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> input = new ArrayList<>();
        input.add(new ArrayList<Double>(Arrays.asList(1.0, 0.8631, 0.5903)));
        input.add(new ArrayList<Double>(Arrays.asList(1.1586, 1.0, 0.6849)));
        input.add(new ArrayList<Double>(Arrays.asList(1.6939, 1.46, 1.0)));
        var res = new Problem_000_DetectArbitrage().detectArbitrage(input);
        System.out.println(res);
    }
}
