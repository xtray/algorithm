package Contest.LC.W283;

import java.util.ArrayList;
import java.util.List;

public class Problem_2194_ExcelCells {

    public List<String> cellsInRange(String s) {

        String[] sArray = s.split(":");
        char[] start = sArray[0].toCharArray();
        char[] end = sArray[1].toCharArray();

        List<String> res = new ArrayList<>();
        for(int i = 0; i < end[0] - start[0] + 1; i++) {
            for(int j = 0; j< end[1]-start[1]+1; j++) {
                char[] item = new char[2];
                item[0] = (char) (start[0] + i);
                item[1] = (char) (start[1] + j);
                res.add(String.valueOf(item));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // String s = "K1:L2";
        // String s = "A1:F1";
        String s = "C2:E3";
        var ans = new Problem_2194_ExcelCells().cellsInRange(s);
        for(String item : ans) {
            System.out.print(item + " ");
        }
        System.out.println();
    }


}
