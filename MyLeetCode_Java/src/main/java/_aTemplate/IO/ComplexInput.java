package _aTemplate.IO;

import java.util.Arrays;
import java.util.Scanner;

public class ComplexInput {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        String[] split = str.split(",");
        int N = split.length;

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }

        System.out.println(Arrays.toString(nums));
    }

}
