package _aTemplate;

public class Enumerate {

    // 从n个1里选出1个1的所有状态位
    public static void subSet(int n) {
        int mask = (1 << n) - 1;
        // for (int i = mask; i != 0; i = (mask & i) - 1) {
        for (int i = mask; i != 0; i -= 1) {
            System.out.println(Integer.toBinaryString(i));
        }
    }

    // 从n个1里选出r个1的所有状态位
    // 从最小的1开始, 不断+最低位的1 | 上一步的i 右移抹掉最后的1
    public static void cnr(int n, int r) {
        int i = (1 << r) - 1; // 最小的 r个1
        // 不断加最右侧的1,
        while (i < (1 << n)) {
            System.out.println(Integer.toBinaryString(i));
            int rightMostOne = i & (-i);
            int addOne = i + rightMostOne;
            // 找到最右侧1的位置, 把它抹掉
            int pos = getRightMostPos(rightMostOne);
            // 抹掉当前i最右侧的1 后跟 i 相与
            i = addOne | ((i & (~addOne)) >> pos);
        }
    }

    private static int getRightMostPos(int num) {
        int cnt = 0;
        while (num != 0) {
            num >>= 1;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        // subSet(n);
        System.out.println("===============");
        cnr(n, 3);
    }
}
