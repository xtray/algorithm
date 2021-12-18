package _TBD;

import java.util.HashMap;
import java.util.HashSet;

// 题目链接 : https://leetcode.com/problems/zuma-game/

// 为什么用位运算, 因为 1 <= board.length <= 16 这个数据状况
// 同时有 五种牌, 可以用 3 个 bit 表示一种排, 这样 16* 3= 48 bit, 可以用一个 long 来表示
public class Problem_0488_ZumaGame {

	public static int max = -1;

	// bb: 桌上的牌
	// bomb: 取出的用来消除的手牌
	// insert: 手牌插入的位置
	public static long eliminate(long bb, long bomb, int insert) {
		int num = 1;
		long left = bb >>> insert; // 插入的左侧部分
		int l = 0;
		long right = bb << (64 - insert); // 插入的右侧部分, 右侧移到最高位
		int r = 61;// 处理右侧部分时, 最大开始位置, 因为左侧至少有一个(占 3 个 bit)
		int rsize = insert; // 右侧总的大小
		while (((right >>> r) & 7L) == bomb) {//从最高位往左移动, 逐个检测是否等于 bomb
			// 看右侧部分有几个跟 bomb 相同的
			r -= 3;
			num++;
		}
		if (num < 3) { // 不足 3 个, 不能消除, 插入丢出去的牌
			return left << (insert + 3) | (bomb << insert) | right >>> (64 - insert);
		} else {
			// 能消除 num >=3个, 左侧的是不同的, 消除右侧的
			// left >>>= l;
			right <<= 61 - r; // 把跟 bomb 相等的部分通过移位洗掉
			rsize -= 61 - r; // 减去消掉的大小更新 rsize: 剩余的右侧大小
			l = 0;
			r = 61; // 右侧的最大开始位置
			while (left != 0 && rsize != 0) { // 左右两侧至少一个还有东西
				num = 0;
				bomb = left & 7L; // 左侧的最右部分作为新的bomb
				while (((left >>> l) & 7L) == bomb) { // 看左侧部分有多少个等于 bomb
					l += 3;
					num++;
				}
				while (((right >>> r) & 7L) == bomb) {
					r -= 3;
					num++;
				}
				if (num < 3) {
					break; // 不存在二次消除的情况
				} else {
					left >>>= l; // 抹去消掉的
					right <<= 61 - r; // 抹去消掉的
					rsize -= 61 - r; // 更新右侧剩余大小
					l = 0;
					r = 61;
				}
			}
			return (left << rsize) | (right >>> (64 - rsize)); // 把剩余的左右侧拼成新的 board
		}
	}

	// 位运算的解
	// 空 -> 0
	// 'R' -> 1
	// 'Y' -> 2
	// 'B' -> 3
	// 'G' -> 4
	// 'W' -> 5
	// 0~5 二进制 最大 101, 3bit
	// board 长度 1~16, 一个 long 64bit , 64/3=21 足够表示 board 所有状态
	public static int findMinStep1(String board, String hand) {
		max = -1;
		long bb = 0L; // 桌面的牌
		int offset = 0;
		for (char cha : board.toCharArray()) {
			if (cha == 'R') {
				bb |= 1L << offset;
			} else if (cha == 'Y') {
				bb |= 2L << offset;
			} else if (cha == 'B') {
				bb |= 3L << offset;
			} else if (cha == 'G') {
				bb |= 4L << offset;
			} else { // 'W'
				bb |= 5L << offset;
			}
			offset += 3; // 每 3 个 bit 标记一个出现的卡牌
		}
		long hc = 0L;  // 手牌, 长度 1~5
		for (char cha : hand.toCharArray()) {
			if (cha == 'R') {
				hc += 1L << 20;
			} else if (cha == 'Y') {
				hc += 1L << 15;
			} else if (cha == 'B') {
				hc += 1L << 10;
			} else if (cha == 'G') {
				hc += 1L << 5;
			} else { // 'W'
				hc++; // 每 5个 bit 标记出现的字母数量, 所以用+=
			}
		}
		process1(bb, hc, hand.length());
		// max == -1: 没有办法用手牌消除干净 board, max 维持-1
		// 返回: 用掉的牌数
		return max == -1 ? -1 : hand.length() - max;
	}

