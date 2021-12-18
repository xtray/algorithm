package LeetCode;

public class Problem_2057_SmallestIndexEqualValue {

    // 各回各家各找各妈
    public int smallestEqual(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        // arr[i] --> i mod 10
        // index: 0 ---> N-1
        // 如果 arr[i] > N -1 丢弃
        for(int i=0;i<N;i++) {
            if(arr[i] != i%10 && arr[i] <N && arr[i] >=0) {
                swap(arr, i, arr[i]);
            }
        }

        for(int i=0;i<N;i++) {
            if(arr[i]==i%10) {
                return i;
            }
        }
        return -1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[i] = tmp;
    }
}
