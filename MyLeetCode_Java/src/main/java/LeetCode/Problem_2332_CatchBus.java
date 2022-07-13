package LeetCode;

import java.util.Arrays;

public class Problem_2332_CatchBus {

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {

        Arrays.sort(buses);
        Arrays.sort(passengers);
        int N = buses.length;
        int M = passengers.length;
        int index = 0;


        for (int i = 0; i < N; i++) {
            int cap = capacity;
            int curTime = buses[i];
            // 乘客到来的时间<= 发车时间, 且有容量, 可以上一个乘客(index++)
            while (index < M && passengers[index] <= curTime && cap > 0) {
                cap--;
                index++;
            }
        }
        int ans = buses[N-1];



        return ans;
    }

    public static void main(String[] args) {
        int[] buses = {10,20};
        int[] passengers ={2,17,18,19};
        int capacity = 2;
        var ans = new Problem_2332_CatchBus().latestTimeCatchTheBus(buses, passengers, capacity);
        System.out.println(ans);
    }

}
