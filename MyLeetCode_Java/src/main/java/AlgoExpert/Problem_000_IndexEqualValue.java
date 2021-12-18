package AlgoExpert;

public class Problem_000_IndexEqualValue {

    // 各回各家各找各妈
    public int indexEqualsValue(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }

        int N = arr.length;

        // arr[i] --> i
        // index: 0 ---> N-1
        // 如果 arr[i] > N -1 丢弃
        for(int i=0;i<N;i++) {
            if(arr[i] != i && arr[i] <N && arr[i] >=0) {
                swap(arr, i, arr[i]);
            }
        }

        for(int i=0;i<N;i++) {
            if(arr[i]==i) {
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
