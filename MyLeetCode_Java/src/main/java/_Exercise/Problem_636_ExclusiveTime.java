package _Exercise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class Problem_636_ExclusiveTime {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        if (logs == null || logs.size() == 0 || n == 0) {
            return ans;
        }
        // stack用来记录运行的进程id, 栈顶为正在运行的
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int preTime = 0;
        for (String log : logs) {
            String[] ss = log.split(":");
            int id = Integer.parseInt(ss[0]);
            boolean start = "start".equals(ss[1]);
            int curTime = Integer.parseInt(ss[2]);
            if(start) { // 开始事件
                if(!stack.isEmpty()) {   // 栈不空, 结算前一个
                    ans[stack.peekLast()] += curTime - preTime;
                }
                stack.addLast(id);
                preTime = curTime;
            } else { // 结束事件
                // 弹出栈顶,并结算
                stack.pollLast();
                ans[id] += curTime - preTime + 1; // NOTE: 结束事件算Gap 要 + 1
                preTime = curTime + 1; // 更新到下一个时刻
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 2;
        String[] ss = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        var ans = new Problem_636_ExclusiveTime().exclusiveTime(n, Arrays.asList(ss));
        System.out.println(Arrays.toString(ans));
    }
}
