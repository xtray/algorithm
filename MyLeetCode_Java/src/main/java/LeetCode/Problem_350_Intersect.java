package LeetCode;

// TODO: 再看看官方的题解

public class Problem_350_Intersect {

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length ==0 || nums2 == null|| nums2.length ==0) {
            return new int[]{};
        }
        int N  = nums1.length;
        int M = nums2.length;
        int[] res = new int[1001];
        int[] count = new int[1001];
        for(int num : nums1) {
            count[num]++;
        }
        int cnt = 0;
        for(int num : nums2) {
            if(count[num] > 0) {
                res[num]++;
                cnt++;
                count[num]--;
            }
        }
        int[] ans = new int[cnt];
        int idx = 0;
        for(int i = 1; i<1001;i++) {
            cnt = res[i];
            while (cnt>0) {
                ans[idx++] = i;
                cnt--;
            }
        }
        return ans;
    }
}
