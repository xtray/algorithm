package _Test;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;

public class TestArrayDeque {

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(3);
        deque.add(5);
        deque.remove(3); // ArrayDeque删除指定元素需要遍历找到第一个匹配的元素, 复杂度O(N)

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.remove(3);

        System.out.println("DONE");

    }
}
