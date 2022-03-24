package LeetCode;

public class Problem_733_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0
            || image[sr][sc] == newColor) {
            return image;
        }
        process(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private static final int[] dirs = new int[]{0, 1, 0, -1, -1, 0, 1, 0};

    private void process(int[][] image, int i, int j, int srcColor, int newColor) {
        int N = image.length;
        int M = image[0].length;
        if (i < 0 || i >= N || j < 0 || j >= M || image[i][j] != srcColor) {
            return;
        }
        image[i][j] = newColor;
        for (int k = 0; k < dirs.length; k += 2) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            process(image, x, y, srcColor, newColor);
        }
    }

    public static void main(String[] args) {
        // int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        // int sr = 1, sc = 1, newColor = 2;
        int[][] image = {{0, 0, 0}, {0, 1, 1}};
        int sr = 1, sc = 1, newColor = 1;
        var ans = new Problem_733_FloodFill().floodFill(image,sr,sc,newColor);
        for(int[] row : image) {
            for(int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
