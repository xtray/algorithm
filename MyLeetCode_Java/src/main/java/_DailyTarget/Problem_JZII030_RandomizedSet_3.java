package _DailyTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_JZII030_RandomizedSet_3 {

    class RandomizedSet {

        private Map<Integer, Integer> map;
        private List<Integer> list;
        private int size;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            size = 0;
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, size++);
            list.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            // 用最后一个数去填充要删除的数的位置
            int last = list.get(--size);
            int toPos = map.get(val);
            map.put(last, toPos);
            map.remove(val);
            list.set(toPos, last);
            list.remove(size);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return list.get((int) (Math.random() * size));
        }
    }
}
