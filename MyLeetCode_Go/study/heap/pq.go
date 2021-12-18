package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

// 牛客的测试链接：
// https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
// 不要提交包信息，提交下面的代码可以直接通过
// 因为测试平台会卡空间，所以把set换成了动态加和减的结构

// 读取一行输入按空格分割
func read(reader *bufio.Reader) []int {
	line, _, _:= reader.ReadLine()
	list := strings.Split(string(line), " ")
	array := make([]int, len(list))
	for idx, val := range list {
		array[idx], _ = strconv.Atoi(val)
	}
	return array
}

func main() {
	var N, K int
	var arr1, arr2 []int

	reader := bufio.NewReader(os.Stdin)
	array := read(reader)
	N = array[0]
	K = array[1]
	_ = N // N 实际直接从输入长度获取
	arr1 = read(reader)
	arr2 = read(reader)

	topK := topKsum(arr1, arr2, K)
	for i := 0; i < K; i++ {
		fmt.Printf("%v ", topK[i])
	}
	fmt.Println()
}

// 放入大根堆中的结构
type Node struct {
	Index1 int // arr1 中的位置
	Index2 int // arr2 中的位置
	Sum    int // arr1[index1] + arr2[index2]的值
}

func NewNode(i1, i2, s int) *Node {
	return &Node{i1, i2, s}
}

