package main

import (
	"container/heap"
	"fmt"
)

type MedianFinder struct {
	maxh *PriorityQueue
	minh *PriorityQueue
}

func Constructor() MedianFinder {
	return MedianFinder{NewPriorityQueue(), NewPriorityQueue()}
}

func (mf *MedianFinder) AddNum(num int) {
	if mf.maxh.IsEmpty() || -mf.maxh.Peek() >= num {
		mf.maxh.Add(-num)
	} else {
		mf.minh.Add(num)
	}
	mf.balance()
}

func (mf *MedianFinder) FindMedian() float64 {
	if mf.maxh.Size() == mf.minh.Size() {
		return float64(-mf.maxh.Peek()+mf.minh.Peek()) / 2
	} else {
		if mf.maxh.Size() > mf.minh.Size() {
			return float64(-mf.maxh.Peek())
		} else {
			return float64(mf.minh.Peek())
		}
	}
}

func (mf *MedianFinder) balance() {
	if abs(mf.maxh.Size()-mf.minh.Size()) == 2 {
		if mf.maxh.Size() > mf.minh.Size() {
			mf.minh.Add(-mf.maxh.Poll().(int))
		} else {
			mf.maxh.Add(-mf.minh.Poll().(int))
		}
	}
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}

// *********************** 优先队列实现

type NodeQueue []int

// Len required by sort.Interface
func (nq NodeQueue) Len() int { return len(nq) }

// Less required by sort.Interface
func (nq NodeQueue) Less(i, j int) bool { return nq[i] < nq[j] }

// Swap required by sort.Interface
func (nq NodeQueue) Swap(i, j int) { nq[i], nq[j] = nq[j], nq[i] }

// Push required by the heap.Interface
func (nq *NodeQueue) Push(x interface{}) {
	*nq = append(*nq, x.(int))
}

// Pop required by the heap.Interface
func (nq *NodeQueue) Pop() interface{} {
	n := len(*nq)
	node := (*nq)[n-1]
	*nq = (*nq)[:n-1]
	return node
}

func (nq *NodeQueue) Peek() interface{} {
	return (*nq)[0]
}

type PriorityQueue struct {
	nodeQueue NodeQueue
}

func NewPriorityQueue() *PriorityQueue {
	pq := &PriorityQueue{nodeQueue: make(NodeQueue, 0)}
	heap.Init(&pq.nodeQueue)
	return pq
}

// Add new node to priority queue
func (pq *PriorityQueue) Add(x interface{}) {
	heap.Push(&pq.nodeQueue, x.(int))
}

// Poll pop node from priority queue according to the order
func (pq *PriorityQueue) Poll() interface{} {
	if pq.nodeQueue.Len() == 0 {
		return nil
	}
	return heap.Pop(&pq.nodeQueue)
}

func (pq *PriorityQueue) Peek() int {
	return pq.nodeQueue.Peek().(int)
}

func (pq *PriorityQueue) Size() int {
	return pq.nodeQueue.Len()
}

func (pq *PriorityQueue) IsEmpty() bool {
	return pq.nodeQueue.Len() == 0
}

func main() {
	mf := Constructor()
	mf.AddNum(1)
	ans := mf.FindMedian()
	fmt.Println(ans)

	mf.AddNum(2)
	ans = mf.FindMedian()
	fmt.Println(ans)

	mf.AddNum(3)
	ans = mf.FindMedian()
	fmt.Println(ans)

	mf.AddNum(4)
	ans = mf.FindMedian()
	fmt.Println(ans)

	//mf.AddNum(5)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
	//
	//mf.AddNum(6)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
	//mf.AddNum(7)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
	//mf.AddNum(8)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
	//mf.AddNum(9)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
	//mf.AddNum(10)
	//ans = mf.FindMedian()
	//fmt.Println(ans)
}
