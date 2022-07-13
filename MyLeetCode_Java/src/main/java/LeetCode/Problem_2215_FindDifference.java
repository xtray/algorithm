package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2215_FindDifference {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        boolean[] status1 = new boolean[2001];
        boolean[] status2 = new boolean[2001];
        for (int num : nums1) {
            status1[num + 1000] = true;
        }
        for (int num : nums2) {
            status2[num + 1000] = true;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 2001; i++) {
            if (status1[i] && !status2[i]) {
                list1.add(i - 1000);
            }
            if (status2[i] && !status1[i]) {
                list2.add(i - 1000);
            }
        }
        ans.add(list1);
        ans.add(list2);
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums1 = {1,2,3};
        // int[] nums2 = {2,4,6};
        int[] nums1 = {1,2,3,3};
        int[] nums2 = {1,1,2,2};
        var ans = new Problem_2215_FindDifference().findDifference(nums1,nums2);
        System.out.print("List1 Only: ");
        for(int num : ans.get(0)) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.print("List2 Only: ");
        for(int num : ans.get(1)) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
