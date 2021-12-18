package LeetCode;

import java.util.*;

public class Problem_869_GetAllPowerOf2 {

    public static void getAllPowerOf2() {

        Set<Integer> hashSet = new HashSet<>();
        Map<Integer, Integer> numMap = new HashMap<>();

        int num = 1;
        int index = 1;
        numMap.put(0, 1);
        while(num <= Integer.MAX_VALUE/2) {
            int digit = 2*num;
            numMap.put(index++, digit);

            List<Integer> res =  getDigit(digit);
            hashSet.addAll(res);

            num = digit;
        }

//        for(int digit : hashSet) {
//            System.out.println(digit);
//        }
        for(Map.Entry<Integer, Integer> en : numMap.entrySet()) {
            System.out.println("key:" + en.getKey() + ", value: " + en.getValue());
        }

    }

    private static List<Integer> getDigit(int n) {
        List<Integer> ans = new ArrayList<>();
        while (n != 0) {
            ans.add(n % 10);
            n /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        getAllPowerOf2();
    }
}
