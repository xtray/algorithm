package _DailyTarget;

public class Problem_11_MostWaterContainer {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int area = 0;
        int maxArea = 0;
        int N = height.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            // 哪边小结算哪边
            if (height[L] >= height[R]) {
                area = (R - L) * height[R];
                R--;
            } else {
                area = (R - L) * height[L];
                L++;
            }
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        var ans = new Problem_11_MostWaterContainer().maxArea(heights);
        System.out.println(ans);
    }
}
