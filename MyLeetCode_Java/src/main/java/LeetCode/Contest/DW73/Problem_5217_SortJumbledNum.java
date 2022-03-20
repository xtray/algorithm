package LeetCode.Contest.DW73;

import java.util.ArrayList;
import java.util.List;

public class Problem_5217_SortJumbledNum {

    private int[] mapping;
    private int zeroIdx;

    public int[] sortJumbled(int[] mapping, int[] nums) {
        int zeroIdx = 0;
        for (int i = 0; i < mapping.length; i++) {
            if (mapping[i] == 0) {
                zeroIdx = i;
                break;
            }
        }

        this.mapping = mapping;
        this.zeroIdx = zeroIdx;
        mergeSort(nums);

        return nums;
    }

    private void mergeSort(int[] nums) {
        process(nums, 0, nums.length - 1);
    }

    private void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = myCompare(arr, p1, p2) <= 0 ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    private int myCompare(int[] arr, int idx1, int idx2) {

        var n1 = getNumList(arr[idx1]);
        var n2 = getNumList(arr[idx2]);
        if (n1.size() < n2.size()) {
            return -1;
        } else if (n1.size() > n2.size()) {
            return 1;
        } else {
            // 长度相等
            int N = n1.size();
            for (int i = 0; i < N; ) {
                if ( mapping[n1.get(i)] < mapping[n2.get(i)]) {
                    return -1;
                } else if (mapping[n1.get(i)] > mapping[n2.get(i)]) {
                    return 1;
                } else {
                    i++;
                }
            }
        }
        return 0;
    }

    private List<Integer> getNumList(int num) {

        int tmp = num;
        List<Integer> list = new ArrayList<>();
        if(tmp == 0) {
            list.add(0);
            return list;
        }
        while (tmp != 0) {
            list.add(0, tmp % 10);
            tmp /= 10;
        }
        while (!list.isEmpty() && list.get(0) == zeroIdx) {
            list.remove(0);
        }

        return list;
    }

    public static void main(String[] args) {
        // int[] mapping = {8,9,4,0,2,1,3,5,7,6};
        // int[] nums = {991,338,38};
        // int[] mapping = {0,1,2,3,4,5,6,7,8,9};
        // int[] nums = {789,456,123};
        // int[] mapping = {0,1,2,3,4,5,6,7,8,9};
        // int[] nums = {0, 999999999};
        int[] mapping = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        var ans = new Problem_5217_SortJumbledNum().sortJumbled(mapping, nums);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
