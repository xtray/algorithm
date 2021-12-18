package main

import (
	"container/heap"
	"fmt"
)

type Comparator func(a, b interface{}) int

func IntComparator(a, b interface{}) int {
	o1 := a.(int)
	o2 := b.(int)
	return o1 - o2
	//switch {
	//case o1 > o2:
	//	return 1
	//case o1 < o2:
	//	return -1
	//default:
	//	return 0
	//}
}

type Node struct {
	Index1 int // arr1 中的位置
	Index2 int // arr2 中的位置
	Sum    int // arr1[index1] + arr2[index2]的值
	comparator Comparator // 比较器
}

// NewNode 使用默认的 int 比较器
func NewNode(i1, i2, s int) *Node {
	return &Node{i1, i2, s, IntComparator}
}

// 自定义比较器
func NewNodeWith(i1, i2, s int, comp Comparator) *Node {
	return &Node{i1, i2, s, comp}
}

type PriorityQueue []*Node

// 生成大根堆的比较器
func MaxHeapComp(a, b interface{}) int {
	o1 := a.(*Node)
	o2 := b.(*Node)
	return o2.Sum - o1.Sum
}

func (pq PriorityQueue) Len() int           { return len(pq) }
func (pq PriorityQueue) Less(i, j int) bool { return pq[i].comparator(pq[i], pq[j]) > 0 }
func (pq PriorityQueue) Swap(i, j int)      { pq[i], pq[j] = pq[j], pq[i] }

func (pq *PriorityQueue) Push(x interface{}) {
	item := x.(*Node)
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	*pq = old[0 : n-1]
	return item
}

func NewPriorityQueue() *PriorityQueue {
	pq := PriorityQueue{}
	heap.Init(&pq)
	return &pq
}

func (pq *PriorityQueue) Add(x interface{}) {
	heap.Push(pq, x)
}
func (pq *PriorityQueue) Poll() interface{} {
	return heap.Pop(pq)
}

func main() {

	arr1 := []int{1, 2, 3, 4}
	arr2 := []int{5, 6, 7, 8}
	N := len(arr1)
	M := len(arr2)
	i1 := N - 1
	i2 := M - 1
	node := NewNodeWith(i1, i2, arr1[i1]+arr2[i2], MaxHeapComp)
	//pq := make(PriorityQueue, 1)
	pq := NewPriorityQueue();
	pq.Add(node)
	pq.Add(NewNodeWith(1, 1, 15, MaxHeapComp))
	pq.Add(NewNodeWith(1, 3, 4, MaxHeapComp))
	for pq.Len() > 0 {
		item := pq.Poll()
		node := item.(* Node)
		fmt.Println(*node)
	}

}
