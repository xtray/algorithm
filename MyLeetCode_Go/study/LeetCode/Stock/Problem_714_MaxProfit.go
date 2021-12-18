package main

// 买入时扣费
func maxProfit(arr []int, fee int) int {
	if arr == nil || len(arr) == 0 {
		return 0
	}
	N := len(arr)
	// 0..0   0 -[0] - fee
	bestbuy := -arr[0] - fee
	// 0..0  卖  0
	bestsell := 0
	for i := 1; i < N; i++ {
		// 来到i位置了！
		// 如果在i必须买  收入 - 批发价 - fee
		curbuy := bestsell - arr[i] - fee
		// 如果在i必须卖  整体最优（收入 - 良好批发价 - fee）
		cursell := bestbuy + arr[i]
		bestbuy = max(bestbuy, curbuy)
		bestsell = max(bestsell, cursell)
	}
	return bestsell
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

// 卖出是扣费, 注意 0 笔卖出时,实际不扣费
func maxProfit2(arr []int, fee int) int {
	if arr == nil || len(arr) == 0 {
		return 0
	}
	N := len(arr)
	// 0..0   0 -[0] - fee
	bestbuy := -arr[0]
	// 0..0  卖  0
	bestsell := 0
	for i := 1; i < N; i++ {
		// 来到i位置了！
		// 如果在i必须买  收入 - 批发价 - fee
		curbuy := bestsell - arr[i]
		// 如果在i必须卖  整体最优（收入 - 良好批发价 - fee）
		cursell := bestbuy + arr[i] - fee
		bestbuy = max(bestbuy, curbuy)
		bestsell = max(bestsell, cursell)
	}
	return bestsell
}

