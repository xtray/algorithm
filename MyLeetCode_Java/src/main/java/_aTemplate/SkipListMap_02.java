package _aTemplate;

import java.util.*;

public class SkipListMap_02 {

	// 跳表的节点定义
	public static class SkipListNode {
		public int key;
		public String val;
		public List<SkipListNode> nextNodes; // 每一个节点可能有多层链表

		public SkipListNode() {
			nextNodes = new ArrayList<>();
		}

		public SkipListNode(int k, String v) {
			key = k;
			val = v;
			nextNodes = new ArrayList<>();
		}
	}

	public static class SkipList {
		private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
		private SkipListNode head;
		private int size;
		private int maxLevel;

		public SkipList() {
			head = new SkipListNode();
			head.nextNodes.add(null); // 0
			size = 0;
			maxLevel = 0;
		}

		// 从最高层开始，一路找下去，
		// 最终，找到第0层的<key的最右的节点
		private SkipListNode mostRightLessNodeInTree(int key) {
			int level = maxLevel;
			SkipListNode cur = head;
			while (level >= 0) { // 从上层跳下层
				//  cur  level  -> level-1
				cur = mostRightLessNodeInLevel(key, cur, level--);
			}
			return cur;
		}

		// 在level层里，不停的往右, 找到<key最后一个节点并返回
		private SkipListNode mostRightLessNodeInLevel(
				int key,
				SkipListNode cur,
				int level) {
			SkipListNode next = cur.nextNodes.get(level);
			while (next != null && next.key < key) {
				cur = next;
				next = cur.nextNodes.get(level);
			}
			return cur;
		}

		public boolean containsKey(int key) {
			SkipListNode less = mostRightLessNodeInTree(key);
			SkipListNode next = less.nextNodes.get(0);
			return next != null && next.key == key;
		}

		// 新增、改value
		public void put(int key, String value) {
			// 0层上，最右一个，< key 的Node -> >key
			SkipListNode less = mostRightLessNodeInTree(key);
			SkipListNode find = less.nextNodes.get(0);
			if (find != null && find.key == key) {
				find.val = value;
			} else { // find == null   8   7   9
				size++;
				int newNodeLevel = 0;
				while (Math.random() < PROBABILITY) {
					newNodeLevel++;
				}
				// newNodeLevel
				while (newNodeLevel > maxLevel) {
					head.nextNodes.add(null);
					maxLevel++;
				}
				SkipListNode newNode = new SkipListNode(key, value);
				for (int i = 0; i <= newNodeLevel; i++) {
					newNode.nextNodes.add(null);
				}
				int level = maxLevel;
				SkipListNode pre = head;
				while (level >= 0) {
					// level 层中，找到最右的 < key 的节点
					pre = mostRightLessNodeInLevel(key, pre, level);
					if (level <= newNodeLevel) {
						newNode.nextNodes.set(level, pre.nextNodes.get(level));
						pre.nextNodes.set(level, newNode);
					}
					level--;
				}
			}
		}

		public String get(int key) {
			SkipListNode less = mostRightLessNodeInTree(key);
			SkipListNode next = less.nextNodes.get(0);
			return next != null && next.key == key ? next.val : null;
		}

		public void remove(int key) {
			if (containsKey(key)) {
				size--;
				int level = maxLevel;
				SkipListNode pre = head;
				while (level >= 0) {
					pre = mostRightLessNodeInLevel(key, pre, level);
					SkipListNode next = pre.nextNodes.get(level);
					// 1）在这一层中，pre下一个就是key
					// 2）在这一层中，pre的下一个key是>要删除key
					if (next != null && next.key == key) {
						// free delete node memory -> C++
						// level : pre -> next(key) -> ...
						pre.nextNodes.set(level, next.nextNodes.get(level));
					}
					// 在level层只有一个节点了，就是默认节点head
					if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
						head.nextNodes.remove(level);
						maxLevel--;
					}
					level--;
				}
			}
		}

		public int firstKey() {
			return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : -1;
		}

		public int lastKey() {
			int level = maxLevel;
			SkipListNode cur = head;
			while (level >= 0) {
				SkipListNode next = cur.nextNodes.get(level);
				while (next != null) {
					cur = next;
					next = cur.nextNodes.get(level);
				}
				level--;
			}
			return cur.key;
		}

		public int ceilingKey(int key) {
			SkipListNode less = mostRightLessNodeInTree(key);
			SkipListNode next = less.nextNodes.get(0);
			return next != null ? next.key : -1;
		}

		public int floorKey(int key) {
			SkipListNode less = mostRightLessNodeInTree(key);
			SkipListNode next = less.nextNodes.get(0);
			return next != null && next.key == key ? next.key : less.key;
		}

		public int size() {
			return size;
		}

	}

	// for test
	public static void printAll(SkipList obj) {
		for (int i = obj.maxLevel; i >= 0; i--) {
			System.out.print("Level " + i + " : ");
			SkipListNode cur = obj.head;
			while (cur.nextNodes.get(i) != null) {
				SkipListNode next = cur.nextNodes.get(i);
				System.out.print("(" + next.key + " , " + next.val + ") ");
				cur = next;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		SkipList test = new SkipList();
		printAll(test);
		System.out.println("========END==========");
		test.put(1, "10");
		printAll(test);
		System.out.println("=========END==========");
		test.remove(1);
		printAll(test);
		System.out.println("=========END==========");
		test.put(5, "E");
		test.put(2, "B");
		test.put(1, "A");
		test.put(6, "F");
		test.put(3, "C");
		test.put(4, "D");
		printAll(test);
		System.out.println("=========END==========");
		System.out.println(test.containsKey(2));
		System.out.println(test.containsKey(26));
		System.out.println(test.firstKey());
		System.out.println(test.lastKey());
		System.out.println(test.floorKey(4));
		System.out.println(test.ceilingKey(4));
		System.out.println("========END===========");
		test.remove(4);
		printAll(test);
		System.out.println("========END===========");
		System.out.println(test.floorKey(4));
		System.out.println(test.ceilingKey(4));
		System.out.println("========END===========");

	}

}
