package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

public class Problem_000_SameBsts {
    public static boolean sameBsts(List<Integer> one, List<Integer> two) {

        if(one.size() != two.size()) {
            return false;
        }
        if(one.size() == 0) {
            return true;
        }
        if(one.get(0).intValue() != two.get(0)) {
            return false;
        }
        List<Integer> leftOne = getSmaller(one);
        List<Integer> leftTwo = getSmaller(two);
        List<Integer> rightOne = getBiggerOrEqual(one);
        List<Integer> rightTwo = getBiggerOrEqual(two);
        return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo) ;
    }

    private static List<Integer> getBigger(List<Integer> arr) {
        List<Integer> list = new ArrayList<>();
        for(int num : arr) {
            if(num > arr.get(0)) {
                list.add(num);
            }
        }
        return list;
    }

    private static List<Integer> getBiggerOrEqual(List<Integer> arr) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i< arr.size();i++) {
            if(arr.get(i) >= arr.get(0)) {
                list.add(arr.get(i));
            }
        }
        return list;
    }

    // 找到比当前 list 0 位置小的所有数, 加入新 list 并返回
    private static List<Integer> getSmaller(List<Integer> arr) {
        List<Integer> list = new ArrayList<>();
        for(int num : arr) {
            if(num < arr.get(0)) {
                list.add(num);
            }
        }
        return list;
    }


}
