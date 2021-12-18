package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

// https://leetcode.com/problems/subtree-of-another-tree/

type Node struct {
	Value int
	Left  *Node
	Right *Node
}

func NewNode(value int) *Node {
	return &Node{Value: value}
}

// big做头节点的树，其中是否有某棵子树的结构，是和small为头的树，完全一样的
func containsTree1(big *Node, small *Node) bool {
	if small == nil {
		return true
	}
	// small != nil
	if big == nil {
		return false
	}
	// big！=nil  small!=nil
	if isSameValueStructure(big, small) {
		return true
	}
	return containsTree1(big.Left, small) || containsTree1(big.Right, small)
}

// head1为头的树，是否在结构对应上，完全和head2一样
func isSameValueStructure(head1 *Node, head2 *Node) bool {
	if head1 == nil && head2 != nil {
		return false
	}
	if head1 != nil && head2 == nil {
		return false
	}
	if head1 == nil && head2 == nil {
		return true
	}
	if head1.Value != head2.Value {
		return false
	}
	// head1.value == head2.value
	return isSameValueStructure(head1.Left, head2.Left) &&
		isSameValueStructure(head1.Right, head2.Right)
}

func containsTree2(big *Node, small *Node) bool {
	if small == nil {
		return true
	}
	if big == nil {
		return false
	}
	str := preSerial(big)
	match := preSerial(small)
	return getIndexOf(str, match) != -1
}

func getIndexOf(str1 []string, str2 []string) int {
	if str1 == nil || str2 == nil || len(str1) < 1 || len(str1) < len(str2) {
		return -1
	}
	x := 0
	y := 0
	next := getNextArray(str2)
	for x < len(str1) && y < len(str2) {
		if str1[x] == str2[y] {
			x++
			y++
		} else if next[y] == -1 {
			x++

		} else {
			y = next[y]
		}
	}
	return ternary(y == len(str2), x-y, -1).(int)
}

func getNextArray(ms []string) []int {
	if len(ms) == 1 {
		return []int{-1}
	}
	next := make([]int, len(ms))
	next[0] = -1
	next[1] = 0
	i := 2
	cn := 0
	for i < len(next) {
		if ms[i-1] == ms[cn] {
			cn++
			next[i] = cn
			i++
		} else if cn > 0 {
			cn = next[cn]

		} else {
			next[i] = 0
			i++
		}
	}
	return next
}

func preSerial(head *Node) []string {
	var ans []string
	pres(head, &ans)
	return ans
}

func pres(head *Node, ans *[]string) {
	if head == nil {
		*ans = append(*ans, "#")
	} else {
		*ans = append(*ans, strconv.Itoa(head.Value))
		pres(head.Left, ans)
		pres(head.Right, ans)
	}
}

func ternary(a bool, b, c interface{}) interface{} {
	if a {
		return b
	}
	return c
}

// for test
func generateRandomBST(maxLevel, maxValue int) *Node {
	return generate(1, maxLevel, maxValue)

}

// for test
func generate(level int, maxLevel int, maxValue int) *Node {
	rand.Seed(time.Now().UnixNano())
	if level > maxLevel || rand.Float32() < 0.5 {
		return nil
	}
	head := NewNode(rand.Intn(maxValue))
	head.Left = generate(level+1, maxLevel, maxValue)
	head.Right = generate(level+1, maxLevel, maxValue)
	return head
}

func main() {
	//bigTreeLevel := 7
	//smallTreeLevel := 4
	//nodeMaxValue := 5
	//testTimes := 10
	//fmt.Println("test begin")
	//for i := 0; i < testTimes; i++ {
	//	big := generateRandomBST(bigTreeLevel, nodeMaxValue)
	//	small := generateRandomBST(smallTreeLevel, nodeMaxValue)
	//	ans1 := containsTree1(big, small)
	//	ans2 := containsTree2(big, small)
	//	if ans1 != ans2 {
	//		fmt.Println("Oops!")
	//	}
	//}
	//fmt.Println("test finish!")

	big1 := NewNode(4)
	big1.Left = NewNode(4)
	big1.Right = NewNode(0)
	small1 := NewNode(0)
	ans1 := containsTree1(big1, small1)
	ans2 := containsTree2(big1, small1)
	if ans1 != ans2 {
		fmt.Println("Oops!")
	}
}