	// 真正有效的可变参数：bb，hc
	// 其他参数完全由这两个参数决定--> hs 完全可以由 hs 算出来
	// bb: 所有出现的卡牌排列, 用 64bit,3 个一组一张卡牌的方式表示
	// hc: 所有手牌及其数量, 每 5 个 bit 表示一种卡牌及其出现的数量
	// hs: 手里剩余的总卡牌数量
	public static void process1(long bb, long hc, int hs) {
		if (hs > max && (bb == 0 || hs != 0)) {
			if (bb == 0) { // 消除到桌面上没有卡牌了
				max = hs; // 桌面上没有牌时, 手里剩的牌的数目
			} else { // bb != 0 && hs != 0
				long bomb = 1L; // 1,2,3,4,5
				// 弹药枚举
				for (int i = 20; i >= 0; i -= 5, bomb++) { // 手里最多 bomb 5 张, i = 20, 先看 R
					if (((hc >>> i) & 31) != 0) { // 31--> 2^5 -1 == 0000~000011111, 手牌这个位置的卡牌有数量 !=0
						long nhc = hc - (1L << i); // 用掉一张卡牌后的数量 nhc: new hc
						// 插入位置枚举
						// 7L -> 111
						// 一开始 bomb 是 1, 代表 R 这张牌, ++, 代表后面的 4 张牌
						// (bb >>> (j - 3) & 7L) == 0 --> 开始没有卡牌了, 再往左的位置更不会有卡牌了
						for (int j = 3; j < 64 && (bb >>> (j - 3) & 7L) != 0; j += 3) { // 第一个插入位置没有必要从 0 开始,小贪心
							if ((bb >>> j & 7L) != bomb) { // 一直移动到左侧的字符跟 bomb 不相等停止, 先消除右侧的
								process1(eliminate(bb, bomb, j), nhc, hs - 1);
							}
						}
					}
				}
			}
		}
	}

	// findMinStep1是暴力递归，findMinStep2是它的动态规划版本
	public static int findMinStep2(String board, String hand) {
		max = -1;
		long bb = 0L;
		int offset = 0;
		for (char cha : board.toCharArray()) {
			if (cha == 'R') {
				bb |= 1L << offset;
			} else if (cha == 'Y') {
				bb |= 2L << offset;
			} else if (cha == 'B') {
				bb |= 3L << offset;
			} else if (cha == 'G') {
				bb |= 4L << offset;
			} else {
				bb |= 5L << offset;
			}
			offset += 3;
		}
		long hc = 0L;
		for (char cha : hand.toCharArray()) {
			if (cha == 'R') {
				hc += 1L << 20;
			} else if (cha == 'Y') {
				hc += 1L << 15;
			} else if (cha == 'B') {
				hc += 1L << 10;
			} else if (cha == 'G') {
				hc += 1L << 5;
			} else {
				hc++;
			}
		}
		process2(bb, hc, hand.length(), new HashMap<Long, HashSet<Long>>());
		return max == -1 ? -1 : hand.length() - max; // 返回用掉的牌数
	}

	// 加了缓存
	// 真正有效的可变参数：bb，hc
	// 其他参数完全由这两个参数决定--> hs 以及 max 完全可以由 hs 算出来
	// bb: 所有出现的卡牌排列, 用 64bit,3 个一组一张卡牌的方式表示
	// hc: 所有手牌及其数量, 每 5 个 bit 表示一种卡牌及其出现的数量
	// hs: 手里剩余的总卡牌数量
	// max: 当桌面消除干净时, 手里剩余的牌, 此时 hs == max
	public static void process2(long bb, long hc, int hs, HashMap<Long, HashSet<Long>> map) {
		if (hs > max && (bb == 0 || hs != 0) && (!map.containsKey(bb) || !map.get(bb).contains(hc))) {
			if (!map.containsKey(bb)) {
				map.put(bb, new HashSet<>());
			}
			map.get(bb).add(hc);
			if (bb == 0) {
				max = hs;
			} else { // bb != 0 && hs != 0
				long bomb = 1L;
				// 弹药枚举
				for (int i = 20; i >= 0; i -= 5, bomb++) {
					if (((hc >>> i) & 31) != 0) {
						long nhc = hc - (1L << i);
						// 插入位置枚举
						for (int j = 3; j < 64 && (bb >>> (j - 3) & 7L) != 0; j += 3) {
							if ((bb >>> j & 7L) != bomb) {
								process2(eliminate(bb, bomb, j), nhc, hs - 1, map);
							}
						}
					}
				}
			}
		}
	}

}
