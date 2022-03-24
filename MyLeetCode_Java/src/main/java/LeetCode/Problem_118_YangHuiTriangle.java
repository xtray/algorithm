package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_118_YangHuiTriangle {

    public List<List<Integer>> generate(int numRows) {
        if(numRows < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i< numRows; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).add(1);
        }
        for(int i = 1; i< numRows; i++) {
            for(int j = 1; j<i; j++) {
                ans.get(i).add(ans.get(i-1).get(j-1)+ ans.get(i-1).get(j));
            }
            ans.get(i).add(1);
        }
        return ans;
    }
}
