package _aTemplate;


// 二进制枚举
// 用来解决例如下边这样的问题
// 给你一串数列，要你将其中所有可能出现的sum找出来，就是说每个数都有两个状态，选或者是不选，
// 那么就选择用二进制通过 0 1 来表示选还是不选
public class BinaryEnum {

    // n 为数组长度, 每一位选择或者不选择以该位上 0,1 标记
    public static void basicTemplate(int n) {

        //枚举每一个状态 0~ 2^n - 1 一共 2^n 个状态
        for (int i = 0; i < (1 << n); i++) {
            //枚举该状态下二进制的每一位数值
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 1) {
                    // 当前状态的第j位  是否为1
                    System.out.println(j);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        basicTemplate(5);
    }
}
