package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"math/rand"
	"os"
	"sort"
	"strconv"
	"strings"
	"time"
)

// 牛客的测试链接：
// https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
// 不要提交包信息，提交下面的代码可以直接通过
// 因为测试平台会卡空间，所以把set换成了动态加和减的结构

// 读取一行输入按空格分割
func read(reader *bufio.Reader) []int {
	line, _ := reader.ReadString('\n')// 每次读取一行
	line = strings.TrimSpace(line)
	list := strings.Split(string(line), " ")
	array := make([]int, len(list))
	for idx, val := range list {
		array[idx], _ = strconv.Atoi(val)
	}
	return array
}


// 比较器
type Comparator func(a, b interface{}) int

// 生成大根堆的比较器
func MaxHeapComp(a, b interface{}) int {
	o1 := a.(*Node)
	o2 := b.(*Node)
	return o1.Sum - o2.Sum
}

// 放入大根堆中的结构
type Node struct {
	Index1     int        // arr1 中的位置
	Index2     int        // arr2 中的位置
	Sum        int        // arr1[index1] + arr2[index2]的值
	comparator Comparator // 比较器
}

// 自定义比较器
func NewNodeWith(i1, i2, s int, comp Comparator) *Node {
	return &Node{i1, i2, s, comp}
}

func topKsum(arr1 []int, arr2 []int, topK int) []int {
	if arr1 == nil || arr2 == nil || topK < 1 {
		return nil
	}
	N := len(arr1)
	M := len(arr2)
	topK = min(topK, N*M)
	res := make([]int, topK)
	resIndex := 0
	maxHeap := NewPriorityQueue()
	set := NewSet()
	i1 := N - 1
	i2 := M - 1
	maxHeap.Add(NewNodeWith(i1, i2, arr1[i1]+arr2[i2], MaxHeapComp))
	set.Add(x(i1, i2, M))
	for resIndex != topK {
		curNode := (maxHeap.Poll()).(*Node)
		res[resIndex] = curNode.Sum
		resIndex++
		i1 = curNode.Index1
		i2 = curNode.Index2
		//set.Remove(x(i1, i2, M))
		if i1-1 >= 0 && !set.Contains(x(i1-1, i2, M)) {
			set.Add(x(i1-1, i2, M))
			maxHeap.Add(NewNodeWith(i1-1, i2, arr1[i1-1]+arr2[i2], MaxHeapComp))
		}
		if i2-1 >= 0 && !set.Contains(x(i1, i2-1, M)) {
			set.Add(x(i1, i2-1, M))
			maxHeap.Add(NewNodeWith(i1, i2-1, arr1[i1]+arr2[i2-1], MaxHeapComp))
		}
	}
	return res
}

