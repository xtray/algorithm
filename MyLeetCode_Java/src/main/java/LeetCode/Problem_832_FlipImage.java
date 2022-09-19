package LeetCode;

public class Problem_832_FlipImage {

    public int[][] flipAndInvertImage(int[][] image) {
        int N = image.length;
        int M = image[0].length;
        for (int i = 0; i < N; i++) {
            int j = 0;
            int k = M - 1;
            while (j < k) {
                int tmp = image[i][j];
                image[i][j++] = image[i][k] == 1 ? 0 : 1;
                image[i][k--] = tmp == 1 ? 0 : 1;
            }
            if(j == k) image[i][j] = image[i][j] == 1 ? 0 : 1;
        }
        return image;
    }
}
