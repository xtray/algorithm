package _Codility;


/**
 * 1. FrogJmp
 * Count minimal number of jumps from position X to Y.
 * https://app.codility.com/demo/results/trainingDM22UD-NXB/
 */
public class Problem_TR301_FrogJump {

    public int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        return (Y - X) % D == 0 ? (Y - X) / D : (Y - X + D) / D;
    }

}
