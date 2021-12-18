package AlgoExpert;

import java.util.*;


// 支持改值的最小堆
public class Problem_000_MinHeapConstruction {

    static class MinHeap {
        List<Integer> heap = new ArrayList<Integer>();
        private int heapSize;

        public MinHeap(List<Integer> array) {
            heapSize = array.size();
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            if (array == null || array.size() == 0) {
                return new ArrayList<>();
            }
            List<Integer> res = new ArrayList<Integer>(array);
            for (int i = 0; i < array.size(); i++) {
                siftUp(i, res);
            }
            return res;
        }

        // heapify
        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int left = currentIdx << 1 | 1; // 左孩子的下标
            while (left <= endIdx) {// 下方还有孩子的时候
                int smallest = left + 1 <= endIdx && heap.get(left + 1) < heap.get(left) ? left + 1 : left;
                // 父和较大的孩子之间，谁的值大，把下标给largest
                smallest = heap.get(smallest) < heap.get(currentIdx) ? smallest : currentIdx;
                if (smallest == currentIdx) {
                    break; // 父亲就是最小的, 不用动了
                }
                swap(heap, smallest, currentIdx);
                currentIdx = smallest;
                left = currentIdx << 1 | 1;
            }
        }

        // heapinsert
        // arr[index]刚来的数，往上
        public void siftUp(int currentIdx, List<Integer> heap) {
            while (heap.get(currentIdx) < heap.get((currentIdx - 1)/2)) {
                swap(heap, currentIdx, (currentIdx - 1)/2);
                currentIdx = (currentIdx - 1) >>> 1;
            }
        }

        private void swap(List<Integer> heap, int i, int j) {
            int tmp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, tmp);
        }

        public int peek() {
            return heap.get(0);
        }

        public int remove() {
            int res = heap.get(0);
            heap.set(0, heap.get(--heapSize));
            heap.remove(heapSize);
            siftDown(0, heapSize - 1, heap);
            return res;
        }

        public void insert(int value) {
            heap.add(value);
            siftUp(heapSize++, heap);
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41};
        List<Integer> arr = new ArrayList<>();
        Collections.addAll(arr, array);
        MinHeap minHeap = new MinHeap(arr);
        minHeap.insert(76);
        int ans = minHeap.peek();
        System.out.println(ans);
        minHeap.remove();
        ans = minHeap.peek();
        System.out.println(ans);
//        minHeap.remove();
//        ans = minHeap.peek();
//        System.out.println(ans);
    }
}
