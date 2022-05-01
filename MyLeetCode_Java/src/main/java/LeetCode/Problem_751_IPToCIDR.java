package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// IMP: 代码很麻烦, 很多细节, 多看!!

public class Problem_751_IPToCIDR {

    public List<String> ipToCIDR(String ip, int n) {
        List<String> ans = new ArrayList<>();
        if (ip == null || ip.length() == 0) {
            return ans;
        }
        int cur = ipToInt(ip);
        // 两个分支: 1. cur自己够用, 2. cur自己不够用(往左升1)
        while (n > 0) {
            int maxPower = mostRightPower(cur);
            int solved = 1; // 给1是ip自己本身就能解决一个, 另外方便右移
            int power = 0;
            // NOTE: 当前的1能解决的最大数量, 做了防止溢出判断, 保证每次很安全的向左移动
            // 否则超过了还得向右移回来
            while ((solved << 1) <= n && (power + 1) <= maxPower) {
                solved <<= 1;
                power++;
            }
            ans.add(genCIDR(cur, power));
            n -= solved; // 剩下没有解决的
            cur += solved; // 往左升一个1
        }
        return ans;
    }

    // 由数字生成CIDR形式
    private static String genCIDR(int cur, int power) {
        StringBuilder sb = new StringBuilder();
        int move = 24;
        int mask = (1 << 8) - 1; // ip地址一段是8位
        while (move >= 0) {
            // NOTE: 如果最高位是255, 是个负数
            int num = (cur >> move) & mask;
            sb.append(String.valueOf(num));
            sb.append(".");
            // if (move != 0) {
            //     sb.append(".");
            // }
            move -= 8;
        }
        // sb.append("/");
        sb.setCharAt(sb.length() - 1, '/');
        sb.append(String.valueOf(32 - power));
        return sb.toString();
    }

    private static int ipToInt(String ip) {
        int ans = 0;
        int move = 24;
        String[] numArr = ip.split("\\.");
        for (String num : numArr) {
            ans |= Integer.parseInt(num) << move;
            move -= 8;
        }
        return ans;
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    // 得到最右侧的1是2的多少次方
    private static int mostRightPower(int num) {
        if (map.isEmpty()) {
            map.put(0, 32); // 最高位的1溢出的时候, 全是32个0
            for (int i = 0; i < 32; i++) {
                map.put(1 << i, i);
            }
        }
        return map.get(num & (-num));
    }

    public static void main(String[] args) {
        String ip = "255.0.0.7";
        int n = 10;
        // var ans = ipToInt(ip);
        // System.out.println(ans);
        // System.out.println(genCIDR(ans, 2));
        var ans = new Problem_751_IPToCIDR().ipToCIDR(ip, n);
        for(String item : ans) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
