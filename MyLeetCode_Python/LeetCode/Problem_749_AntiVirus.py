from collections import defaultdict
from typing import List

class UnionFind:
    def __init__(self, n):
        self.root = [i for i in range(n)]
        self.size = [1] * n
        self.part = n

    def find(self, x):
        if x != self.root[x]:
            root_x = self.find(self.root[x])
            self.root[x] = root_x
            return root_x
        return x

    def union(self, x, y):
        root_x = self.find(x)
        root_y = self.find(y)
        if root_x == root_y:
            return
        if self.size[root_x] >= self.size[root_y]:
            root_x, root_y = root_y, root_x
        self.root[root_x] = root_y
        self.size[root_y] += self.size[root_x]
        self.part -= 1
        return


class Solution:
    def containVirus(self, isInfected: List[List[int]]) -> int:
        m, n = len(isInfected), len(isInfected[0])
        # 建立并查集结构
        uf = UnionFind(m * n)
        for i in range(m):
            for j in range(n):
                if isInfected[i][j] == 1:
                    if i + 1 < m and isInfected[i + 1][j] == 1:
                        uf.union(i * n + j, i * n + n + j)
                    if j + 1 < n and isInfected[i][j + 1] == 1:
                        uf.union(i * n + j, i * n + j + 1)
        ans = 0
        while True:
            # 找寻当前的病毒块
            part = defaultdict(set)
            for i in range(m * n):
                if isInfected[i // n][i % n] == 1:
                    part[uf.find(i)].add(i)

            # 找寻病毒的感染区域
            inspect = []
            for root in part:
                area = []
                for num in part[root]:
                    i, j = num // n, num % n
                    nodes = [[i + 1, j], [i - 1, j], [i, j - 1], [i, j + 1]]
                    nodes = [node for node in nodes if 0 <= node[0] < m and 0 <= node[1] < n]
                    nodes = [node for node in nodes if isInfected[node[0]][node[1]] == 0]
                    for node in nodes:
                        area.append(tuple(node))
                # 注意感染区域个数为集合唯一个数而防火墙为列表个数
                inspect.append([len(set(area)), root, area])
            # 不存在感染区域了则退出
            inspect.sort(key=lambda x: x[0], reverse=True)
            if not inspect or inspect[0][0] == 0:
                break
            # 安装防火墙
            ans += len(inspect[0][2])
            for i in part[inspect[0][1]]:
                isInfected[i // n][i % n] = 2
            # 下一轮扩散
            for count, root, area in inspect[1:]:
                if not count:
                    continue
                for i, j in area:
                    isInfected[i][j] = 1
                    # 更新并查集
                    nodes = [[i + 1, j], [i - 1, j], [i, j - 1], [i, j + 1]]
                    nodes = [node for node in nodes if 0 <= node[0] < m and 0 <= node[1] < n]
                    nodes = [node for node in nodes if isInfected[node[0]][node[1]] == 1]
                    for node in nodes:
                        uf.union(i * n + j, node[0] * n + node[1])
        return ans
