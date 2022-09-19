package _DailyTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_380_InsertDelGetO1_2 {

    static class RandomizedSet {

        // [ val : pos ]
        private Map<Integer, Integer> map = new HashMap<>();
        private List<Integer> list = new ArrayList<>();
        private int size;

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            list.add(val);
            map.put(val, size++);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            // 删除某个元素用最后位置的元素填充
            size--;
            // 1.收集信息
            int delPos = map.get(val);
            // // 删除的是最后一个元素的时候, 不用下面特殊处理也可以
            // if (delPos == size) { // 删除的是最后一个元素
            //     list.remove(size);
            //     map.remove(val);
            //     return true;
            // }
            int lastVal = list.get(size);
            // 2.做填充
            list.set(delPos, lastVal);
            map.put(lastVal, delPos);
            // 3.清理
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
