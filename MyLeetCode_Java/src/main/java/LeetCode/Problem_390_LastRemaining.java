package LeetCode;

public class Problem_390_LastRemaining {

    // 暴力
    public int lastRemaining0(int n) {
        if (n == 1) return n;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return process(arr, n, true);
    }

    // 返回最后剩余的数
    private int process(int[] arr, int size, boolean dir) {
        if (size == 1) {
            return arr[0];
        }
        int[] newArr = new int[size >> 1];
        int index = 0;
        if (dir) {
            for (int i = 0; i + 1 < size; i=i+2) {
                newArr[index++] = arr[i+1];
            }
        } else {
            index = newArr.length -1;
            for(int i = size -1; i-1>=0;i=i-2) {
                newArr[index--] = arr[i - 1];
            }
        }
        return process(newArr, size>>1, !dir);
    }


    // 约瑟夫环
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

    // https://leetcode-cn.com/problems/elimination-game/solution/wo-hua-yi-bian-jiu-kan-dong-de-ti-jie-ni-k2uj/
    public int lastRemaining2(int n) {
        int head = 1;
        int step = 1;
        boolean left = true;
        while (n > 1) {
            //从左边开始移除 or（从右边开始移除，数列总数为奇数）
            if (left || n % 2 != 0) {
                head += step;
            }
            step *= 2; //步长 * 2
            left = !left; //取反移除方向
            n /= 2; //总数 / 2
        }
        return head;
    }


    public static void main(String[] args) {
        int n = 9;
        var ans = new Problem_390_LastRemaining().lastRemaining0(n);
        System.out.println(ans);

        System.out.println("=======START========");
        int maxNum = 200;
        for(int num = 1; num <=maxNum;num++) {
            var res = new Problem_390_LastRemaining().lastRemaining0(num);
            System.out.println(num + ": "+res);
        }
    }
}
