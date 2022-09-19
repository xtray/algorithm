package _Exercise;

public class Problem_4_FindMedian {

    // 时间复杂度O(M + N)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        int[] nums = new int[N + M];
        int i = 0;
        int j = 0;
        int idx = 0;
        while (i < N || j < M) {
            if (i < N && j < M) {
                if (nums1[i] < nums2[j]) {
                    nums[idx++] = nums1[i++];
                } else {
                    nums[idx++] = nums2[j++];
                }
            } else if (i < N) {
                nums[idx++] = nums1[i++];
            } else {
                nums[idx++] = nums2[j++];
            }
        }
        double ans = 0;
        N = nums.length;
        if ((N & 1) != 0) {
            ans = nums[N >> 1];
        } else {
            ans = (nums[N >> 1] + nums[(N >> 1) - 1]) / 2.0D;
        }
        return ans;
    }


    // 时间复杂度 O(log min(M, N))
    // 重定向长短数组, 按短数组, 长数组, 长度之和三个区间考虑
    // 注意: 找第k小原型k从1开始, 当某一数组为空时取中位数是下标从0开始
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        int size = N + M;
        boolean even = (size & 1) == 0;
        if (N != 0 && M != 0) {
            if (even) {
                return (findKthNum(nums1, nums2, size >> 1) + findKthNum(nums1, nums2, size / 2 + 1)) / 2.0;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (N != 0) {
            if (even) {
                // 单一数组中位数, 偶数, 取上中位数 跟 下中位数, 下标从0开始
                return (nums1[N / 2 - 1] + nums1[N / 2]) / 2.0;
            } else {
                return nums1[N >> 1];
            }
        } else if (M != 0) {
            if (even) {
                return (nums2[M / 2 - 1] + nums2[M / 2]) / 2.0;
            } else {
                return nums2[M >> 1];
            }
        } else {
            return 0;
        }
    }

    private double findKthNum(int[] nums1, int[] nums2, int kth) {
        int N = nums1.length;
        int M = nums2.length;
        int[] longs = nums1.length > nums2.length ? nums1 : nums2;
        int[] shorts = longs == nums1 ? nums2 : nums1;
        int l = longs.length;
        int s = shorts.length;
        // 情况1: kth <= 短数组的长度, 调原型
        if (kth <= s) {
            return getUpMedian(longs, shorts, 0, kth - 1, 0, kth - 1);
        }
        // 情况3: kth > 长数组长度
        if (kth > l) {
            // 手动验证两个边界
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            return getUpMedian(longs, shorts, kth - s, l - 1, kth - l, s - 1);
        }
        // 情况2: kth > 短数组的长度 && kth <= 长数组长度
        // 短数组全部有可能, 长数组需要单独验证, 且长数组部分可能
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(longs, shorts, kth - s, kth - 1, 0, s - 1);
    }

    // 两个等长数组(s1~e1 : s2~e2)求上中位数
    public static int getUpMedian(int[] A, int[] B, int s1, int e1, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = s1 + ((e1 - s1) >> 1);
            mid2 = s2 + ((e2 - s2) >> 1);
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            // 两个中点一定不等！
            if (((e1 - s1 + 1) & 1) != 0) { // 长度为奇数
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else {
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }

            } else { // 长度为偶数
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }
        }
        return Math.min(A[s1], B[s2]); // 两个数的第一小, 二者取最小
    }


    public static void main(String[] args) {
        // int[] nums1 = {1, 2};
        // int[] nums2 = {3, 4};

        // int[] nums1 = {1, 3};
        // int[] nums2 = {2}; // 2

        int[] nums1 = {3};
        int[] nums2 = {-2, -1}; // -1.0

        var ans = new Problem_4_FindMedian().findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);

        var ans1 = new Problem_4_FindMedian().findMedianSortedArrays1(nums1, nums2);
        System.out.println(ans1);
    }
}
