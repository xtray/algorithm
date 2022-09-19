package _Exercise;

// leetcode上，所有题解都只能做到O( (logN) 平方)的解
// 课上讲的是O(logN)的解

// NOTE: 比较难, 多看!!
// ref: 386

// 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小 的数字。
public class Problem_440_KthSmallestInLexicographicalOrder {

    // 获取第1个数字
    public static int[] offset = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

    // 以x开头<=k位的数有多少个
    public static int[] number = {0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111};

    // 分区间讨论
    // ....left....| ...Mid...| ....right...
    // 65237
    // 左边: 1~5长度<=5位的
    // 中间: 必须6开头的<=5位的
    // 右边: 7~9开头的, 长度<=4位的
    public static int findKthNumber(int n, int k) {
        // 数字num，有几位，len位
        // 65237, 5位，len = 5
        int len = len(n);
        // 65237, 开头数字，6，first
        int first = n / offset[len];
        // 65237，左边有几个？ 1~5开头
        int left = (first - 1) * number[len]; // 1开头的左边就是0个
        int pickSlot = 0; // 在number数组里扎到的槽位
        int already = 0;
        if (k <= left) {// 找到来自左侧谁开头的
            // k / a 向上取整-> (k + a - 1) / a
            // pick: 第k个数在number数组里扎到的槽位,
            // 注意是要除 number 数组, 而不是 offset数组, 因为要找到第k个数扎到了 number数组第几个槽位
            // 比如算出3.1, 其实是来到了第4个数
            pickSlot = (k + number[len] - 1) / number[len];
            // already: 以pick这个最高位开头数字 <= len的一共有多少个
            // pick 这个槽位会有断层, 所以先计算这个槽位之前压了多少数字
            already = (pickSlot - 1) * number[len];
            return kth((pickSlot + 1) * offset[len] - 1, len, k - already);
        }
        int mid = number[len - 1] + (n % offset[len]) + 1;
        if (k - left <= mid) {
            return kth(n, len, k - left);
        }
        k -= left + mid;
        // 右侧范围的数长度要小1位
        len--;
        // > first 以上的数字的第pickSlot的槽位
        // first: 基准开始的中间槽位
        pickSlot = (k + number[len] - 1) / number[len] + first;
        // already: 在右边槽位数字分布上压过的数, -1 是不包括本身, 是下面的数
        already = (pickSlot - first - 1) * number[len];
        return kth((pickSlot + 1) * offset[len] - 1, len, k - already);
    }

    public static int len(int n) {
        int len = 0;
        while (n != 0) {
            n /= 10;
            len++;
        }
        return len;
    }

    // 位数<=len时候, 不能超过maxLimit的 第kth个
    // ans 最高位一定是maxLimit的最高位
    public static int kth(int maxLimit, int len, int kth) {
        // 中间范围还管不管的着！
        // 有任何一步，中间位置没命中，左或者右命中了，那以后就都管不着了！
        // 但是一开始时，肯定是管的着的！
        boolean closeToMidMaxLimit = true;
        // 必须以ans这个数字开头, 也就是第一位固定是 maxLimit / offset[len]
        int ans = maxLimit / offset[len];
        // 如果kth == 1, 就是单独ans一个数字, 直接返回, 不会进入while
        // 之后剩下的数字就是 --kth
        // 每一个新加的数字要单算, 后面不跟东西, 所以都要先 --kth
        while (--kth > 0) { // 先--, 减掉当前正好为ans的那个数(1个), 循环里要往后append了
            // 开头数字定了, 长度少了一位,
            // 在新的长度下, 更新新的maxLimit限制, 长度后面也要len--
            maxLimit %= offset[len--]; // 剥掉最高位剩下的限制
            int pickSlot = 0; // 卡到的槽位
            if (!closeToMidMaxLimit) {
                // 中间位置管不着了, 以后都管不着了, 都是走这个逻辑, maxLimit对你没有任何影响, 因为他已经管不着了
                // 选取每一位的数字
                // 关于-1:
                //         0 --> -1
                //         x
                pickSlot = (kth - 1) / number[len]; // 找到在number 中的槽位
                ans = ans * 10 + pickSlot;
                kth -= pickSlot * number[len];
            } else {
                // 获取第一个数字, 做范围划分:  ....left...|...mid(first xxx)...|...right...
                int first = maxLimit / offset[len];
                int left = first * number[len]; // 这里包括0,即: [0, first-1] 所以不用first-1
                // 命中左边, 中间位置以后都管不着了
                if (kth <= left) {
                    closeToMidMaxLimit = false;
                    // kth - 1: 是减掉0开头的
                    pickSlot = (kth - 1) / number[len];
                    ans = ans * 10 + pickSlot;
                    kth -= pickSlot * number[len];
                    continue;
                }
                // 命中中间, 中间位置后面不变
                kth -= left;
                int mid = number[len - 1] + (maxLimit % offset[len]) + 1;
                if (kth <= mid) {
                    ans = ans * 10 + first;
                    continue;
                }
                // 命中右边, 中间位置以后都管不着了
                closeToMidMaxLimit = false;
                kth -= mid;
                len--;
                pickSlot = (kth + number[len] - 1) / number[len] + first; // 向上取整
                ans = ans * 10 + pickSlot;
                kth -= (pickSlot - first - 1) * number[len];
            }
        }
        return ans;
    }


    // 字典树的思想, O((logN)^2)
    // 利用字典树的特性将所有小于等于 n 的数字按照字典序的方式进行重建
    // 整个字典序排列也就是对十叉树进行先序遍历
    // 已知节点 i 的子节点为(10×i,10×i+1,⋯,10×i+9)
    // ref: https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/zi-dian-xu-de-di-kxiao-shu-zi-by-leetcod-bfy0/
    public static int findKthNumber2(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            // == 的时候不够, 是因为重复计算curr这个头部的数, 多算了一个, 实际要减掉
            if (steps <= k) {
                k -= steps;
                curr++; // 移到左边兄弟节点上
            } else { // 当前以curr为头的数大小cover k 了, 往下扎一层, 继续找
                curr = curr * 10;
                k--; // curr * 10 这个数占一个
            }
        }
        return curr;
    }

    // 即找到以 curr 为头的子树下有多少节点
    // 按照层次遍历子树
    public static int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            // 第 i 层的最右侧节点应 min(n, last)
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }


    public static void main(String[] args) {
        // int n = 65237;
        // int k = 21034;
        // int n = 34;
        // int k = 32;
        // 主函数从中间进入kth, kth函数进入右侧的例子
        // int n = 51492;
        // int k = 46528;
        // int n = 13531;
        // int k = 4250;
        int n = 13;
        int k = 12;
        var ans = findKthNumber(n, k);
        System.out.println(ans);
        var ans2 = findKthNumber2(n, k);
        System.out.println(ans2);

        int times = 0;
        ;
        int numMax = 100000;
        for (int i = 0; i < times; i++) {
            int num = (int) (Math.random() * numMax) + 1;
            int kth = (int) (Math.random() * numMax) + 1;
            var res = findKthNumber(num, kth);
            System.out.printf("num: %d, kth: %d, ans=%d.\n", num, kth, res);
        }
        System.out.println();
    }

}
