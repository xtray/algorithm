package _LintCode;

public class Problem_65_SortedArrayMedian {

    public double findMedianSortedArrays(int[] a, int[] b) {
        int N = a.length;
        int M = b.length;
        int size = N + M;
        boolean even = (size & 1) == 0;
        if (N != 0 && M != 0) {
            if (even) { // 上中位数+下中位数平均值
                int up = getKthNum(a, b, size >> 1);
                int down = getKthNum(a, b, size / 2 + 1);
                return (up + down) / 2.0;
                // return (getKthNum(a, b, size >> 1) + getKthNum(a, b, size / 2 + 1)) / 2.0;
            } else {
                return getKthNum(a, b, size / 2 + 1);
            }
        } else if (N != 0) {
            // 单个数组的中位数
            if ((N & 1) == 0) {
                return (a[N / 2 - 1] + a[N >> 1]) / 2.0;
            } else {
                return a[N >> 1];
            }
        } else if (M != 0) {
            // 单个数组的中位数
            if ((M & 1) == 0) {
                return (b[M / 2 - 1] + b[M >> 1]) / 2.0;
            } else {
                return b[M >> 1];
            }
        }
        return 0;
    }

    // 算法原型2, 查找两个有序数组的第K小, k从1开始
    private int getKthNum(int[] a, int[] b, int kth) {
        // 1.数组重定向
        int[] longs = a.length > b.length ? a : b;
        int[] shorts = longs == a ? b : a;
        int l = longs.length;
        int s = shorts.length;
        // 2.分类讨论, 三个区间
        // a. 区间1: kth <= 短数组长度s
        if (kth <= s) {
            // NOTE: getUpMedian 传递的数组是重定向后的数组, 不是原数组
            return getUpMedian(longs, shorts, 0, kth - 1, 0, kth - 1);
        }
        // b.区间3: kth > 长数组长度 l, 手动验证边界
        if (kth > l) {
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            return getUpMedian(longs, shorts, kth - s, l - 1, kth - l, s - 1);
        }
        // c.区间2: kth > 短数组长度 s && kth<= 长数组长度l
        // 短数组全部有可能, 手动验证长数组
        // NOTE: 长数组结尾位置为kth-1
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(longs, shorts, kth - s, kth - 1, 0, s - 1);
    }

    // 算法原型1: 查找两个数组等长区间上的上中位数, 考虑奇数, 偶数长度
    private int getUpMedian(int[] a, int[] b, int s1, int e1, int s2, int e2) {

        while (s1 < e1) {
            int mid1 = s1 + ((e1 - s1) >> 1);
            int mid2 = s2 + ((e2 - s2) >> 1);
            if (a[mid1] == b[mid2]) {
                return a[mid1];
            }
            // a[mid1] != a[mid2], 一定不相等
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数, 需要手动验证
                if (a[mid1] > b[mid2]) {
                    if (b[mid2] >= a[mid1 - 1]) {
                        return b[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else {
                    if (a[mid1] >= b[mid2 - 1]) {
                        return a[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }
            } else { // 偶数
                if (a[mid1] > b[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }
        }
        // s1 == e1, 两个数组各剩一个数, 单独验证
        return Math.min(a[s1], b[s2]);
    }

    public static void main(String[] args) {
        // int[] a = {1, 2, 3, 4, 5, 6};
        // int[] b = {2, 3, 4, 5}; // 3.50
        int[] a = {5, 6, 9, 10};
        int[] b = {0, 2, 3, 4}; // 4.50

        var ans = new Problem_65_SortedArrayMedian().findMedianSortedArrays(a, b);
        System.out.println(ans);
    }
}
