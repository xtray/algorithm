package LeetCode;

import java.util.*;

public class Problem_452_MinArrowShots {

    public int findMinArrowShots(int[][] points) {
        // [start, end]
        // 从左往右依次射箭, 如果在某一个气球结束时还不射箭, 就再也没有机会挽救, 所以:
        // 整理成事件(开始事件, 结束事件), 时间排序,
        // event: [type, 结束时间, 当前时间]
        //        [0 (开始), 结束时间, 开始时间]
        //        [1 (结束), 结束时间, 结束时间]
        // 遇到开始事件, 把结束时间加入集合
        // 遇到结束事件, set里的东西清空, 相当于射了一箭
        //             如果set为空, 说明之前射过, skip
        int N = points.length;
        Integer[][] events = new Integer[N << 1][3];
        int idx = 0;
        for (int[] point : points) { // 一个时间段生成两组事件
            events[idx][0] = 0;
            events[idx][1] = point[1];
            events[idx++][2] = point[0];
            events[idx][0] = 1;
            events[idx][1] = point[1];
            events[idx++][2] = point[1];
        }
        // 时间相等, 开始事件放在前面
        Arrays.sort(events, (o1, o2) -> !Objects.equals(o1[2], o2[2]) ? o1[2].compareTo(o2[2]) : o1[0] - o2[0]);
        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (Integer[] e : events) {
            if (e[0] == 0) { // 遇到开始事件, 把结束时间加入set
                set.add(e[1]);
            } else { // 遇到结束事件, 结算
                if (set.contains(e[1])) {
                    set.clear();
                    cnt++;
                }
                // 如果结束时间不在set里说明之前清理过了,skip
            }
        }
        return cnt;
    }

    public int findMinArrowShots1(int[][] points) {
        // [start, end]
        // 从左往右依次射箭, 如果在某一个气球结束时还不射箭, 就再也没有机会挽救, 所以:
        // 整理成事件(开始事件, 结束事件), 时间排序,
        // event: [type, 结束时间, 当前时间]
        //        [0 (开始), 结束时间, 开始时间]
        //        [1 (结束), 结束时间, 结束时间]
        // 遇到开始事件, 把结束时间加入集合
        // 遇到结束事件, set里的东西清空, 相当于射了一箭
        //             如果set为空, 说明之前射过, skip
        int N = points.length;
        int[][] events = new int[N << 1][3];
        int idx = 0;
        for (int[] point : points) { // 一个时间段生成两组事件
            events[idx][0] = 0;
            events[idx][1] = point[1];
            events[idx++][2] = point[0];
            events[idx][0] = 1;
            events[idx][1] = point[1];
            events[idx++][2] = point[1];
        }
        // NOTE: 时间相等, 开始事件放在前面, 可以用后面同时间的结束事件来收割
        Arrays.sort(events, (o1, o2) -> o1[2] != o2[2] ? (o1[2] < o2[2] ? -1 : 1) : o1[0] - o2[0]);
        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int[] e : events) {
            if (e[0] == 0) { // 遇到开始事件, 把结束时间加入set
                set.add(e[1]);
            } else { // 遇到结束事件, 结算
                if (set.contains(e[1])) {
                    set.clear();
                    cnt++;
                }
                // 如果结束时间不在set里说明之前清理过了,skip
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}}; // 2
        // int[][] points = {{1,2},{2,3},{3,4},{4,5}}; // 2
        int[][] points = {{Integer.MIN_VALUE, Integer.MAX_VALUE}}; // 1, NOTE: 注意这个case, 比较器不能用-
        var ans = new Problem_452_MinArrowShots().findMinArrowShots(points);
        System.out.println(ans);
    }
}
