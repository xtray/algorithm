package AlgoExpert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_000_ApartmentHunting {


    // 假设需求不多于 32 种的解法
    public static int apartmentHunting(List<Map<String, Boolean>> blocks, String[] reqs) {
        if (blocks == null || blocks.size() == 0 || reqs == null || reqs.length == 0) {
            return -1;
        }
        // 根据需求数组创建简化的 blocks 数组
        int M = reqs.length;
        int mask = fillOnes(M);
        // 将街区数组转换为一个 array, 每个数字里的 1 代表某个项目是否出现
        int[] blockList = generateBlockList(blocks, reqs);
        int minCost = Integer.MAX_VALUE;
        int minPos = -1;
        for (int i = 0; i < blockList.length; i++) {
            // 从每一个 i 出发, 向上下找最小的代价
            int cost = process(blockList, mask, i);
            if (cost < minCost) {
                minCost = cost;
                minPos = i;
            }
        }
        return minPos;
    }

    // 从 i 位置出发, 可以向上或者向下, 或操作 碰到 mask 的最小距离返回
    private static int process(int[] blockList, int mask, int i) {
        // 以 i 为中心, 向上下辐射
        int limit = Math.max(i, blockList.length - 1 - i);
        int r = 1;
        int status = blockList[i];
        int step = 0;
        while (r <= limit) {
            int preStatus = status;
            // 1.向上
            if (i - r >= 0) {
                status |= blockList[i - r];
            }
            // 2.向下
            if (i + r < blockList.length) {
                status |= blockList[i + r];
            }
            if(status!=preStatus) {
                step = r;
                if (status == mask) {
                    return step;
                }
            }
            r++;
        }
        return step;
    }

    private static int[] generateBlockList(List<Map<String, Boolean>> blocks, String[] reqs) {
        int[] list = new int[blocks.size()];
        int index = 0;
        for (Map<String, Boolean> map : blocks) {
            int status = 0;
            for (int i = 0; i < reqs.length; i++) {
                if (map.get(reqs[i])) {
                    status |= 1 << i;
                }
            }
            list[index++] = status;
        }
        return list;
    }

    private static int fillOnes(int len) {
        return -1 >>> (32 - len);
    }

//    public static void main(String[] args) {
//        List<Map<String, Boolean>> blocks = new ArrayList<>();
//        Map<String, Boolean> map0 = new HashMap<>();
//        map0.put("gym", false);
//        map0.put("school", true);
//        map0.put("store", false);
//        Map<String, Boolean> map1 = new HashMap<>();
//        map1.put("gym", true);
//        map1.put("school", false);
//        map1.put("store", false);
//        Map<String, Boolean> map2 = new HashMap<>();
//        map2.put("gym", true);
//        map2.put("school", true);
//        map2.put("store", false);
//        Map<String, Boolean> map3 = new HashMap<>();
//        map3.put("gym", false);
//        map3.put("school", true);
//        map3.put("store", false);
//        Map<String, Boolean> map4 = new HashMap<>();
//        map4.put("gym", false);
//        map4.put("school", true);
//        map4.put("store", true);
//        blocks.add(map0);
//        blocks.add(map1);
//        blocks.add(map2);
//        blocks.add(map3);
//        blocks.add(map4);
//
//        String[] reqs = new String[]{"gym", "school", "store"};
//
//        int ans = apartmentHunting(blocks, reqs);
//        System.out.println(ans);
//    }

    public static void main(String[] args) {
        List<Map<String, Boolean>> blocks = new ArrayList<>();
        Map<String, Boolean> map0 = new HashMap<>();
        map0.put("gym", false);
        map0.put("office", true);
        map0.put("school", true);
        map0.put("store", false);
        Map<String, Boolean> map1 = new HashMap<>();
        map1.put("gym", true);
        map1.put("office", false);
        map1.put("school", false);
        map1.put("store", false);
        Map<String, Boolean> map2 = new HashMap<>();
        map2.put("gym", true);
        map2.put("office", false);
        map2.put("school", true);
        map2.put("store", false);
        Map<String, Boolean> map3 = new HashMap<>();
        map3.put("gym", false);
        map3.put("office", false);
        map3.put("school", true);
        map3.put("store", false);
        Map<String, Boolean> map4 = new HashMap<>();
        map4.put("gym", false);
        map4.put("office", false);
        map4.put("school", true);
        map4.put("store", false);
        Map<String, Boolean> map5 = new HashMap<>();
        map5.put("gym", false);
        map5.put("office", false);
        map5.put("school", true);
        map5.put("store", true);
        blocks.add(map0);
        blocks.add(map1);
        blocks.add(map2);
        blocks.add(map3);
        blocks.add(map4);
        blocks.add(map5);

        String[] reqs = new String[]{"gym","office", "school", "store"};

        int ans = apartmentHunting(blocks, reqs);
        System.out.println(ans);
    }
}
