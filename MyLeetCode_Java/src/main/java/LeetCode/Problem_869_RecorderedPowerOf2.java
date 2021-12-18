package LeetCode;

import java.util.*;

public class Problem_869_RecorderedPowerOf2 {

    /**
     * 方法 1: 拆解数字各个位数, 然后做全排列得到新的数字
     * 最后判断一个数字是否是 2 的某次幂
     */
    public boolean reorderedPowerOf2(int n) {

        List<Integer> ans = new ArrayList<>();
        int[] list = getDigit(n);
        getAllNums(list, ans, 0);
        for (int num : ans) {
            if (isPowerOf2(num)) {
                return true;
            }
        }
        return false;
    }

    // 判断一个数是不是 2 的某次幂
    private boolean isPowerOf2(int n) {
        return (n & (n - 1)) == 0;
    }

    // 得到所有情况的全排列
    private void getAllNums(int[] input, List<Integer> ans, int index) {

        // 每个位数填充 index 位置
        if (index == input.length) {
            int num = getRealNum(input); // 去掉第一位是 0 的数
            if (num != -1) {
                ans.add(num);
            }
        } else {
            for (int i = index; i < input.length; i++) {
                swap(input, index, i);
                getAllNums(input, ans, index + 1);
                swap(input, index, i);
            }
        }
    }

    // 根据一个数组表达的数字形式得到真实的 num
    private int getRealNum(int[] input) {
        int N = input.length;
        if (input[N - 1] == 0) { // 剔除 0 开头的数
            return -1;
        }
        int pre = 0;
        for (int i = N - 1; i >= 0; i--) {
            int cur = input[i];
            pre = pre * 10 + cur;
        }
        return pre;
    }

    private void swap(int[] list, int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    // 获得数字位数长度
    private int getLength(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n = n / 10;
        }
        return len;
    }

    // 得到每一位数的数字存到数组里
    private int[] getDigit(int n) {
        int N = getLength(n);
        int[] list = new int[N];
        int index = 0;
        while (n != 0) {
            list[index++] = n % 10;
            n /= 10;
        }
        return list;
    }

    // 方法 2, 因为int 最大数是 2^31-1 = 2147483647
    // 所以 int 范围内所有的整形 0~30 次幂, 一共 31 个数
    // 只要当前数字 n 的某种排列跟上面 31 个数相同代表这个数可以排列为 2 的某次幂
    // 所以只要统计一个数 n 的各个位上 0~9 的词频即可, 这种方法不用排序
    // Map<Integer, List<int[]>>: key: 数字长度, value: 该长度下的词频统计数据
    // 类似 242_ValidAnagram 那道题
    private Map<Integer, List<int[]>> getAllNumCount() {
        Map<Integer, List<int[]>> countMap = new HashMap<>();
        // 统计 0~9 出现的词频
        List<int[]> res = new ArrayList<>();
        res.add(getCountArray(1));
        countMap.put(1, res);
        int num = 1;
        for (int i = 1; i <= 30; i++) {
            num *= 2;
            int len = getLength(num);
            List<int[]> list = countMap.get(len);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(getCountArray(num));
            countMap.put(len, list);
        }
        return countMap;
    }

    // 得到数字的词频统计表
    private int[] getCountArray(int num) {
        int[] count = new int[10];
        int[] list = getDigit(num);
        for (int digit : list) {
            count[digit]++;
        }
        return count;
    }

    public boolean reorderedPowerOf22(int n) {
        Map<Integer, List<int[]>> allCount = getAllNumCount();
        int[] list = getDigit(n);
        int len = getLength(n);
        // 只跟数字长度相同的做比较
        for (int[] numCount : allCount.get(len)) {
            int i = 0;
            for (; i < list.length; i++) {
                if (--numCount[list[i]] < 0) {
                    break;
                }
            }
            if (i == list.length) {
                return true;
            }
        }
        return false;
    }


    // 方法 3: 小优化
    // 把数字拆成单个数字排序后加入哈希表, 对判断数字也类似处理看是否在哈希表里
    Set<String> getAllPowerOf2() {
        Set<String> set = new HashSet<>();
        set.add(String.valueOf(1));
        int num = 1;
        for (int i = 1; i <= 30; i++) {
            num *= 2;
            int[] list = getDigit(num);
            Arrays.sort(list);
            set.add(getNumString(list));
        }
        return set;
    }

    private String getNumString(int[] list) {
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        return sb.toString();
    }

    public boolean reorderedPowerOf23(int n) {
        Set<String> set = getAllPowerOf2();
        int[] list = getDigit(n);
        Arrays.sort(list);
        return set.contains(getNumString(list));
    }

    public static void main(String[] args) {

        // 1948026527
        // 790568312
        // 1
        // 29058176

        Problem_869_RecorderedPowerOf2 sl = new Problem_869_RecorderedPowerOf2();
        int num = 29058176;
        boolean res = sl.reorderedPowerOf2(num);
        System.out.println(res);
        boolean res2 = sl.reorderedPowerOf22(num);
        System.out.println(res2);
        boolean res3 = sl.reorderedPowerOf23(num);
        System.out.println(res3);

        int time = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < time; i++) {
            int rand = (int) (Math.random() * 1000000000) + 1;
            boolean ans1 = sl.reorderedPowerOf2(rand);
            boolean ans2 = sl.reorderedPowerOf22(rand);
            boolean ans3 = sl.reorderedPowerOf23(rand);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Ops!--->" + rand);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
