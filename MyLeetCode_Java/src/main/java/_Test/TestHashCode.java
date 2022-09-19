package _Test;

import java.util.Arrays;

public class TestHashCode {

    public static void main(String[] args) {
        int[] nums1 = new int[2];
        int[] nums2 = new int[2];
        System.out.println(nums1.hashCode());
        System.out.println(nums2.hashCode());

        System.out.println(nums1.toString());
        System.out.println(nums2.toString());

        System.out.println(Arrays.hashCode(nums1));
        System.out.println(Arrays.hashCode(nums2));

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));

        Integer num1 = 1000;
        Integer num2 = 1000;

        System.out.println(num1.hashCode());
        System.out.println(num1.hashCode());

        System.out.println(num1.toString());
        System.out.println(num1.toString());




    }
}
