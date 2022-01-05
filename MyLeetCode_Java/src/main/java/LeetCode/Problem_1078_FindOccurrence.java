package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_1078_FindOccurrence {

    public String[] findOcurrences(String text, String first, String second) {
        if (text == null || first == null || second == null || text.length() == 0 ||
                first.length() == 0 || second.length() == 0) {
            return new String[]{};
        }
        List<String> list = new ArrayList<>();
        String[] textArray = text.split(" ");
        if (textArray.length <= 2) {
            return new String[]{};
        }
        String preOne = textArray[0];
        String preTwo = textArray[1];
        for (int i = 2; i < textArray.length; i++) {
            String cur = textArray[i];
            if (preOne.equals(first) && preTwo.equals(second)) {
                list.add(cur);
            }
            preOne = preTwo;
            preTwo = cur;
        }
        // return list.toArray(new String[list.size()]);
        return list.toArray(new String[0]);
    }

    public String[] findOcurrences1(String text, String first, String second) {
        if (text == null || first == null || second == null || text.length() == 0 ||
                first.length() == 0 || second.length() == 0) {
            return new String[]{};
        }
        List<String> list = new ArrayList<>();
        String[] textArray = text.split(" ");
        for (int i = 0; i +2 < textArray.length; i++) {
            String preOne = textArray[i];
            String preTwo = textArray[i+1];
            String cur = textArray[i+2];
            if (preOne.equals(first) && preTwo.equals(second)) {
                list.add(cur);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
