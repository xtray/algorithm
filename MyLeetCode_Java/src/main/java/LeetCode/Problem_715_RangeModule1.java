package LeetCode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// https://leetcode.cn/problems/range-module/

public class Problem_715_RangeModule1 {

	class RangeModule {
		// 某个区间的开头是key，结尾是value
		// 目标：不管怎么调用，一定要保证！
		// map里表示的区间，能合并就合并，且没有交集
		TreeMap<Integer, Integer> map;

		public RangeModule() {
			map = new TreeMap<>();
		}

		// 加入一个区间
		public void addRange(int left, int right) {
			// 无效区间直接返回
			if (right <= left) {
				return;
			}
			// 有效区间！
			// 当前要加入的区间是[left, right)
			Integer start = map.floorKey(left);
			Integer end = map.floorKey(right);
			if (start == null && end == null) {
				map.put(left, right);
			} else if (start != null && map.get(start) >= left) {
				map.put(start, Math.max(map.get(end), right));
			} else {
				map.put(left, Math.max(map.get(end), right));
			}
			Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
			Set<Integer> set = new HashSet<>(subMap.keySet());
			map.keySet().removeAll(set);
		}

		public boolean queryRange(int left, int right) {
			// [34, 76) 整体被你的结构，有没有包含！
			// <=34 开头都没！
			Integer start = map.floorKey(left);
			if (start == null)
				return false;
			// [34, 76) 整体被你的结构，有没有包含！
			// <=34 开头有！[17，~ 60) [60 ~ 76)
			return map.get(start) >= right;
		}

		public void removeRange(int left, int right) {
			if (right <= left) {
				return;
			}
			Integer start = map.floorKey(left);
			Integer end = map.floorKey(right);
			if (end != null && map.get(end) > right) {
				map.put(right, map.get(end));
			}
			if (start != null && map.get(start) > left) {
				map.put(start, left);
			}
			Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
			Set<Integer> set = new HashSet<>(subMap.keySet());
			map.keySet().removeAll(set);
		}
	}

	public static void main(String[] args) {
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(6, "我是6");
		map.put(3, "我是3");
		map.put(9, "我是9");
		map.put(5, "我是9");
		map.put(4, "我是9");
		// 3 4 5 6 9
		// [4~6) -> 4, 5,6

		Map<Integer, String> subMap = map.subMap(4, true, 6, false);

		for (int key : subMap.keySet()) {
			System.out.println(key);
		}

	}

}
