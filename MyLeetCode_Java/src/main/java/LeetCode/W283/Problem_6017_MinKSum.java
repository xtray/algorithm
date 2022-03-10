package LeetCode.W283;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode-cn.com/problems/append-k-integers-with-minimal-sum/
public class Problem_6017_MinKSum {

    public long minimalKSum(int[] nums, int k) {

        Arrays.sort(nums);
        List<int[]> gapList = new ArrayList<>();
        int lower = 1;
        int upper = (int) 1e9 + 1;
        for (int num : nums) {
            if (num > lower && num <= upper) {
                gapList.add(new int[]{lower, num - 1});
            }
            lower = num + 1;
        }
        if (lower <= upper) {
            gapList.add(new int[]{lower, upper});
            lower = upper + 1;
        }
        long sum = 0;
        int left = k;
        for (int[] gap : gapList) {
            int s = gap[0];
            int e = gap[1];
            long[] res = getRes(s, e, left);
            sum += res[0];
            if (res[1] == left) {
                return sum;
            }
            left -= res[1];
        }
        int base = lower;
        long[] extra = getRes(base, base + left - 1, left);
        return sum + extra[0];
    }

    // 从start...end 区间最多数 k 个数
    //返回: [sum, 跟真实个数]
    private long[] getRes(long start, long end, long k) {
        long size = end - start + 1;
        if (k <= size) {
            return new long[]{(start + start + k - 1) * k / 2, k};
        }
        // 如果不够 size < k
        return new long[]{(start + end) * size / 2, size};
    }

    public static void main(String[] args) {
        // int[] nums = {5,6};
        // int k = 6;

        // int[] nums = {1,4,25,10,25};
        // int k = 2;

        // int[] nums = {53, 41, 90, 33, 84, 26, 50, 32, 63, 47, 66, 43, 29, 88, 71, 28, 83};
        // int k = 76; // 3444

        // int[] nums = {4086366, 5582122, 602252, 194088, 5843831, 4688011, 4225061, 8086852, 8386614, 6397207, 3939315, 6563348, 5461867, 1699531, 5885475, 8053023, 4047122, 7456495, 3678920, 8243072, 377780, 2756730, 2238655, 4323623, 1154565, 4687386, 4308410, 1951050, 1891303, 1006569, 1061783, 6939240, 4713534, 8569169, 8726299, 6888672, 7984676, 135795, 4629646, 4516253, 9312465, 8462150, 3077367, 802271, 123243, 8262967, 1989621, 2533719, 1426910, 2797278, 2752827, 6004612, 9438815, 6132815, 2797005, 4429511, 7261388, 7973411, 5245256, 2696281, 8694421, 4296106, 1560394, 4496732, 8562126, 2532417, 6085845, 9364032, 5345936, 3462299, 3041207, 938739, 2354798, 5540338, 4359813, 5951900, 2207679, 5736816, 7707333, 1296648, 3918653, 847239, 1770683, 5759051, 4622699, 407228, 8817884, 9809250, 1519119, 1444821, 5038916, 8841743, 9720317, 4216286, 2062814, 5711520, 5439440, 1052348, 7446169, 9543139, 1917346, 4485838, 2047658, 3568114, 2258313, 4552562, 6947695, 2015302, 1796037, 7680038, 2009051, 8682473, 3484758, 2014331, 6859840, 4309197, 9137311, 1973701, 1079789, 8105112, 8708327, 2787814, 6617336, 8404464, 4417701, 2287618, 5590477, 4622699, 5270227, 9939149, 1990570, 4206791, 9574578, 6636757, 4871663, 2490722, 5203713, 8478043, 1813483, 5010841, 8867368, 5095178, 1917044, 26398, 3563047, 9699594, 9047579, 2118564, 3240432, 6173597, 3506966, 6583980, 136048, 4444722, 2477032, 1359924, 5886161, 8402840, 130246, 9583350, 8182998, 6175575, 6510635, 4354226, 8105636, 7650668, 7237252, 9813751, 1403441, 4059468, 3325422, 9574358, 6354440, 7185252, 8656682, 6232308, 6982621, 1265741, 6899242, 965724, 5020804, 1166479, 1202268, 2011019, 1015096, 6800199, 4139873, 4619521, 1865535, 4162165, 6724563, 3256758, 8580761, 6087629, 6035871, 4383929, 507862, 6021736, 4196698, 7618057, 2450252, 2495527, 4636943, 9821349, 1779822, 6695262, 5506788, 9959147, 5289563, 319802, 2594359, 8367482, 5769667, 6126724, 7122260, 9556036, 6970413, 3432498, 4204680, 66725, 8155578, 3054663, 5133426, 5897964, 6398030, 1229518, 3539606, 4426621, 447387, 8228027, 3966639, 8494754, 4653466, 2093971, 6106655, 3731405, 8782058, 2236724, 7085790, 3107254, 1060867, 9206908, 6767527, 7590952, 1138170, 2802192, 8409986, 9475557, 1648307, 9825848, 410114, 3537172, 8564044, 6253483, 463882, 8412682, 2896214, 9220546, 4778537, 4717637, 7354782, 3606191, 3486494, 4046755, 3815894, 5055736, 4776925, 1578093, 9624507, 4168578, 3518370, 1051575, 9528564, 7752314, 4087683, 3498469, 7799485, 3610326, 7002787, 5136544, 9394674, 2188796, 4178712, 2683164, 5154700, 9345189, 5990215, 7789758, 2690770, 5253367, 9150601, 2774297, 5639420, 7838201, 951477, 5941685, 4312091, 7270569, 4880182, 3458668, 3949835, 2576449};
        // int k = 1991601; // 1983286874545

        int[] nums = {1};
        // int[] nums = {1000000000};
        int k = 1000000000;
        //      1000000000
        // 500000000500000001
        // 500000000500000001

        // 500000000500000001

        var ans = new Problem_6017_MinKSum().minimalKSum(nums, k);
        System.out.println(ans);
    }

}
