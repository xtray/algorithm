package LeetCode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Problem_895_FreqStack {

    class FreqStack {
        Map<Integer, Integer> freq;
        Map<Integer, ArrayDeque<Integer>> group;
        int maxFreq;

        public FreqStack() {
            freq = new HashMap<>();
            group = new HashMap<>();
            maxFreq = 0;
        }

        public void push(int x) {
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            if (f > maxFreq) {
                maxFreq = f;
            }
            group.computeIfAbsent(f, k -> new ArrayDeque<>()).push(x);
        }

        public int pop() {
            int x = group.get(maxFreq).pop();
            freq.put(x, freq.get(x) - 1);
            if (group.get(maxFreq).size() == 0) {
                maxFreq--;
            }
            return x;
        }
    }

}
