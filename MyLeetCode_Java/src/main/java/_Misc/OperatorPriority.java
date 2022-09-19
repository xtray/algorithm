package _Misc;

public class OperatorPriority {

    public static void main(String[] args) {
        int n = 5;
        if( 3 > n>>1) { // NOTE: 右移优先级比 > 高
            System.out.println("OK");
        } else {
            System.out.println("NG");
        }

        int L = 0;
        int R = n;
        int mid = L + ((R-L)>>1); // NOTE: + 优先级比 >> 高, 所以要括号
    }
}
