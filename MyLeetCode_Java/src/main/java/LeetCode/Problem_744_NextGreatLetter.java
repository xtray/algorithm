package LeetCode;

public class Problem_744_NextGreatLetter {

    public char nextGreatestLetter(char[] letters, char target) {
        if( letters == null || letters.length == 0) {
            return '0';
        }
        int L = 0;
        int R = letters.length - 1;
        int ans = -1;
        // >= target最左
        while (L<=R) {
            int mid = L + ((R-L)>>1);
            if(letters[mid] > target) {
                ans = mid;
                R = mid -1;
            }else {
                L = mid + 1;
            }
        }
        return ans == -1? letters[0] : letters[ans];
    }
}
