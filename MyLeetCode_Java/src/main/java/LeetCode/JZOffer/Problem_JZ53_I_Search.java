package LeetCode.JZOffer;

public class Problem_JZ53_I_Search {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        // 找>= target最左的位置
        int pos = -1;
        while (L<=R) {
            int mid = L + ((R-L)>>1);
            if(nums[mid] < target) {
                L = mid + 1;
            } else if(nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            }
        }
        if(pos == -1 || nums[pos] != target) {
            return 0;
        }
        int cnt = 0;
        while (pos < N && nums[pos] == target) {
            cnt++;
            pos++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        var ans = new Problem_JZ53_I_Search().search(nums, target);
        System.out.println(ans);
    }
}
