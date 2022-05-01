package LeetCode;

public class Problem_852_MountainArray {

    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return -1;
        }
        int N = arr.length;
        int L = 1;
        int R = N - 2;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return L;
    }

    // IMP: 最小的满足 arr[i] >arr[i+1] 的下标i
    public int peakIndexInMountainArray2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return -1;
        }
        int N = arr.length;
        int L = 1;
        int R = N - 2;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }
}
