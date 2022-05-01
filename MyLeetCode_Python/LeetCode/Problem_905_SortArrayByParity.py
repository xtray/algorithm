class Solution(object):
    def sortArrayByParity(self, nums):
        n = len(nums)
        even, odd = -1, n
        cur = 0
        while cur < odd:
            if (nums[cur] & 1) != 0:
                odd -= 1
                nums[cur], nums[odd] = nums[odd], nums[cur]
            else:
                even += 1
                nums[cur], nums[even] = nums[even], nums[cur]
                cur += 1
        return nums
