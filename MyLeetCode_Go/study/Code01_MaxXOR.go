package main

import (
	"fmt"
	"math"
	"math/rand"
)


// O(N^2)
func maxXorSubarray1(arr []int) (mx int) {
	if arr == nil || len(arr) == 0 {
		return 0
	}
	// 准备一个前缀异或和数组eor
	// eor[i] = arr[0...i]的异或结果
	eor := make([]int, len(arr))
	eor[0] = arr[0]
	// 生成eor数组，eor[i]代表arr[0..i]的异或和
	for i := 1; i < len(arr); i++ {
		eor[i] = eor[i-1] ^ arr[i]
	}
	mx = math.MinInt32
	for j := 0; j < len(arr); j++ {
		for i := 0; i < len(arr); i++ {
			if i == 0 {
				mx = max(mx, eor[j])
			} else {
				mx = max(mx, eor[j]^eor[i-1])
			}
		}
	}
	return
}

// 前缀树的Node结构
// nexts[0] -> 0方向的路
// nexts[1] -> 1方向的路
// nexts[0] == null 0方向上没路！
// nexts[0] != null 0方向有路，可以跳下一个节点
// nexts[1] == null 1方向上没路！
// nexts[1] != null 1方向有路，可以跳下一个节点
type Node struct {
	Nexts []*Node
}

// 基于本题，定制前缀树的实现
type NumTrie struct {
	// 头节点
	Head *Node
}

func InitNode() (res *Node) {
	lst := []*Node{nil, nil}
	res = &Node{
		lst,
	}
	return
}

func InitNumTrie() *NumTrie {
	return &NumTrie{InitNode()}
}

func (nt *NumTrie) add(newNum int) {
	cur := nt.Head
	for move := 31; move >= 0; move-- {
		path := (newNum >> move) & 1
		if cur.Nexts[path] == nil {
			cur.Nexts[path] = InitNode()
		}
		cur = cur.Nexts[path]
	}
}

// 该结构之前收集了一票数字，并且建好了前缀树
// num和 谁 ^ 最大的结果（把结果返回）
func (nt *NumTrie) MaxXor(num int) (ans int) {
	cur := nt.Head
	for move := 31; move >= 0; move-- {
		// 取出num中第move位的状态，path只有两种值0就1，整数
		path, best := (num>>move)&1, 0
		// 期待遇到的东西
		if move == 31 {
			best = path
		} else {
			best = path ^ 1
		}
		// 实际遇到的东西
		if cur.Nexts[best] == nil {
			best = best ^ 1
		}
		// (path ^ best) 当前位位异或完的结果
		ans |= (path ^ best) << move
		cur = cur.Nexts[best]
	}
	return
}

// O(N)
func MaxXorSubarray2(arr []int) (mx int) {
	if arr == nil || len(arr) == 0 {
		return 0
	}
	mx = math.MinInt32
	// 0~i整体异或和
	xor := 0
	numTrie := InitNumTrie()
	numTrie.add(0)
	for i := 0; i < len(arr); i++ {
		xor ^= arr[i]
		mx = max(mx, numTrie.MaxXor(xor))
		numTrie.add(xor)
	}
	return
}

// for test
func generateRandomArray(maxSize, maxValue int) []int {
	arr := make([]int, rand.Intn(maxSize+1))
	for i := 0; i < len(arr); i++ {
		arr[i] = rand.Intn(maxValue+1) - rand.Intn(maxValue)
	}
	return arr
}

// for test
func printArray(arr []int) {
	if arr == nil {
		return
	}
	for i := 0; i < len(arr); i++ {
		fmt.Print(fmt.Sprintf("%d ", arr[i]))
	}
	fmt.Println()
}

// for test
//func main() {
//	// 随机种子
//	rand.Seed(time.Now().UnixNano())
//	testTime, maxSize, maxValue, succeed := 500000, 30, 50, true
//	for i := 0; i < testTime; i++ {
//		arr := generateRandomArray(maxSize, maxValue)
//		comp, res := maxXorSubarray1(arr), MaxXorSubarray2(arr)
//		if res != comp {
//			succeed = false
//			printArray(arr)
//			fmt.Println(res)
//			fmt.Println(comp)
//			break
//		}
//	}
//	if succeed {
//		fmt.Println("Nice!")
//	} else {
//		fmt.Println("Fucking fucked!")
//	}
//}

func main() {
	arr := []int{
		-2, 2, 11, 8, -24, 0, 29, -11, 6, 41, 22, -31,
	}
	comp, res := maxXorSubarray1(arr), MaxXorSubarray2(arr)
	fmt.Println(comp)
	fmt.Println(res)
}

func max(a ...int) (res int) {
	res = math.MaxInt
	for _, v := range a {
		if v > res {
			res = v
		}
	}
	return
}

