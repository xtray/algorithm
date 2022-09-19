package _Misc;

import java.util.Arrays;

public class Comparator {


    public static void main(String[] args) {
        int[] nums = {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        int[][] array = {{Integer.MAX_VALUE, 2}, {Integer.MIN_VALUE, 0}, {Integer.MIN_VALUE, 1}};
        Arrays.sort(array, (o1, o2) -> o1[0]-o2[0]);
        System.out.println(Arrays.deepToString(array));

        Arrays.sort(array, (o1, o2) -> o1[0] != o2[0] ? (o1[0] < o2[0] ? -1 : 1) : 0);
        System.out.println(Arrays.deepToString(array));

        Arrays.sort(array, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        System.out.println(Arrays.deepToString(array));
    }
}
