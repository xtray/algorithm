package LeetCode;

import java.util.Arrays;

public class Problem_1710_MaxUnits {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        if (boxTypes == null || boxTypes.length == 0 ||
                boxTypes[0] == null || boxTypes[0].length == 0 || truckSize == 0) {
            return 0;
        }
        Arrays.sort(boxTypes, (o1, o2) -> o2[1] - o1[1]);
        int boxCnt = 0;
        int sum = 0;
        for (int[] item : boxTypes) {
            if (boxCnt + item[0] < truckSize) {
                boxCnt += item[0];
                sum += item[0] * item[1];
            } else {
                int gap = truckSize - boxCnt;
                sum += gap * item[1];
                return sum;
            }
        }
        // truckSize 太大, 没装满
        return sum;
    }
}
