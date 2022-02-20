class Solution(object):
    '''
    def fourSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[List[int]]
        """

    '''

    # 依然套用三数之和的双指针法, 前两个指针遍历, 后两个指针相向检索. 技巧是加入提前判定条件, 略过不必要的计算.
    # O(N^3)
    """
    def fourSum(self, nums, target):
        nums.sort()  # O(NlogN), 排序,便于从两头检索
        result = []
        for i in range(len(nums) - 3):
            if i >= 1 and nums[i] == nums[i - 1]: # 跳过重复值
                continue
            if nums[0] + nums[1] + nums[2] + nums[3] > target: # 剪枝, 加速
                break
            if nums[i] + sum(nums[-3:]) < target:  #剪枝,加速, 开头加末尾最大三个数,如果不满足,说明开头元素过小,直接跳过
                continue
            # 转为3sum
            for j in range(i + 1, len(nums) - 2): # j 从 i的下一个数开始
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                if nums[i] + nums[j] + nums[-2] + nums[-1] < target: #开头加末尾最大两个数,如果不满足,说明开头元素过小,直接跳过
                    continue
                if nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target: # 开头最小三个数都比target大,这组不满足,跳过
                    break
                l = j + 1
                r = len(nums) - 1
                while l < r:
                    if nums[i] + nums[j] + nums[l] + nums[r] == target:
                        result.append([nums[i], nums[j], nums[l], nums[r]])
                        while l < r and nums[l] == nums[l + 1]: # 跳过相同的
                            l += 1
                        while l < r and nums[r] == nums[r - 1]: # 跳过相同的
                            r -= 1
                        l += 1
                        r -= 1
                    elif nums[i] + nums[j] + nums[l] + nums[r] < target: # 左边++,调大左值
                        l += 1
                    else: # 左边--,调小右值
                        r -= 1
     return result
     """

    # 采用递归的办法将N SUM削减到2 sum求解
    def fourSum(self, nums, target):
        nums.sort()
        results = []
        self.findNsum(nums, target, 4, [], results)
        return results

    def findNsum(self, nums, target, N, result, results):
        if len(nums) < N or N < 2: return

        # solve 2-sum
        if N == 2:
            l, r = 0, len(nums) - 1
            while l < r:
                if nums[l] + nums[r] == target:
                    results.append(result + [nums[l], nums[r]])
                    l += 1
                    r -= 1
                    while l < r and nums[l] == nums[l - 1]:
                        l += 1
                    while r > l and nums[r] == nums[r + 1]:
                        r -= 1
                elif nums[l] + nums[r] < target:
                    l += 1
                else:
                    r -= 1
        else:
            for i in range(0, len(nums) - N + 1):  # careful about range
                if target < nums[i] * N or target > nums[-1] * N:  # take advantages of sorted list
                    break
                if i == 0 or i > 0 and nums[i - 1] != nums[i]:  # recursively reduce N
                    self.findNsum(nums[i + 1:], target - nums[i], N - 1, result + [nums[i]], results)
        return
