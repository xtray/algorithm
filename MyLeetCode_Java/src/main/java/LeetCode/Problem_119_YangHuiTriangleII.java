package LeetCode;

import java.util.ArrayList;
import java.util.List;

// NOTE: I,J的特殊控制方法, 从1开始

public class Problem_119_YangHuiTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        pre.add(1); // 0
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 1; j < i; j++) {
                int cur = pre.get(j-1) + pre.get(j);
                next.add(cur);
            }
            next.add(1);
            pre = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int rowIdx = 3;
        var ans = new Problem_119_YangHuiTriangleII().getRow(rowIdx);
        for(int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
