package LeetCode.JZOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_JZII030_RandomizedSet {
    class RandomizedSet {

        private List<Integer> list;
        private Map<Integer, Integer> map;
        private int size;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            size = 0; // 待存放的位置

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, size++);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            // NOTE: 有可能需要删的正好就是最后一个元素, 不过先填充再删无所谓了
            int pos = map.get(val); // 待填空的位置
            int last = list.get(--size); // 取出最后一个元素填空
            list.set(pos, last); // 最后一个元素填到空的位置上
            map.put(last, pos); // 更新填空的元素新位置
            // 清理
            list.remove(size); // 删除最后一个元素
            map.remove(val);
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
