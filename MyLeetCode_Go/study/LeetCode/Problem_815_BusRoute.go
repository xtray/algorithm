package main

import "fmt"

// 来自三七互娱
// Leetcode原题 : https://leetcode.com/problems/bus-routes/

// 0 : [1,3,7,0]
// 1 : [7,9,6,2]
// ....
// 返回：返回换乘几次+1 -> 返回一共坐了多少条线的公交。
func numBusesToDestination(routes [][]int, source int, target int) int {
	if source == target {
		return 0
	}
	n := len(routes)
	// key : 车站
	// value : list -> 该车站拥有哪些线路！
	hashMap := map[int][]int{}
	for i := 0; i < n; i++ {
		for j := 0; j < len(routes[i]); j++ {
			if _, ok := hashMap[routes[i][j]]; !ok {
				hashMap[routes[i][j]] = []int{}
			}
			list := hashMap[routes[i][j]]
			list = append(list, i)
			hashMap[routes[i][j]] = list
		}
	}
	var queue []int
	set := make([]bool, n)
	for _, route := range hashMap[source] {
		queue = append(queue, route)
		set[route] = true
	}
	length := 1
	for len(queue) != 0 {
		var nextLevel []int
		for _, route := range queue {
			bus := routes[route]
			for _, station := range bus {
				if station == target {
					return length
				}
				for _, nextRoute := range hashMap[station] {
					if !set[nextRoute] {
						nextLevel = append(nextLevel, nextRoute)
						set[nextRoute] = true
					}
				}
			}
		}
		queue = nextLevel
		length++
	}
	return -1
}

func main() {

	routes := [][]int{{1, 2, 7}, {3, 6, 7}}
	source := 1
	target := 6
	res := numBusesToDestination(routes, source, target)
	fmt.Println(res)

}
