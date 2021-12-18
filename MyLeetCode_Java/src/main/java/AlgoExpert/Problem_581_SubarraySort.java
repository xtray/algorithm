package AlgoExpert;

public class Problem_581_SubarraySort {

    public static int[] subarraySort(int[] arr) {

        if (arr == null || arr.length==0) {
            return new int[] {-1, -1};
        }

        int N = arr.length;
        // 需要确定
        // 从左往右遍历, 左边的 leftMax
        // 如果左边的最大值<=当前数, ✅
        // 如果左边的最大值>当前数,说明这个最大值需要往左移动 ❎
        // 找到最右的❎位置, 后面都是✅, 说明这些数都有序, 不用调整

        // 从右往左遍历, 右边的 rightMin
        // 如果当前数<=rightMin, ✅
        // 如果当前数>rightMin,当前数需要往后移动 ❎
        // 找到最左的❎位置, 再往左说明都有序不需要调整
        // 两个位置之间的数字需要调整
        int leftMax = arr[0];
        int right = -1;
        for (int i=1;i<N;i++) {
            if(leftMax > arr[i]) {
                right = i;
            }
            leftMax = Math.max(leftMax, arr[i]);
        }
        int rightMin = arr[N-1];
        int left = -1;
        for (int i=N-2;i>=0;i--) {
            if(arr[i] > rightMin) {
                left = i;
            }
            rightMin = Math.min(rightMin, arr[i]);
        }
        return new int[] {left, right};
    }

    public static int[] subarraySort2(int[] arr) {

        if (arr == null || arr.length==0) {
            return new int[] {-1, -1};
        }

        int N = arr.length;
        // 两个位置之间的数字需要调整
        int leftMax = arr[0];
        int right = -1;
        for (int i=0;i<N;i++) {
            if(leftMax > arr[i]) {
                right = i;
            }
            leftMax = Math.max(leftMax, arr[i]);
        }
        int rightMin = arr[N-1];
        int left = -1;
        for (int i=N-1;i>=0;i--) {
            if(arr[i] > rightMin) {
                left = i;
            }
            rightMin = Math.min(rightMin, arr[i]);
        }
        return new int[] {left, right};
    }
}
