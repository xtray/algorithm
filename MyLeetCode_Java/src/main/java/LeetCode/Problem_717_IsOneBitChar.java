package LeetCode;

public class Problem_717_IsOneBitChar {

    // 第一种字符一定以 0 开头，第二种字符一定以 1 开头。
    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0 || bits.length == 1 && bits[0] != 0) {
            return false;
        }
        if (bits.length <= 2) {
            return bits[0] == 0;
        }
        // 长度>=3, 最后一位必须是 0
        int i = 0;
        while (i < bits.length - 1) {
            if (bits[i] == 0) i++;
            if (bits[i] == 1) i += 2;
        }
        // return i < bits.length;
        return i == bits.length - 1; // 如果 i 最后是一位加的, 能到 N-1, 如果是两位加则变成 N
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 1, 1, 0};
        var ans = new Problem_717_IsOneBitChar().isOneBitCharacter(num);
        System.out.println(ans);
    }

}
