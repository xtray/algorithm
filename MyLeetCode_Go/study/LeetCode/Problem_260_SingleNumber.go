package main

func singleNumber(nums []int) []int {
	if nums == nil || len(nums) == 0 {
		return []int{}
	}
	var xor = 0
	for _, num := range nums {
		xor ^= num
	}
	// 提取最右侧的 1, 将数字分成两类
	mostRightOne := xor & (^xor + 1)
	var oneNum = 0
	for _, num := range nums {
		// 找出带 1 的哪一类数字
		if num&mostRightOne != 0 {
			oneNum ^= num
		}
	}
	return []int{oneNum, xor ^ oneNum}
}
