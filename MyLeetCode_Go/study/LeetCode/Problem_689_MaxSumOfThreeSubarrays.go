package main

import "fmt"

func maxSumOfThreeSubarrays(nums []int, k int) []int {
	N := len(nums)
	ranges := make([]int, N)
	left := make([]int, N)
	sum := 0
	for i := 0; i < k; i++ {
		sum += nums[i]
	}
	ranges[0] = sum
	left[k-1] = 0 // 0~i 上随便选k 个最大的开始位置
	maxVal := sum
	for i := k; i < N; i++ {
		sum = sum - nums[i-k] + nums[i]
		ranges[i-k+1] = sum
		left[i] = left[i-1]
		if sum > maxVal {
			maxVal = sum
			left[i] = i - k + 1 // 取得最大值的最左边位置
		}
	}
	sum = 0
	for i := N - 1; i >= N-k; i-- {
		sum += nums[i]
	}
	maxVal = sum
	right := make([]int, N)
	right[N-k] = N - k
	for i := N - k - 1; i >= 0; i-- {
		sum = sum - nums[i+k] + nums[i]
		right[i] = right[i+1]
		if sum >= maxVal {
			maxVal = sum
			right[i] = i
		}
	}
	a := 0
	b := 0
	c := 0
	maxVal = 0
	for i := k; i < N-2*k+1; i++ { // 中间一块的起始点 (0...k-1)选不了 i == N-1
		part1 := ranges[left[i-1]]
		part2 := ranges[i]
		part3 := ranges[right[i+k]]
		if part1+part2+part3 > maxVal {
			maxVal = part1 + part2 + part3
			a = left[i-1]
			b = i
			c = right[i+k]
		}
	}
	return []int{a, b, c}
}

func main() {
	nums := []int{1,2,1,2,1,2,1,2,1}
	k := 2
	ans := maxSumOfThreeSubarrays(nums, k)
	fmt.Println(ans)

}