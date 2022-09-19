package _LintCode;

public class Problem_983_CalPoints {


    // 假设指令都有效
    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        int N = ops.length;
        int[] score = new int[N];
        int curPos = -1;
        int ans = 0;
        int cur = 0;
        // NOTE: 因为C会恢复上两次的, 所以得保存所有历史记录(多次C)
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            if (op.equals("C")) {
                cur = -score[curPos];
                --curPos;
            } else if (op.equals("D")) {
                cur = score[curPos] * 2;
                score[++curPos] = cur;
            } else if (op.equals("+")) {
                cur = score[curPos] + score[curPos - 1];
                score[++curPos] = cur;
            } else {
                cur = Integer.parseInt(op);
                score[++curPos] = cur;
            }
            ans += cur;
        }
        return ans;
    }

    public static void main(String[] args) {
        // String[] ops = {"-52","-92","-70","C","+","26","17","+","17","+","-27","-46","73","D","+","D","-25","C","84","C"};
        String[] ops = {"-2", "-2", "-7", "C", "+", "2", "1", "+", "1"}; // -1
        var ans = new Problem_983_CalPoints().calPoints(ops);
        System.out.println(ans);
    }


}
