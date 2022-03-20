package _Test;

public class GetLeftMostOne {

    // num != 0
    public static int getAndRemoveLeftMostOne(int num) {

        int pos = -1;

        for(int i = 31; i>=0; i--) {
            if( ((1<<i) & num) != 0) {
                pos = i;
                break;
            }
        }

        return num - (1<<pos);
    }

    public static void main(String[] args) {
        int num = -1; // 1000
        var ans = getAndRemoveLeftMostOne(num);
        System.out.println(ans);
    }
}
