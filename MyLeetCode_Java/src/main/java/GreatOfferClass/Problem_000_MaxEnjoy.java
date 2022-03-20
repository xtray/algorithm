package GreatOfferClass;

import java.util.Arrays;

public class Problem_000_MaxEnjoy {

    public static int maxEnjoy(int[][] movies) {
        Arrays.sort(movies, (o1, o2) -> o1[0] != o2[0] ? (o1[0] - o2[0]) : (o1[1] - o2[1]));
        return process(movies, 0, 0, 3);
    }

    private static int process(int[][] movies, int index, int time, int rest) {
        if (index == movies.length) {
            return rest == 0 ? 0 : -1;
        }
        // 不要当前电影
        int p1 = process(movies, index + 1, time, rest);
        // 要当前电影
        int next = -1;
        if (movies[index][0] >= time && rest > 0) {
            next = process(movies, index + 1, movies[index][1], rest - 1);
        }
        int p2 = next != -1 ? (movies[index][1] - movies[index][0] + next) : -1;
        return Math.max(p1, p2);
    }

    /**
     * 记忆化搜索
     * index: 2000场电影
     * time: 一天1440分钟
     * rest: 3场电影
     * Integer[][][] dp : 默认未赋值时是null
     */
    public static int maxEnjoy2(int[][] movies) {
        Arrays.sort(movies, (o1, o2) -> o1[0] != o2[0] ? (o1[0] - o2[0]) : (o1[1] - o2[1]));
        Integer[][][] dp = new Integer[2001][1441][4];
        return process2(movies, 0, 0, 3, dp);
    }

    private static int process2(int[][] movies, int index, int time, int rest, Integer[][][] dp) {
        if (dp[index][time][rest] != null) {
            return dp[index][time][rest];
        }

        int ans = -1;
        if (index == movies.length) {
            ans = rest == 0 ? 0 : -1;
            dp[index][time][rest] = ans;
            return ans;
        }
        // 不要当前电影
        int p1 = process2(movies, index + 1, time, rest, dp);
        // 要当前电影
        int next = -1;
        if (movies[index][0] >= time && rest > 0) {
            next = process2(movies, index + 1, movies[index][1], rest - 1, dp);
        }
        int p2 = next != -1 ? (movies[index][1] - movies[index][0] + next) : -1;
        ans = Math.max(p1, p2);
        dp[index][time][rest] = ans;
        return ans;
    }


    public static void main(String[] args) {
        // int maxMovieCnt = 2000; // 太大, 时间特别长
        int maxMovieCnt = 200;
        int[][] movies = generateMoves(maxMovieCnt);
        var ans1 = maxEnjoy(movies);
        var ans2 = maxEnjoy2(movies);
        if (ans1 != ans2) {
            System.out.println("ooops!");
        }

        System.out.println("测试开始");
        int times = 10;
        for(int i = 0; i< times; i++) {
            int[][] testMovies = generateMoves(maxMovieCnt);
            var res1 = maxEnjoy(testMovies);
            var res2 = maxEnjoy2(testMovies);
            if (res1 != res2) {
                System.out.println("ooops!");
                break;
            }
        }
        System.out.println("测试结束");

    }

    // 生成2000场随机的电影
    // 时间0~1440
    private static int[][] generateMoves(int maxMovieNumber) {
        int[][] movies = new int[maxMovieNumber][2];
        for (int i = 0; i < maxMovieNumber; i++) {
            movies[i][0] = (int) (Math.random() * 1441);
            movies[i][1] = movies[i][0] + (int) (Math.random() * (1440 - movies[i][0]));
        }
        return movies;
    }
}
