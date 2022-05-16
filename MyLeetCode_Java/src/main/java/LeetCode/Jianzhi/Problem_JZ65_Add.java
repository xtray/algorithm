package LeetCode.Jianzhi;

// IMP: 记住, 非常重要!!!
// 在计算机系统中，数值一律用 补码 来表示和存储, 方法同时适用于正数和负数的加法

public class Problem_JZ65_Add {

    public int add(int a, int b) {

        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b; // 无进位相加
            b = c; // 进位
        }
        return a;
    }
}
