package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_599_FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {

        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for(String str : list1) {
            map.put(str, idx++);
        }
        List<String> list = new ArrayList<>();
        int minIdxSum = Integer.MAX_VALUE;
        idx = 0;
        for(String str : list2) {
            if(map.containsKey(str)) {
                int sum = map.get(str) + idx;
                if(sum < minIdxSum) {
                    minIdxSum = sum;
                    list.clear();
                    list.add(str);
                } else if(sum == minIdxSum) {
                    list.add(str);
                }
            }
            idx++;
        }
        String[] ans = new String[list.size()];
        idx = 0;
        for(String str : list) {
            ans[idx++] = str;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        var ans = new Problem_599_FindRestaurant().findRestaurant(list1, list2);
        for(String str : ans) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

}
