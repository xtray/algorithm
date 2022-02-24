package AlgoExpert;

import java.util.ArrayList;
import java.util.List;

/**
 * 俄罗斯信封套娃三维问题
 * ref: https://leetcode-cn.com/problems/russian-doll-envelopes/solution/ru-he-pai-xu-ru-he-kuo-zhan-dao-gao-wei-wr4qc/
 *
 */
public class Problem_000_DiskStacking {

    //
    public static List<Integer[]> diskStacking(List<Integer[]> disks) {
        List<Integer[]> ans = new ArrayList<>();
        if (disks == null || disks.size() == 0) {
            return ans;
        }
        disks.sort((o1, o2) -> o1[0] == o2[0] ? (o1[1] == o2[1] ? o1[2] - o2[2] : o1[1] - o2[1]) : o1[0] - o2[0]);
        Integer[] base = disks.get(0);
        for (int i = 1; i < disks.size(); i++) {
            Integer[] cur = disks.get(i);


        }


        return new ArrayList<Integer[]>();
    }
}
