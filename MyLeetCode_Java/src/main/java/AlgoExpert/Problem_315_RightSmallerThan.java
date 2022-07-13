package AlgoExpert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Problem_315_RightSmallerThan {

    // 从数组最后一个往前做插入排序, 有多少个数越过当前数(swap), 值就是几
    public static List<Integer> rightSmallerThan(List<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            return new ArrayList<>();
        }
        int N = arr.size();
        // 有序区 N-1~N-1
        Integer[] res = new Integer[N];
        List<Integer> ans = new LinkedList<>();
        res[N - 1] = 0;
        for (int i = N - 2; i >= 0; i--) {
            // 有序区 i-1~N-1
            // arr[i]: 待排序的数
            int cnt = 0;
            for (int j = i + 1; j < N && arr.get(j - 1) > arr.get(j); j++) {
                cnt++;
                swap(arr, j, j - 1);
            }
            res[i] = cnt;
        }
        Collections.addAll(ans, res);
        return ans;
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public static class Node {
        public int value;
        public int index;

        public Node(int v, int i) {
            value = v;
            index = i;
        }
    }

    public static List<Integer> rightSmallerThan1(List<Integer> arr) {
        if (arr == null || arr.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            ans.add(0);
        }
        if (arr.size() < 2) {
            return ans;
        }
        Node[] list = new Node[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            list[i] = new Node(arr.get(i), i);
        }
        process(list, 0, list.length - 1, ans);
        return ans;
    }

    private static void process(Node[] arr, int l, int r, List<Integer> ans) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid, ans);
        process(arr, mid + 1, r, ans);
        merge(arr, l, mid, r, ans);
    }

    private static void merge(Node[] arr, int l, int mid, int r, List<Integer> ans) {
        Node[] help = new Node[r - l + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = r;
        while (p1 >= l && p2 >= mid + 1) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - mid);
            }
            help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{8, 5, 11, -1, 3, 4, 2};
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, arr);
        List<Integer> ans = rightSmallerThan(list);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
