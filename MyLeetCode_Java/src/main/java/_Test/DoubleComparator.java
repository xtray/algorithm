package _Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class DoubleComparator {

    public static void main(String[] args) {

        PriorityQueue<Double> reveseOrderPq =
        new PriorityQueue<>(((o1, o2) -> o2.compareTo(o1)));
        // PriorityQueue<Double> reveseOrderPq =
        //         new PriorityQueue<>((Comparator.reverseOrder()));
        reveseOrderPq.add(1.0);
        reveseOrderPq.add(3.0);
        reveseOrderPq.add(2.2);
        while (!reveseOrderPq.isEmpty()) {
            System.out.print(reveseOrderPq.poll() + " ");
        }
        System.out.println();
    }
}
