package L_INPRG;

import java.util.*;

// IMP: 单调栈,重要基础题
public class Problem_739_DailyTemperatures {

    public static int[]  dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[]{};
        }
        int N = temperatures.length;
        int[] ans = new int[N];
        // 栈底到栈顶大-->小
        // stack里存的是下标
        LinkedList<List<Integer>> stack = new LinkedList<>();
        // Deque<List<Integer>> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peekFirst().get(0)]) {
                // 弹出结算
                List<Integer> curList = stack.pollFirst();
                for (int pos : curList) { // pos也是答案需要填写的位置
                    ans[pos] = i - pos;
                }
            }
            if (!stack.isEmpty() && temperatures[stack.peekFirst().get(0)] == temperatures[i]) {
                stack.peekFirst().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.addFirst(list);
            }
        }
        return ans;
    }


    public static int[] dailyTemperatures2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int N = arr.length;
        int[] ans = new int[N];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] < arr[i]) {
                List<Integer> popIs = stack.pop();
                for (Integer popi : popIs) {
                    ans[popi] = i - popi;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] temp = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] temp = {31, 89, 47, 49, 42, 95, 37, 67, 59, 40, 84, 32, 41, 95, 59, 66, 42, 31, 32, 83, 72, 74, 56, 70, 93, 61, 65, 81, 35, 38, 52, 78, 31, 50};
        var ans = dailyTemperatures(temp);
        printArray(ans);
        var ansT = dailyTemperatures2(temp);
        printArray(ansT);
        System.out.println();

        int times = 0;
        int maxSize = 35;
        int maxVal = 70;
        System.out.println("测试开始");
        for(int i = 1; i<=times; i++) {
            int[] arr = generateRamdomArray(maxSize, maxVal);
            var ans1 = dailyTemperatures(arr);
            var ans2 = dailyTemperatures2(arr);
            if(!checkEqual(ans1, ans2)) {
                System.out.println("opps!!");
                printArray(arr);
            }
        }
        System.out.println("测试结束");
    }

    private static void printArray(int[] arr) {
        for(int num  : arr) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    private static boolean checkEqual(int[] ans1, int[] ans2) {
        int N = ans1.length;
        for(int i = 0; i<N; i++) {
            if(ans1[i] != ans2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] generateRamdomArray(int maxSize, int maxVal) {
        int size = (int)(Math.random()*maxSize) + 1;
        int[] arr = new int[size];
        for(int i=0; i<size; i++) {
            arr[i] = (int)(Math.random()*maxVal) + 30;
        }
        return arr;
    }
}
