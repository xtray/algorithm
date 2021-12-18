package LeetCode;

public class Problem_1504_NumSubMat {
    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return 0;
        }
        int nums = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            // 求每一行做地基的矩形个数, 压缩数组技巧
            nums += countFromBottom(height);
        }
        return nums;
    }

    private int countFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int nums = 0;
        int[] stack = new int[height.length];
        int si = -1;
        for (int i = 0; i < height.length; i++) {
            // 单调栈 栈底-->栈顶 由小到大
            // si != -1 : 栈不为空
            // height[stack[si]] : 栈顶
            while (si != -1 && height[stack[si]] >= height[i]) { // 注意: 这里是>=
                int cur = stack[si--]; // 相等的时候, 只弹出栈顶, 不计算, 等后面小的数把这个释放的时候算
                if (height[cur] > height[i]) { // 当前数<栈顶, 弹出结算
                    int left = si == -1 ? -1 : stack[si]; // left 是弹出后的栈顶
                    int n = i - left - 1;
                    int down = Math.max(left == -1 ? 0 : height[left], height[i]);
                    nums += (height[cur] - down) * num(n);
                }
            }
            stack[++si] = i; // 该弹出的弹出后,当前 i 位置入栈
        }
        while (si != -1) { // 栈里的东西单独结算
            int cur = stack[si--];//弹出栈顶
            int left = si == -1 ? -1 : stack[si];
            int n = height.length - left - 1;
            int down = left == -1 ? 0 : height[left];//只有下面小的了, 右边的没有了
            nums += (height[cur] - down) * num(n);
        }
        return nums;
    }

    private int num(int n) {
        return ((n * (n + 1)) >> 1);
    }

    public static void main(String[] args) {
        int[][] mat = new int[][] {{0,1,1,0},{1,0,1,0},{1,1,0,0},{1,0,0,1}};
        var ans = new Problem_1504_NumSubMat().numSubmat(mat);
        System.out.println(ans);
    }
}
