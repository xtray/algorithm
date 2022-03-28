package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Problem_1356_OrderByOneNums {

    // 暴力
    public int[] sortByBitsBao(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
            bit[x] = get(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }

    // 归并排序
    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int N = arr.length;
        int[] bit = new int[10001]; // 预先计算每个数1的个数
        getOneCnts(arr, bit);
        process(arr, 0, N - 1, bit);
        return arr;
    }

    private void getOneCnts(int[] arr, int[] bit) {
        int N = arr.length;
        for (int num : arr) {
            bit[num] = getOneCnt(num);
        }
    }

    // 得到一个数里1的个数
    private static int getOneCnt(int num) {
        int cnt = 0;
        while (num != 0) {
            // if ((num & 1) != 0) {
            //     cnt++;
            // }
            cnt += (num & 1);
            num >>= 1;
        }
        return cnt;
    }

    // 数组 arr L...R 上排有序
    private static void process(int[] arr, int L, int R, int[] bit) {
        if (L >= R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, 0, mid, bit);
        process(arr, mid + 1, R, bit);
        merge(arr, L, mid, R, bit);
    }

    // 具体排序规则在merge里
    private static void merge(int[] arr, int l, int mid, int r, int[] bit) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int idx = 0;
        while (p1 <= mid && p2 <= r) {
            int cnt1 = bit[arr[p1]];
            int cnt2 = bit[arr[p2]];
            help[idx++] = cnt1 == cnt2 ?
                    (arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++]) :
                    (cnt1 < cnt2 ? arr[p1++] : arr[p2++]);
        }
        while (p1 <= mid) {
            help[idx++] = arr[p1++];
        }
        while (p2 <= r) {
            help[idx++] = arr[p2++];
        }
        for (int i = l; i <= r; i++) {
            arr[i] = help[i - l];
        }
    }


    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n >>> 1;
        }
        return count;
    }

    // 插入排序
    public int[] sortByBits2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int N = arr.length;
        int[] bit = new int[10001]; // 预先计算每个数1的个数
        getOneCnts(arr, bit);
        // 0 ~ 0 是有序区
        // 1~N-1 选一个最小的插入有序区
        for(int i = 1; i<N; i++) {
            for(int j = i-1; j>=0; j--) {
                if(bit[arr[j]] == bit[arr[j+1]]) {
                    if(arr[j+1] < arr[j]) {
                        swap(arr, j+1, j);
                    } else {
                        break;
                    }
                } else if(bit[arr[j+1]] < bit[arr[j]]) {
                    swap(arr, j+1, j);
                } else {
                    break;
                }
            }
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
