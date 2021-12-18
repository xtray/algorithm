package main
//
//func distributeCandies(candyType []int) int {
//	if candyType == nil || len(candyType) == 0 {
//		return 0
//	}
//
//	// 利用 map 的 key 唯一性当 set 使用
//	//set := map[int]bool{}
//	candyMap := map[int]int{}
//	for i:=0;i< len(candyType);i++ {
//		if _, ok := candyMap[i]; !ok {
//			candyMap[i] = 1
//		} else {
//			candyMap[i]++
//		}
//	}
//
//	return process(candyType, 0, candyMap, len(candyType)/2)
//}
//
//
//// 当前来到 i 位置分配, 之前0~i-1已经分配完了
//// rest: 待分给妹妹剩余糖果数
//// 返回: 最大种类
//func process(candyType []int, index int, candyMap map[int]int, rest int) int {
//	if index == len(candyType) {
//		return 0
//	}
//	// index < len
//
//	// 当前糖果分给妹妹
//	p1 := -1
//	nextA := process(candyType, index+1, )
//
//}
