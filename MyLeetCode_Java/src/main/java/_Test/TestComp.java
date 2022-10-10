package _Test;

import java.util.Arrays;
import java.util.Comparator;

public class TestComp {

    public static void main(String[] args) {


        Integer[] nums = {4, 3, 2, 1};
        // Arrays.sort(nums, new Comparator<>() {
        //     @Override
        //     public int compare(Integer o1, Integer o2) {
        //         return o2 - o1;
        //     }
        // });
        Arrays.sort(nums, (o1, o2) -> {
            System.out.println("Hello");
            return  o1 - o2;});
        System.out.println(Arrays.toString(nums));


    }
}
