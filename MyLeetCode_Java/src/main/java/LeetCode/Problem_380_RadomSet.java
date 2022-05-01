package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_380_RadomSet {

    class RandomizedSet {

        private List<Integer> list;
        // key: 元素, value: index
        private Map<Integer, Integer> map;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            int size = list.size();
            int lastVal = list.get(size - 1);
            list.set(idx, lastVal); // 用最后的元素值填充, NOTE: list更新元素用set
            map.put(lastVal, idx); // 更新lastVal下标
            map.remove(val); // 删除val
            list.remove(size - 1);
            return true;
        }

        public int getRandom() {
            int size = list.size();
            return list.get((int) (Math.random() * size));
        }
    }

    public void test() {
        RandomizedSet sl = new RandomizedSet();
        System.out.print("null" + " ");
        System.out.print(sl.insert(1) + " ");
        System.out.print(sl.remove(2) + " ");
        System.out.print(sl.insert(2) + " ");
        System.out.print(sl.getRandom() + " ");
        System.out.print(sl.remove(1) + " ");
        System.out.print(sl.insert(2) + " ");
        System.out.print(sl.getRandom() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Problem_380_RadomSet sl = new Problem_380_RadomSet();
        sl.test();
    }
}