func x(i1, i2, M int) int {
	return i1*M + i2
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

// *********************** HashSet 实现
type Set struct {
	items map[interface{}]struct{}
}

var itemExists = struct{}{}

func NewSet(values ...interface{}) *Set {
	set := &Set{items: make(map[interface{}]struct{})}
	if len(values) > 0 {
		set.Add(values...)
	}
	return set
}

func (set *Set) Add(items ...interface{}) {
	for _, item := range items {
		set.items[item] = itemExists
	}
}

func (set *Set) Remove(items ...interface{}) {
	for _, item := range items {
		delete(set.items, item)
	}
}

func (set *Set) Contains(items ...interface{}) bool {
	for _, item := range items {
		if _, contains := set.items[item]; !contains {
			return false
		}
	}
	return true
}

func (set *Set) Empty() bool {
	return set.Size() == 0
}

func (set *Set) Size() int {
	return len(set.items)
}

func (set *Set) Clear() {
	set.items = make(map[interface{}]struct{})
}

// *********************** 优先队列实现
type PriorityQueue []*Node

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

// 使用下面三组函数更方便
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

func RandomArray(l, maxValue int) (arr []int) {
	rand.Seed(time.Now().UnixNano())
	arr = make([]int, l)
	for i := 0; i < l; i++ {
		arr[i] = rand.Intn(maxValue)
	}
	sort.Ints(arr)
	return
}

func isEqual(arr1, arr2 []int) bool {
	if (arr1 == nil && arr2 != nil) || (arr1 != nil && arr2 == nil) {
		return false
	}
	if arr1 == nil && arr2 == nil {
		return true
	}
	if len(arr1) != len(arr2) {
		return false
	}
	for i := 0; i < len(arr1); i++ {
		if arr1[i] != arr2[i] {
			return false
		}
	}
	return true
}

func main() {

	fi, err := os.Open("/Users/xtray/Desktop/dev/workbench/gotest/study/verify/input.data")
	if err != nil {
		fmt.Printf("Error: %s\n", err)
		return
	}
	defer fi.Close()

	var N, K int
	var arr1, arr2 []int

	reader := bufio.NewReader(fi)
	array := read(reader)
	N = array[0]
	K = array[1]
	_ = N // N 实际直接从输入长度获取
	arr1 = read(reader)
	arr2 = read(reader)



	//var N, K int
	//var arr1, arr2 []int

	//reader := bufio.NewReader(os.Stdin)
	//array := read(reader)
	//N = array[0]
	//K = array[1]
	//_ = N // N 实际直接从输入长度获取
	//arr1 = read(reader)
	//arr2 = read(reader)

	//var N, K int
	//var arr1, arr2 []int
	//_ = N // N 实际直接从输入长度获取
	//K = 1
	//arr1 = []int{3, 5}
	//arr2 = []int{0, 1}
	//sort.Ints(arr1)
	//sort.Ints(arr2)
	//
	topK1 := topKsum(arr1, arr2, K)
	for i := 0; i < K; i++ {
		fmt.Printf("%v ", topK1[i])
	}
	fmt.Println()
	topK2 := TopK(arr1, arr2, K)
	for i := 0; i < K; i++ {
		fmt.Printf("%v ", topK2[i])
	}
	fmt.Println()

	// 对数器
	//var arr1, arr2 []int
	//
	//times := 1
	//succeed := true
	//for i:=0; i<times;i++ {
	//	//maxVal := 100000
	//	//maxLen := rand.Intn(maxVal) + 2
	//	//k := rand.Intn(maxLen*maxLen)
	//	//for k == 0 {
	//	//	k = rand.Intn(maxLen*maxLen)
	//	//}
	//	//
	//	//length := maxLen
	//	//val :=maxVal
	//
	//	//length := 99999
	//	//val :=100
	//	k:=26186
	//
	//	//arr1 = RandomArray(length, val)
	//	//arr2 = RandomArray(length, val)
	//	arr1 = []int{}
	//	arr2 = []int{}
	//	ans1:= topKsum(arr1,arr2, k)
	//	ans2:= TopK(arr1,arr2, k)
	//	if !isEqual(ans1, ans2) {
	//		succeed = false
	//		break
	//	}
	//}
	//if succeed {
	//	fmt.Println("Nice!")
	//} else {
	//	fmt.Println("Fucking fucked!")
	//}
}


func TopK(arr1 []int, arr2 []int, k int) []int {
	N := len(arr1)
	M := len(arr2)
	k = min(k, N*M)
	res := make([]int, k)
	resIndex := 0

	n:= N

	var maxheap = &MaxHeap{}

	heap.Push(maxheap, [3]int{arr1[n-1] + arr2[n-1], n - 1, n - 1})
	m := map[[2]int]bool{[2]int{n - 1, n - 1}: true}
	for i := 0; i < k; i++ {
		temp := heap.Pop(maxheap).([3]int)
		//fmt.Printf("%d ", temp[0])
		res[resIndex] = temp[0]
		resIndex++
		if temp[1] - 1 >= 0 && m[[2]int{temp[1] - 1, temp[2]}] != true {
			heap.Push(maxheap, [3]int{arr1[temp[1]-1] + arr2[temp[2]], temp[1] - 1, temp[2]})
			m[[2]int{temp[1] - 1, temp[2]}] = true
		}
		if temp[2]-1 >= 0 && m[[2]int{temp[1], temp[2] - 1}] != true {
			heap.Push(maxheap, [3]int{arr1[temp[1]] + arr2[temp[2]-1], temp[1], temp[2] - 1})
			m[[2]int{temp[1], temp[2] - 1}] = true
		}
	}
	return res
}



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
