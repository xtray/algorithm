package L_INPRG;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class RepairMan {

    public static int repair(int[][] range, int[] positions) {
        Arrays.sort(positions);
        Arrays.sort(range, Comparator.comparingInt(o -> o[0]));
        int count = 0;
        int index = 0;
        int posNum = positions.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int position : positions) {
            while (index < posNum && range[index][0] <= position) {
                queue.add(range[index][1]);
                index++;
            }
            while (!queue.isEmpty()) {
                Integer cur = queue.poll();
                if (cur >= position) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] positions = {2, 4, 6, 9, 7};
        int[][] range = {{1,2}, {2,4}, {3,6}, {7,8}, {1,9}};

        int[] positions1 = {9, 23, 6, 11, 17};
        int[][] range1 = {{23,24}, {16,19}, {6,12}, {2,10}, {2,9}};
        System.out.println(repair(range, positions));
        System.out.println(repair(range1, positions1));
    }
}
