package main

import (
	"container/heap"
	"fmt"
)

// Node defines the item saved in priority queue
type Node struct {
	name  string
	val   int
	index int
}

func NewNode(n string, v int) *Node {
	return &Node{name: n, val: v}
}

func main() {
	pq := NewPriorityQueue()
	pq.Add(NewNode("A", 10))
	pq.Add(NewNode("B", 20))
	pq.Add(NewNode("C", 17))
	pq.Add(NewNode("X", 55))
	pq.Add(NewNode("E", 5))
	pq.Add(NewNode("D", 33))

	test := NewNode("F", 100)
	pq.Add(test)
	pq.Update(test, 1)
	for !pq.IsEmpty() {
		node := pq.Poll().(*Node)
		fmt.Printf("value:%v, name:%v\n", node.val, node.name)
	}
}

// *********************** 优先队列实现

type NodeQueue []*Node

// Len required by sort.Interface
func (nq *NodeQueue) Len() int { return len(*nq) }

// Less required by sort.Interface
func (nq NodeQueue) Less(i, j int) bool {
	return nq[i].val < nq[j].val
}
//func (nq NodeQueue) Less(i, j int) bool { return nq[i].comparator(nq[i], nq[j]) < 0 }

// Swap required by sort.Interface
func (nq NodeQueue) Swap(i, j int) {
	nq[i], nq[j] = nq[j], nq[i]
	nq[i].index = i
	nq[j].index = j
}

// Push required by the heap.Interface
func (nq *NodeQueue) Push(x interface{}) {
	node := x.(*Node)
	node.index = len(*nq)
	*nq = append(*nq, node)
}

// Pop required by the heap.Interface
func (nq *NodeQueue) Pop() interface{} {
	n := len(*nq)
	node := (*nq)[n-1]
	node.index = -1 // required by the heap
	*nq = (*nq)[:n-1]
	return node
}

type PriorityQueue struct {
	nodeQueue NodeQueue
}

func NewPriorityQueue() *PriorityQueue {
	pq := &PriorityQueue{nodeQueue: make(NodeQueue,0)} 
	heap.Init(&pq.nodeQueue)
	return pq
}

// Add new node to priority queue
func (pq *PriorityQueue) Add(x interface{}) {
	heap.Push(&pq.nodeQueue, x.(* Node))
}

// Poll pop node from priority queue according to the order
func (pq *PriorityQueue) Poll() interface{} {
	if pq.nodeQueue.Len() == 0 {
		return nil
	}
	return heap.Pop(&pq.nodeQueue)
}

//Update restore the priority queue when priority changes
func (pq *PriorityQueue) Update(node *Node, priority int) {
	node.val = priority
	heap.Fix(&pq.nodeQueue, node.index)
}

func (pq *PriorityQueue) Size() int {
	return pq.nodeQueue.Len()
}

func (pq *PriorityQueue) IsEmpty() bool {
	return pq.nodeQueue.Len() == 0
}