// 生成大根堆的比较器
func MaxHeapComp(a, b interface{}) int {
	// 如果返回负数，认为第一个参数应该排在前面
	// 如果返回正数，认为第二个参数应该排在前面
	// 如果返回0，认为谁放前面无所谓
	o1 := a.(*Node)
	o2 := b.(*Node)
	return o2.Sum - o1.Sum
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
	maxHeap := NewHeapWith(MaxHeapComp)
	set := NewSet()
	i1 := N - 1
	i2 := M - 1
	maxHeap.Push(NewNode(i1, i2, arr1[i1]+arr2[i2]))
	set.Add(x(i1,i2,M))
	for resIndex != topK {
		value,_ := maxHeap.Pop()
		curNode := value.(*Node)
		res[resIndex] = curNode.Sum
		resIndex++
		i1 = curNode.Index1
		i2 = curNode.Index2
		set.Remove(x(i1, i2, M))
		if i1-1>=0&&!set.Contains(x(i1-1,i2,M)) {
			set.Add(x(i1-1,i2,M))
			maxHeap.Push(NewNode(i1-1,i2,arr1[i1 - 1] + arr2[i2]))
		}
		if i2-1>=0&&!set.Contains(x(i1,i2-1,M)) {
			set.Add(x(i1,i2-1,M))
			maxHeap.Push(NewNode(i1,i2-1,arr1[i1] + arr2[i2-1]))
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

// Heap holds elements in an array-list
type Heap struct {
	list       *List
	Comparator Comparator
}

func NewHeapWith(comparator Comparator) *Heap {
	return &Heap{list: NewList(), Comparator: comparator}
}

func (heap *Heap) Push(values ...interface{}) {
	if len(values) == 1 {
		heap.list.Add(values[0])
		heap.bubbleUp()
	} else {
		// Reference: https://en.wikipedia.org/wiki/Binary_heap#Building_a_heap
		for _, value := range values {
			heap.list.Add(value)
		}
		size := heap.list.Size()/2 + 1
		for i := size; i >= 0; i-- {
			heap.bubbleDownIndex(i)
		}
	}
}

func (heap *Heap) Pop() (value interface{}, ok bool) {
	value, ok = heap.list.Get(0)
	if !ok {
		return
	}
	lastIndex := heap.list.Size() - 1
	heap.list.Swap(0, lastIndex)
	heap.list.Remove(lastIndex)
	heap.bubbleDown()
	return
}

func (heap *Heap) Peek() (value interface{}, ok bool) {
	return heap.list.Get(0)
}

func (heap *Heap) Empty() bool {
	return heap.list.Empty()
}

func (heap *Heap) Size() int {
	return heap.list.Size()
}

func (heap *Heap) Clear() {
	heap.list.Clear()
}

func (heap *Heap) bubbleDown() {
	heap.bubbleDownIndex(0)
}

func (heap *Heap) bubbleDownIndex(index int) {
	size := heap.list.Size()
	for leftIndex := index<<1 + 1; leftIndex < size; leftIndex = index<<1 + 1 {
		rightIndex := index<<1 + 2
		smallerIndex := leftIndex
		leftValue, _ := heap.list.Get(leftIndex)
		rightValue, _ := heap.list.Get(rightIndex)
		if rightIndex < size && heap.Comparator(leftValue, rightValue) > 0 {
			smallerIndex = rightIndex
		}
		indexValue, _ := heap.list.Get(index)
		smallerValue, _ := heap.list.Get(smallerIndex)
		if heap.Comparator(indexValue, smallerValue) > 0 {
			heap.list.Swap(index, smallerIndex)
		} else {
			break
		}
		index = smallerIndex
	}
}

func (heap *Heap) bubbleUp() {
	index := heap.list.Size() - 1
	for parentIndex := (index - 1) >> 1; index > 0; parentIndex = (index - 1) >> 1 {
		indexValue, _ := heap.list.Get(index)
		parentValue, _ := heap.list.Get(parentIndex)
		if heap.Comparator(parentValue, indexValue) <= 0 {
			break
		}
		heap.list.Swap(index, parentIndex)
		index = parentIndex
	}
}

// *********************** 比较器

type Comparator func(a, b interface{}) int

// *********************** ArrayList 实现

// List holds the elements in a slice
type List struct {
	elements []interface{}
	size     int
}

const (
	growthFactor = float32(2.0)  // growth by 100%
	shrinkFactor = float32(0.25) // shrink when size is 25% of capacity (0 means never shrink)
)

func NewList(values ...interface{}) *List {
	list := &List{}
	if len(values) > 0 {
		list.Add(values...)
	}
	return list
}

func (list *List) Add(values ...interface{}) {
	list.growBy(len(values))
	for _, value := range values {
		list.elements[list.size] = value
		list.size++
	}
}

func (list *List) Get(index int) (interface{}, bool) {

	if !list.withinRange(index) {
		return nil, false
	}

	return list.elements[index], true
}

func (list *List) Remove(index int) {

	if !list.withinRange(index) {
		return
	}

	list.elements[index] = nil                                    // cleanup reference
	copy(list.elements[index:], list.elements[index+1:list.size]) // shift to the left by one (slow operation, need ways to optimize this)
	list.size--

	list.shrink()
}

func (list *List) Contains(values ...interface{}) bool {

	for _, searchValue := range values {
		found := false
		for _, element := range list.elements {
			if element == searchValue {
				found = true
				break
			}
		}
		if !found {
			return false
		}
	}
	return true
}

func (list *List) Values() []interface{} {
	newElements := make([]interface{}, list.size, list.size)
	copy(newElements, list.elements[:list.size])
	return newElements
}

func (list *List) IndexOf(value interface{}) int {
	if list.size == 0 {
		return -1
	}
	for index, element := range list.elements {
		if element == value {
			return index
		}
	}
	return -1
}

func (list *List) Empty() bool {
	return list.size == 0
}

func (list *List) Size() int {
	return list.size
}

func (list *List) Clear() {
	list.size = 0
	list.elements = []interface{}{}
}

func (list *List) Swap(i, j int) {
	if list.withinRange(i) && list.withinRange(j) {
		list.elements[i], list.elements[j] = list.elements[j], list.elements[i]
	}
}

func (list *List) Insert(index int, values ...interface{}) {

	if !list.withinRange(index) {
		// Append
		if index == list.size {
			list.Add(values...)
		}
		return
	}

	l := len(values)
	list.growBy(l)
	list.size += l
	copy(list.elements[index+l:], list.elements[index:list.size-l])
	copy(list.elements[index:], values)
}

func (list *List) Set(index int, value interface{}) {

	if !list.withinRange(index) {
		// Append
		if index == list.size {
			list.Add(value)
		}
		return
	}

	list.elements[index] = value
}

func (list *List) withinRange(index int) bool {
	return index >= 0 && index < list.size
}

func (list *List) resize(cap int) {
	newElements := make([]interface{}, cap, cap)
	copy(newElements, list.elements)
	list.elements = newElements
}

func (list *List) growBy(n int) {
	// When capacity is reached, grow by a factor of growthFactor and add number of elements
	currentCapacity := cap(list.elements)
	if list.size+n >= currentCapacity {
		newCapacity := int(growthFactor * float32(currentCapacity+n))
		list.resize(newCapacity)
	}
}

func (list *List) shrink() {
	if shrinkFactor == 0.0 {
		return
	}
	// Shrink when size is at shrinkFactor * capacity
	currentCapacity := cap(list.elements)
	if list.size <= int(float32(currentCapacity)*shrinkFactor) {
		list.resize(list.size)
	}
}