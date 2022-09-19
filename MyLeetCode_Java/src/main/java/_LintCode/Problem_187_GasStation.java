package _LintCode;

public class Problem_187_GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;
        int startIndex = 0; // 选择的出发点
        int curSum = 0; // curSum: 从当前出发点目前富余的可以使用的汽油
        int sum = 0;
        for (int i = 0; i < N; i++) { // 准备登上 i
            int cur = gas[i] - cost[i]; // 纯能值
            sum += cur;
            curSum += cur;
            if (curSum < 0) { // 当前不是良好出发点
                curSum = 0;
                startIndex = i + 1; // 从第一个是正数的点出发(负数必然不是良好出发点)
            }
            // else: leftOil >= 0, 如果新积攒的油>= 之前的欠账, 当前i位置可以走一圈
        }
        return sum >= 0 ? startIndex : -1;
    }
}
