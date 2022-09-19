package _DailyTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_380_InsertDelGetO1 {

    static class RandomizedSet {
        private Map<Integer, Integer> map;
        private List<Integer> list;
        private int size;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            list.add(val);
            map.put(val, size++);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            // 用最后一个元素替换
            int delPos = map.get(val);
            int lastVal = list.get(--size);
            map.put(lastVal, delPos);
            list.set(delPos, lastVal);
            // NOTE: 注意顺序, 先调整, 最后删除, 只有1个的时候也可以支持
            map.remove(val);
            list.remove(size);
            return true;
        }

        public int getRandom() {
            return list.get((int) (Math.random() * size));
        }
    }

    public static void main(String[] args) {
        RandomizedSet rs = new RandomizedSet();
        var ans = rs.remove(0);
        System.out.println(ans);
        ans = rs.remove(0);
        System.out.println(ans);
        ans = rs.insert(0);
        System.out.println(ans);
        var num = rs.getRandom();
        System.out.println(num);
        ans = rs.remove(0);
        System.out.println(ans);
        ans = rs.insert(0);
        System.out.println(ans);
    }
}
