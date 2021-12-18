package main

// 实现大根堆结构
type MaxHeap [][3]int

func (m *MaxHeap) Len() int           { return len(*m) }
func (m *MaxHeap) Less(i, j int) bool { return (*m)[i][0] > (*m)[j][0] }
func (m *MaxHeap) Swap(i, j int)      { (*m)[i], (*m)[j] = (*m)[j], (*m)[i] }

func (m *MaxHeap) Push(e interface{}) {
	*m = append(*m, e.([3]int))
}

func (m *MaxHeap) Pop() interface{} {
	n := m.Len()
	v := (*m)[n-1]
	*m = (*m)[:n-1]
	return v
}
