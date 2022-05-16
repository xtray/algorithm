package _aTemplate;

// ref: https://www.zhihu.com/question/38573286
// ref: https://hur.st/bloomfilter

public class BoolmFilter {

    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    // false positive probability
    static double accurateFpp(long n, long m, int k) {
        double power = -(n * k) / (double) m;
        return Math.pow(1 - Math.pow(Math.E, power), k);
    }

    public static void main(String[] args) {
        double p = 0.0001; // 万分之一
        long n = (long) 1e10; // 100亿
        long m = optimalNumOfBits(n, p);
        System.out.println("样本量100亿, 失误率万分之一下需要的硬盘空间: " + (int) (m / 8e9) + "G"); // 23G

        int k = optimalNumOfHashFunctions(n, m);
        System.out.println("需要的独立哈希函数个数: " + k); // 13个

        // 如果m = 30G 的真实失误率  1G  8*10^10 bit
        m = (long) (30 * 1e9 * 8);
        var realFpp = accurateFpp(n, m, k);
        System.out.println("30G硬盘做位图下的真实失误率" + realFpp); // 0.000011975, 十万分之1.2
    }
}
