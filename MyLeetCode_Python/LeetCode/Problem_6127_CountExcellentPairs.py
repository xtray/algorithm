from collections import defaultdict, Counter
from typing import List


class Solution:
    def countExcellentPairs(self, nums: List[int], k: int) -> int:
        tmp = defaultdict(int)
        for num in set(nums):
            tmp[num.bit_count()] += 1  # 3.10
        ans = 0
        for x in tmp:
            for y in tmp:
                if x + y >= k:
                    ans += tmp[x] * tmp[y]
        return ans

    def countExcellentPairs1(self, nums: List[int], k: int) -> int:
        cnt = Counter(x.bit_count() for x in set(nums))
        ans = 0
        for i, x in cnt.items():
            for j, y in cnt.items():
                if i + j >= k:
                    ans += x * y
        return ans

    def countExcellentPairs2(self, nums: List[int], k: int) -> int:
        ans = 0
        cnt = [0] * 30 # 1~29
        for x in set(nums):
            cnt[x.bit_count()] += 1
        s = sum(cnt[k:]) # >= k的数目直接获得
        for i, c in enumerate(cnt):
            ans += c * s
            if 0 <= k - 1 - i < 30:
                s += cnt[k - 1 - i]
        return ans


def main(argv=None):
    sl = Solution()
    nums = [1, 2, 3, 1]
    k = 3
    cnt = sl.countExcellentPairs2(nums, k)
    print(cnt)


if __name__ == '__main__':
    main()
