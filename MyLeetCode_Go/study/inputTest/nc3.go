package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func TopK(arr1 []int, arr2 []int, n, k int) {
	heap.Push(maxheap, [3]int{arr1[n-1] + arr2[n-1], n - 1, n - 1})
	m := map[[2]int]bool{[2]int{n - 1, n - 1}: true}
	for i := 0; i < k; i++ {
		temp := heap.Pop(maxheap).([3]int)
		fmt.Printf("%d ", temp[0])
		if m[[2]int{temp[1] - 1, temp[2]}] != true {
			heap.Push(maxheap, [3]int{arr1[temp[1]-1] + arr2[temp[2]], temp[1] - 1, temp[2]})
			m[[2]int{temp[1] - 1, temp[2]}] = true
		}
		if m[[2]int{temp[1], temp[2] - 1}] != true {
			heap.Push(maxheap, [3]int{arr1[temp[1]] + arr2[temp[2]-1], temp[1], temp[2] - 1})
			m[[2]int{temp[1], temp[2] - 1}] = true
		}
	}
}

var maxheap = &MaxHeap{}

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

func main() {
	reader := bufio.NewReader(os.Stdin)

	// 读一行，去掉最后的换行后按照空格分割
	str, _ := reader.ReadString('\n')
	str = str[:len(str)-1]
	strs := strings.Split(str, " ")

	// 将strs中的字符串转为整数后传入
	n, _ := strconv.Atoi(strs[0])
	k, _ := strconv.Atoi(strs[1])

	arr1 := make([]int, n)
	arr2 := make([]int, n)

	str, _ = reader.ReadString('\n')
	str = str[:len(str)-1]
	strs = strings.Split(str, " ")

	for i := 0; i < n; i++ {
		arr1[i], _ = strconv.Atoi(strs[i])
	}

	str, _ = reader.ReadString('\n')
	str = str[:len(str)-1]
	strs = strings.Split(str, " ")

	for i := 0; i < n; i++ {
		arr2[i], _ = strconv.Atoi(strs[i])
	}

	sort.Ints(arr1)
	sort.Ints(arr2)

	TopK(arr1, arr2, n, k)
}
