package Company;

public class Problem_000_LifeGame {

    // keep 面试题, 生命游戏数组版, 大厂 42 课
    public void lifeGame(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return;
        }
        process(arr, M);
    }

    // arr 10^6, M 10^10
    private void process(int[] arr, int M) {
        int N = arr.length;
        int[] leftOne = new int[N]; // left[i]: i 位置左侧最近的 1 的位置
        int[] rightOne = new int[N]; // right[i]: i 位置右侧最近的 1 的位置
        int curOnePos = arr[0] == 1 ? 0 : -1;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 1) {
                curOnePos = i;
                leftOne[i] = i;
            } else { // arr[i] 是 0;
                leftOne[i] = curOnePos;
            }
        }
        curOnePos = arr[N - 1] == 1 ? N - 1 : -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                curOnePos = i;
                rightOne[i] = i;
            } else { // arr[i] 是 0;
                rightOne[i] = curOnePos;
            }
        }
        for (int i = 0; i < N; i++) {
            int leftGap = leftOne[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - leftOne[i]);
            int rightGap = rightOne[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - rightOne[i]);
            if (leftGap != rightGap) {
                if (Math.min(leftGap, rightGap) <= M) {
                    arr[i] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1};
        int M = 3;
        Problem_000_LifeGame sl = new Problem_000_LifeGame();
        sl.lifeGame(arr, M);
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
