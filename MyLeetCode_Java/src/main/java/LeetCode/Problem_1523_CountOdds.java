package LeetCode;

public class Problem_1523_CountOdds {

    public int countOdds(int low, int high) {
        boolean lowOdd = (low & 1) == 1;
        boolean hiOdd = (high & 1) == 1;
        if(lowOdd && hiOdd) {
            return ((high-low + 1)>> 1) + 1;
        } else {
            return (high-low + 1)>> 1;
        }
    }
}
