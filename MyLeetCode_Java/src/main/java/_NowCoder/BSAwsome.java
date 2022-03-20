package _NowCoder;


// https://www.nowcoder.com/questionTerminal/d1c8838fc9e54b89bc10b5b6d2b52157
public class BSAwsome {

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // int N = in.nextInt(); // 数组长度
        // int[] arr = new int[N];
        // for (int i = 0; i < N; i++) {
        //     arr[i] = in.nextInt();
        // }

        int[] arr = new int[]{8,7,3,0,3};

        int ans = bsAwsome(arr);
        System.out.println(ans);

        int times = 1;
        int size = 5;
        int range = 10;
        for(int i =0;i<times;i++) {
            int[] testArr = generateRandomArray(size, range);
            int res = bsAwsome(testArr);
            System.out.println(res);
        }
    }

    private static int[] generateRandomArray(int maxSize, int range) {
        int size = (int)(Math.random()*maxSize + 1);
        int[] arr = new int[size];
        for(int i = 0; i<size;i++) {
            arr[i]= (int)(Math.random()*range) - (int)(Math.random()*range);
        }
        return arr;
    }

    // 返回任意一个局部最小的位置
    private static int bsAwsome(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        int N = arr.length;
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }

        int mid = 0;
        int L = 1;
        int R = N - 2;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;
            } else {
                return mid;
            }
        }
        return L;
    }



}
