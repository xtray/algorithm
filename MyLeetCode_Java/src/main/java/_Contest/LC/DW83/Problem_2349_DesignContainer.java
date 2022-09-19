package _Contest.LC.DW83;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Problem_2349_DesignContainer {

    static class NumberContainers {
        // [数值, 下标(有序表)]
        private Map<Integer, TreeSet<Integer>> valueMap;
        // [下标, 数值] : 代表该下标有没有占用
        private Map<Integer, Integer> idxMap;

        public NumberContainers() {
            valueMap = new HashMap<>();
            idxMap = new HashMap<>();
        }

        public void change(int index, int number) {
            TreeSet<Integer> set = null;
            if (idxMap.containsKey(index)) { // 下标有数字
                int oldVal = idxMap.get(index); // 得到下标对应数值
                if(oldVal == number) return;
                // 更新为number
                idxMap.put(index, number);
                set = valueMap.get(oldVal); // 根据数值得到对应的valuemap
                set.remove(index); //  删除对应下标
                if(valueMap.get(oldVal).isEmpty()) {
                    valueMap.remove(oldVal);
                }
            } else { // 新下标
                idxMap.put(index, number);
            }
            valueMap.computeIfAbsent(number, key->new TreeSet<>()).add(index);
        }

        public int find(int number) {
            if (valueMap.containsKey(number)) {
                return valueMap.get(number).first();
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        // ["NumberContainers","change","find","change","find","find","find"]
        // [[],[1,10],[10],[1,20],[10],[20],[30]]
        NumberContainers sl = new NumberContainers();
        sl.change(1, 10);
        var ans = sl.find(10);
        System.out.println(ans);
        sl.change(1, 20);
        ans = sl.find(10);
        System.out.println(ans);
        ans = sl.find(20);
        System.out.println(ans);
        ans = sl.find(30);
        System.out.println(ans);
    }
}
