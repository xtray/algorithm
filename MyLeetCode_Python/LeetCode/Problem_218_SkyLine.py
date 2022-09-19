from collections import defaultdict
from typing import List


class SegmentTree:
    def __init__(self):
        self.height = defaultdict(int)
        self.lazy = defaultdict(int)

    def push_down(self, i):
        # 懒标记下放，注意取最大值
        if self.lazy[i]:
            self.height[2 * i] = self.height[2 * i] if self.height[2 * i] > self.lazy[i] else self.lazy[i]
            self.height[2 * i + 1] = self.height[2 * i + 1] if self.height[2 * i + 1] > self.lazy[i] else self.lazy[i]

            self.lazy[2 * i] = self.lazy[2 * i] if self.lazy[2 * i] > self.lazy[i] else self.lazy[i]
            self.lazy[2 * i + 1] = self.lazy[2 * i + 1] if self.lazy[2 * i + 1] > self.lazy[i] else self.lazy[i]

            self.lazy[i] = 0
        return

    def update(self, l, r, s, t, val, i):
        # 更新区间最大值
        if l <= s and t <= r:
            self.height[i] = self.height[i] if self.height[i] > val else val
            self.lazy[i] = self.lazy[i] if self.lazy[i] > val else val
            return
        self.push_down(i)
        m = s + (t - s) // 2
        if l <= m:  # 注意左右子树的边界与范围
            self.update(l, r, s, m, val, 2 * i)
        if r > m:
            self.update(l, r, m + 1, t, val, 2 * i + 1)
        self.height[i] = self.height[2 * i] if self.height[2 * i] > self.height[2 * i + 1] else self.height[2 * i + 1]
        return

    def query(self, l, r, s, t, i):
        # 查询区间的最大值
        if l <= s and t <= r:
            return self.height[i]
        self.push_down(i)
        m = s + (t - s) // 2
        highest = 0
        if l <= m:
            cur = self.query(l, r, s, m, 2 * i)
            if cur > highest:
                highest = cur
        if r > m:
            cur = self.query(l, r, m + 1, t, 2 * i + 1)
            if cur > highest:
                highest = cur
        return highest


class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        # 端点去重
        pos = set()
        for left, right, _ in buildings:
            pos.add(left)
            pos.add(right)
        posList = sorted(list(pos))
        n = len(posList)
        postMap = {x: i for i, x in enumerate(posList)}
        # 离散化更新线段树
        seg = SegmentTree()
        for left, right, height in buildings:
            seg.update(postMap[left], postMap[right] - 1, 0, n - 1, height, 1)
        # 按照端点进行关键点查询
        pre = -1
        ans = []
        for pos in posList:
            h = seg.query(postMap[pos], postMap[pos], 0, n - 1, 1)
            if h != pre:
                ans.append([pos, h])
                pre = h
        return ans


def main(argv=None):
    sl = Solution()
    nums = [[2, 13, 10], [10, 17, 25], [12, 20, 14]]
    res = sl.getSkyline(nums)
    print(res)


if __name__ == '__main__':
    main()
