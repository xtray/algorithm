package LeetCode.MianshiJindian;

public class Problem_0810_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return image;
        }
        if (image[sr][sc] == newColor) {
            return image;
        }
        process(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static final int[] dirs = new int[]{0, -1, 0, 1, 0};

    private void process(int[][] image, int i, int j, int org, int newColor) {
        int N = image.length;
        int M = image[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || image[i][j] != org) {
            return;
        }
        image[i][j] = newColor;
        for (int d = 1; d < dirs.length; d++) {
            int dx = i + dirs[d - 1];
            int dy = j + dirs[d];
            process(image, dx, dy, org, newColor);
        }
    }
}
