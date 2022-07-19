package BigComQues;

import java.util.List;
import java.util.TreeSet;

/**
 * Given an array ranks of ranks of students in a school. All students need to be split into groups k.
 * Find the total 'imbalance' of all groups. An imabalance of a group can be found as :
 *
 * Sorting each group in the order of their ranks.
 * A group contributes to imbalance if any 2 students in the sorted array have a rank difference
 * of more than 1.
 * Find the total sum of imbalance of all such groups.
 *
 * This is the example that was given :
 * [4,1,3,2]
 * [1] contributes 0 to imbalance
 * [2] contributes 0 to imbalance
 * [3] contributes 0 to imbalance
 * [4] contributes 0 to imbalance
 * [4,1] contributes 1 to imbalance
 * [4,3] contributes 0 to imbalance
 * [4,2] contributes 1 to imbalance
 * [4,1,3,2] contributes 0 to imbalance
 * [1,3] contributes 1 to imbalance
 * [1,2] contributes 0 to imbalance
 * [3,2] contributes 0 to imbalance
 * Answer = 1 + 1 + 1 = 3
 */

public class Problem_Amazon_TotalImbalance {

    public static long imbalance(List<Integer> rank) {
        long imbalance = 0;
        int r = 0;
        TreeSet<Integer> set = new TreeSet<>();
        while(r < rank.size()-1) {
            set.clear();
            set.add(rank.get(r));
            long curImbalance = 0;
            for(int i=r+1; i<rank.size(); i++) {
                Integer e = rank.get(i);
                set.add(e);
                Integer f = set.lower(e);
                Integer c = set.higher(e);

                if(f == null) { // added at beginning
                    curImbalance += (((c - e) > 1) ? 1 : 0);
                }
                else if(c == null) {// added at end
                    curImbalance += (((e - f) > 1) ? 1 : 0);
                }
                else {
                    curImbalance += (c - f) > 1 ? -1 : 0;
                    curImbalance += (((c - e) > 1) ? 1 : 0);
                    curImbalance += (((e - f) > 1) ? 1 : 0);
                }
                imbalance += curImbalance;
            }
            r++;
        }
        return imbalance;
    }

    public static void main(String[] args) {

    }
}
