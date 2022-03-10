package _Test;

import java.util.Stack;

public class QuickSortIII {

    // 快排 3.0
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 在 L..R 快速排序
    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        // L <=R
        int[] equal = netherlandsFlag(arr, L, R);
        process(arr, L, equal[0] - 1);
        process(arr, equal[1] + 1, R);
    }

    // 在L...R 上做荷兰国旗问题
    private static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        // L <= R
        int less = L - 1; // 注意 less 的初始值
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else { // >
                swap(arr, --more, index);
            }
        }
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static class Ops {
        int L;
        int R;
        public Ops(int l, int r) {
            L = l;
            R = r;
        }
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int R = arr.length - 1;
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        // L <=R
        int[] equal = netherlandsFlag(arr, L, R);
        Stack<Ops> stack = new Stack<>();
        stack.add(new Ops(L, equal[0]-1));
        stack.add(new Ops(equal[1]+1, R));
        while (!stack.isEmpty()) {
            Ops ops = stack.pop();
            if(ops.L < ops.R) {
                swap(arr, ops.L + (int) (Math.random() * (ops.R - ops.L + 1)), ops.R);
                equal = netherlandsFlag(arr, ops.L, ops.R);
                stack.add(new Ops(ops.L, equal[0]-1));
                stack.add(new Ops(equal[1]+1, ops.R));
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 7, 1, 8, 5, 6, 4};
        quickSort2(arr);
        for (var num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
