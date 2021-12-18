package main

import (
	"container/heap"
	"fmt"
)

// *********************** 优先队列实现

type NodeQueue []int

// Len required by sort.Interface
func (nq *NodeQueue) Len() int { return len(*nq) }

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
	pq := NewPriorityQueue()
	pq.Add(2)
	pq.Add(3)
	pq.Add(1)
	pq.Add(4)
	//for !pq.IsEmpty() {
		fmt.Println(pq.Peek())
	//}

}
