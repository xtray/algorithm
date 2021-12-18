package AlgoExpert;

import java.util.*;

public class Problem_000_TaskAssignment {

    public ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (tasks == null || tasks.size() == 0 || k == 0 || tasks.size() != 2 * k) {
            return ans;
        }
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for(int i=0;i<tasks.size();i++) {
            if(!indexMap.containsKey(tasks.get(i))) {
                indexMap.put(tasks.get(i), new ArrayList<>());
            }
            indexMap.get(tasks.get(i)).add(i);
        }

        Collections.sort(tasks);
        int L = 0;
        int R =  tasks.size()-1;
        while (L < R) {
            ArrayList<Integer> item = new ArrayList<>();
            item.add(getIndex(indexMap, tasks.get(L++)));
            item.add(getIndex(indexMap, tasks.get(R--)));
            ans.add(item);
        }
        return ans;
    }

    private int getIndex(Map<Integer, List<Integer>> indexMap, int val) {
        List<Integer> list = indexMap.get(val);
        return list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        Problem_000_TaskAssignment sl = new Problem_000_TaskAssignment();
        int k = 3;
        Integer[] arr = new Integer[]{1, 3, 5, 3, 1, 4};
        ArrayList<Integer> tasks = new ArrayList<>();
        Collections.addAll(tasks, arr);
        ArrayList<ArrayList<Integer>> res = sl.taskAssignment(k, tasks);
        for(List<Integer> list : res) {
            for(int num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
