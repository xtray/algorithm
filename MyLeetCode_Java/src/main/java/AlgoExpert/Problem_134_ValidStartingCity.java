package AlgoExpert;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_134_ValidStartingCity {


    // 1.加工出纯能值数组
    // 2.求累加和从正数点出发绕一圈
    public int validStartingCity(int[] distances, int[] fuel, int mpg) {
        if (distances == null || distances.length == 0 || fuel == null || fuel.length == 0) {
            return -1;
        }
        int N = fuel.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = fuel[i] * mpg - distances[i];
            arr[i + N] = arr[i];
        }
        for (int i = 1; i < M; i++) { // 两倍长累加和数组
            arr[i] += arr[i - 1];
        }
        // 利用窗口求解
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) { // 先形成第一个窗口
            // 窗口最小值的更新结构, list 内从小到大
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        int offset = 0;
        for (int i = 0, j = N; j < M; i++, j++) {
            if (arr[w.peekFirst()] - offset >= 0) { // 窗口内最小值掉到 0 以下不是良好出发点
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            // j 的值要进入窗口了
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
            offset = arr[i];
        }
        for (int i = 0; i < N; i++) {
            if (ans[i]) {
                return i;
            }
        }
        return -1;
    }

    public int validStartingCity2(int[] cost, int[] gas, int mpg) {
        int N = gas.length;
        int startIndex = 0; // 选择的出发点
        int remain = 0; // 剩余待走的路
        int leftOil = 0; // 目前拥有的可以使用的汽油
        for (int i = 1; i < N; i++) { // 准备登上 i
            leftOil += gas[i - 1] * mpg - cost[i - 1];
            if (leftOil < remain) {
                remain = leftOil;
                startIndex = i;
            }
        }
        return startIndex;
    }

    public int validStartingCity3(int[] distances, int[] fuel, int mpg) {
        int numberOfCities = distances.length;
        int milesRemaining = 0;
        int indexOfStartingCityCandidate = 0;
        int milesRemainingAtStartingCityCandidate = 0;
        for (int cityIdx = 1; cityIdx < numberOfCities; cityIdx++) {
            int distanceFromPreviousCity = distances[cityIdx - 1];
            int fuelFromPreviousCity = fuel[cityIdx - 1];
            milesRemaining += fuelFromPreviousCity * mpg - distanceFromPreviousCity;
            if (milesRemaining < milesRemainingAtStartingCityCandidate) {
                milesRemainingAtStartingCityCandidate = milesRemaining;
                indexOfStartingCityCandidate = cityIdx;
            }
        }
        return indexOfStartingCityCandidate;
    }

    public int validStartingCity4(int[] distances, int[] fuel, int mpg) {
        int numberOfCities = distances.length;
        for (int startCityIdx = 0; startCityIdx < numberOfCities; startCityIdx++) {
            int milesRemaining = 0;
            for (int currentCityIdx = startCityIdx;
                 currentCityIdx < startCityIdx + numberOfCities;
                 currentCityIdx++) {
                if (milesRemaining < 0) {
                    continue;
                }
                int currentCityIdxRotated = currentCityIdx % numberOfCities;
                int fuelFromCurrentCity = fuel[currentCityIdxRotated];
                int distanceToNextCity = distances[currentCityIdxRotated];
                milesRemaining += fuelFromCurrentCity * mpg - distanceToNextCity;
            }
            if (milesRemaining >= 0) {
                return startCityIdx;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Problem_134_ValidStartingCity sl = new Problem_134_ValidStartingCity();
//        int[] distances = new int[]{5, 25, 15, 10, 15};
//        int[] fuel = new int[]{1, 2, 1, 0, 3};
//        int mpg = 10;
        int[] distances = new int[]{3, 4, 3};
        int[] fuel = new int[]{2, 3, 4};
        int mpg = 1;
        int ans = sl.validStartingCity3(distances, fuel, mpg);
        System.out.println(ans);
    }
}
