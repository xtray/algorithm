package main

import (
	"container/heap"
	"fmt"
)

// 来自网易
// map[i][j] == 0，代表(i,j)是海洋，渡过的话代价是2
// map[i][j] == 1，代表(i,j)是陆地，渡过的话代价是1
// map[i][j] == 2，代表(i,j)是障碍，无法渡过
// 每一步上、下、左、右都能走，返回从左上角走到右下角最小代价是多少，如果无法到达返回-1
func minCost(Map [][]int) int {
	if Map[0][0] == 2 {
		return -1
	}
	n := len(Map)
	m := len(Map[0])
	heap1 := NewPriorityQueue()
	visited := make([][]bool, n)
	for i := range visited {
		visited[i] = make([]bool, m)
	}
	add(Map, 0, 0, 0, heap1, visited)
	for !heap1.IsEmpty() {
		cur := heap1.Poll().(*Node)
		if cur.Row == n-1 && cur.Col == m-1 {
			return cur.Cost
		}
		add(Map, cur.Row-1, cur.Col, cur.Cost, heap1, visited)
		add(Map, cur.Row+1, cur.Col, cur.Cost, heap1, visited)
		add(Map, cur.Row, cur.Col-1, cur.Cost, heap1, visited)
		add(Map, cur.Row, cur.Col+1, cur.Cost, heap1, visited)
	}
	return -1
}

func add(m [][]int, i int, j int, pre int, heap *PriorityQueue, visited [][]bool) {
	if i >= 0 && i < len(m) && j >= 0 && j < len(m[0]) && m[i][j] != 2 && !visited[i][j] {
		var node *Node
		if m[i][j] == 0 {
			node = NewNodeWith(i, j, pre+2)
		} else {
			node = NewNodeWith(i, j, pre+1)
		}
		heap.Add(node)
		visited[i][j] = true
	}
}

type Node struct {
	Row        int
	Col        int
	Cost       int
	index      int
}

func NewNodeWith(a, b, c int) *Node {
	return &Node{Row: a, Col: b, Cost: c}
}

// *********************** 优先队列实现

type NodeQueue []*Node

// Len required by sort.Interface
func (nq *NodeQueue) Len() int { return len(*nq) }

// Less required by sort.Interface
func (nq NodeQueue) Less(i, j int) bool {
	return nq[i].Cost < nq[j].Cost
}

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
	node.Cost = priority
	heap.Fix(&pq.nodeQueue, node.index)
}

func (pq *PriorityQueue) Size() int {
	return pq.nodeQueue.Len()
}

func (pq *PriorityQueue) IsEmpty() bool {
	return pq.nodeQueue.Len() == 0
}

func main() {
	Map := [][]int{{0,2,0,2},{0,1,0,0},{0,0,2,0},{0,1,1,1}}
	ans := minCost(Map)
	fmt.Print(ans)
}