package L_INPRG;

import java.util.Arrays;


// ref: https://leetcode-cn.com/problems/find-the-distance-value-between-two-arrays/solution/by-longluo-19pr/

public class Problem_1385_FindDistanceValue {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            return 0;
        }
        int N = arr1.length;
        Arrays.sort(arr2);
        int ans = 0;
        for(int num : arr1) {
            int low = num - d;
            int high = num + d;
            if(!check(arr2, low, high)) {
                ans++;
            }
        }
        return ans;
    }

    // IMP: 在arr2中找 位于low~high区间的数
    private boolean check(int[] arr2, int low, int high) {
        int N = arr2.length;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if(arr2[mid] >= low && arr2[mid] <= high) {
                return true;
            } else if(arr2[mid] < low){
                L = mid + 1;
            } else if(arr2[mid] > high) {
                R = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {4,5,8};
        int[] arr2 = {10,9,1,8};
        int d = 2;
        var ans = new Problem_1385_FindDistanceValue().findTheDistanceValue(arr1, arr2, d);
        System.out.println(ans);
    }
}
