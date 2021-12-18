package main

import (
	"container/heap"
	"fmt"
)

func topKFrequent(nums []int, k int) []int {

	hashMap := make(map[int]int)
	for _, num := range nums {
		if _,ok := hashMap[num];!ok{
			hashMap[num] = 1
		} else {
			hashMap[num]++
		}
	}
	heapq := &MinHeap{}
	heap.Init(heapq)
	for num, node := range hashMap {
		if heapq.Len() < k || heapq.Len() == k && node > heapq.Peek().([2]int)[1] {
			heap.Push(heapq, [2]int{num, node})
		}
		if heapq.Len() > k {
			heap.Pop(heapq)
		}
	}
	ans := make([]int, k)
	for index := 0;!heapq.IsEmpty();index++ {
		ans[index] = heap.Pop(heapq).([2]int)[0]
	}
	return ans
}

// 小根堆的简化定义方式
type MinHeap [][2]int

func (mh MinHeap) Len() int           { return len(mh) }
func (mh MinHeap) Less(i, j int) bool { return mh[i][1] < mh[j][1] }
func (mh MinHeap) Swap(i, j int)      { mh[i], mh[j] = mh[j], mh[i] }

func (mh *MinHeap) Push(x interface{}) {
	*mh = append(*mh, x.([2]int))
}

func (mh *MinHeap) Pop() interface{} {
	n := len(*mh)
	node := (*mh)[n-1]
	*mh = (*mh)[:n-1]
	return node
}

func (mh *MinHeap) Peek()  interface{} {
	return (*mh)[0]
}

func (mh *MinHeap) IsEmpty() bool {
	return mh.Len() == 0
}

func main() {
    nums := []int{1,1,1,2,2,3}
	k :=2
	ans := topKFrequent(nums, k)
	fmt.Println(ans)
}
