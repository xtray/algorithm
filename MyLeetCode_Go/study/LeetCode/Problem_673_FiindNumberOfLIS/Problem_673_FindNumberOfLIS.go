package main

import (
	"fmt"
	"math/rand"
)

// 好理解的方法，时间复杂度O(N^2)
func findNumberOfLIS(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}
	n := len(nums)
	lens := make([]int, n)
	cnts := make([]int, n)
	lens[0] = 1
	cnts[0] = 1
	maxLen := 1
	allCnt := 1
	for i := 1; i < n; i++ {
		preLen := 0
		preCnt := 1
		for j := 0; j < i; j++ {
			if nums[j] >= nums[i] || preLen > lens[j] {
				continue
			}
			if preLen < lens[j] {
				preLen = lens[j]
				preCnt = cnts[j]
			} else {
				preCnt += cnts[j]
			}
		}
		lens[i] = preLen + 1
		cnts[i] = preCnt
		if maxLen < lens[i] {
			maxLen = lens[i]
			allCnt = cnts[i]
		} else if maxLen == lens[i] {
			allCnt += cnts[i]
		}
	}
	return allCnt
}

// 优化后的最优解，时间复杂度O(N*logN)
func findNumberOfLIS2(nums []int) int {
	if nums == nil || len(nums) == 0 {
		return 0
	}

	var dp []*SkipListMap
	length := 0
	cnt := 0
	for _, num := range nums {
		// num之前的长度，num到哪个长度len+1
		length = search(dp, num)
		// cnt : 最终要去加底下的记录，才是应该填入的value
		if length == 0 {
			cnt = 1
		} else {
			p := dp[length-1]
			over := 0
			if p.CeilingKey(num) != nil {
				over = p.Get(p.CeilingKey(num)).(int)
			}
			_, v := p.FirstEntry()
			cnt = v.(int) - over
		}
		if length == len(dp) {
			newItem := NewSkipListMap(IntComparator)
			newItem.Put(num, cnt)
			dp = append(dp, newItem)
		} else {
			p := dp[length]
			_, v := p.FirstEntry()
			p.Put(num, v.(int)+cnt)
		}
	}
	_, v := dp[len(dp)-1].FirstEntry()
	return v.(int)
}

// 二分查找，返回>=num最左的位置
func search(dp []*SkipListMap, num int) int {
	l := 0
	r := len(dp) - 1
	m := 0
	ans := len(dp)
	for l <= r {
		m = (l + r) / 2
		p:=dp[m]
		if p.FirstKey().(int) >= num {
			ans = m
			r = m - 1
		} else {
			l = m + 1
		}
	}
	return ans
}

// *****************使用 skiplist 实现的有序表
// 跳表的节点定义
type SkipListNode struct {
	Key       interface{}
	Val       interface{}
	NextNodes []*SkipListNode
}

func NewSkipListNode(k, v interface{}) *SkipListNode {
	return &SkipListNode{
		Key:       k,
		Val:       v,
		NextNodes: []*SkipListNode{nil},
	}
}

// 遍历的时候，如果是往右遍历到的null(next == null), 遍历结束
// 头(null), 头节点的null，认为最小
// node  -> 头，node(null, "")  node.IsKeyLess(!null)  true
// node里面的key是否比otherKey小，true，不是false
func (skn *SkipListNode) IsKeyLess(otherKey interface{}, comparator Comparator) bool {
	//  otherKey == null -> false
	if otherKey != nil && (skn.Key == nil || comparator(skn.Key, otherKey) < 0) {
		return true
	}
	return false
}

func (skn *SkipListNode) IsKeyEqual(otherKey interface{}, comparator Comparator) bool {
	if (skn.Key == nil && otherKey == nil) ||
		(otherKey != nil && (skn.Key == nil || comparator(skn.Key, otherKey) == 0)) {
		return true
	}
	return false
}

// IntComparator provides a basic comparison on int
func IntComparator(a, b interface{}) int {
	aAsserted := a.(int)
	bAsserted := b.(int)
	switch {
	case aAsserted > bAsserted:
		return 1
	case aAsserted < bAsserted:
		return -1
	default:
		return 0
	}
}

type Comparator func(a, b interface{}) int

type SkipListMap struct {
	PROBABILITY float32
	Head        *SkipListNode
	Size        int
	MaxLevel    int
	Comparator  Comparator
}

func NewSkipListMap(comp Comparator) *SkipListMap {
	return &SkipListMap{
		PROBABILITY: 0.5, // < 0.5 继续做，>=0.5 停
		Head:        NewSkipListNode(nil, nil),
		Size:        0,
		MaxLevel:    0,
		Comparator:  comp,
	}
}

// 从最高层开始，一路找下去，
// 最终，找到第0层的<key的最右的节点
func (skm *SkipListMap) mostRightLessNodeInTree(key interface{}) *SkipListNode {
	if key == nil {
		return nil
	}
	level := skm.MaxLevel
	cur := skm.Head
	for level >= 0 { // 从上层跳下层
		//  cur  level  -> level-1
		cur = skm.mostRightLessNodeInLevel(key, cur, level)
		level--
	}
	return cur
}

// 在level层里，如何往右移动
// 现在来到的节点是cur，来到了cur的level层，在level层上，找到<key最后一个节点并返回
func (skm *SkipListMap) mostRightLessNodeInLevel(key interface{},
	cur *SkipListNode,
	level int) *SkipListNode {
	//next := cur.NextNodes[level]
	next := getNextNodes(cur, level)
	for next != nil && next.IsKeyLess(key, skm.Comparator) {
		cur = next
		//next = cur.NextNodes[level]
		next = getNextNodes(cur, level)
	}
	return cur
}

func getNextNodes(cur *SkipListNode, level int) *SkipListNode {
	if level >= 0 && level < len(cur.NextNodes) {
		return cur.NextNodes[level]
	} else {
		return nil
	}
}

func (skm *SkipListMap) ContainsKey(key interface{}) bool {
	if key == nil {
		return false
	}
	less := skm.mostRightLessNodeInTree(key)
	//next := less.NextNodes[0]
	next := getNextNodes(less, 0)
	return next != nil && next.IsKeyEqual(key, skm.Comparator)
}

// 新增、改value
func (skm *SkipListMap) Put(key, value interface{}) {
	if key == nil {
		return
	}
	// 0层上，最右一个，< key 的Node -> >key
	less := skm.mostRightLessNodeInTree(key)
	//find := less.NextNodes[0]
	find := getNextNodes(less, 0)
	if find != nil && find.IsKeyEqual(key, skm.Comparator) {
		find.Val = value
	} else { // find == null   8   7   9
		skm.Size++
		newNodeLevel := 0
		for rand.Float32() < skm.PROBABILITY {
			newNodeLevel++
		}
		// newNodeLevel
		for newNodeLevel > skm.MaxLevel {
			skm.Head.NextNodes = append(skm.Head.NextNodes, nil)
			skm.MaxLevel++
		}
		newNode := NewSkipListNode(key, value)
		for i := 0; i <= newNodeLevel; i++ {
			newNode.NextNodes = append(newNode.NextNodes, nil)
		}
		level := skm.MaxLevel
		pre := skm.Head
		for level >= 0 {
			// level 层中，找到最右的 < key 的节点
			pre = skm.mostRightLessNodeInLevel(key, pre, level)
			if level <= newNodeLevel {
				//newNode.NextNodes[level] = pre.NextNodes[level]
				newNode.NextNodes[level] = getNextNodes(pre, level)
				pre.NextNodes[level] = newNode
			}
			level--
		}
	}
}

func (skm *SkipListMap) Get(key interface{}) interface{} {
	if key == nil {
		return nil
	}
	less := skm.mostRightLessNodeInTree(key)
	//next := less.NextNodes[0]
	next := getNextNodes(less, 0)
	if next != nil && next.IsKeyEqual(key, skm.Comparator) {
		return next.Val
	} else {
		return nil
	}
}

func (skm *SkipListMap) Remove(key interface{}) {
	if skm.ContainsKey(key) {
		skm.Size--
		level := skm.MaxLevel
		pre := skm.Head
		for level >= 0 {
			pre = skm.mostRightLessNodeInLevel(key, pre, level)
			//next := pre.NextNodes[level]
			next := getNextNodes(pre, level)
			// 1）在这一层中，pre下一个就是key
			// 2）在这一层中，pre的下一个key是>要删除key
			if next != nil && next.IsKeyEqual(key, skm.Comparator) {
				// free delete node memory -> C++
				// level : pre -> next(key) -> ...
				pre.NextNodes[level] = next.NextNodes[level]
			}
			// 在level层只有一个节点了，就是默认节点head
			if level != 0 && pre == skm.Head && pre.NextNodes[level] == nil {
				skm.Head.NextNodes = skm.Head.NextNodes[:len(skm.Head.NextNodes)-1]
				skm.MaxLevel--
			}
			level--
		}
	}
}

func (skm *SkipListMap) FirstKey() interface{} {
	if skm.Head.NextNodes[0] != nil {
		return skm.Head.NextNodes[0].Key
	} else {
		return nil
	}
}

func (skm *SkipListMap) FirstEntry() (interface{}, interface{}) {
	if skm.Head.NextNodes[0] != nil {
		return skm.Head.NextNodes[0].Key, skm.Head.NextNodes[0].Val
	} else {
		return nil, nil
	}
}

func (skm *SkipListMap) LastKey() interface{} {
	level := skm.MaxLevel
	cur := skm.Head
	for level >= 0 {
		//next := cur.NextNodes[level]
		next := getNextNodes(cur, level)
		for next != nil {
			cur = next
			//next = cur.NextNodes[level]
			next = getNextNodes(cur, level)
		}
		level--
	}
	return cur.Key
}

func (skm *SkipListMap) LastEntry() (interface{}, interface{}) {
	level := skm.MaxLevel
	cur := skm.Head
	for level >= 0 {
		//next := cur.NextNodes[level]
		next := getNextNodes(cur, level)
		for next != nil {
			cur = next
			//next = cur.NextNodes[level]
			next = getNextNodes(cur, level)
		}
		level--
	}
	return cur.Key, cur.Val
}

func (skm *SkipListMap) CeilingKey(key interface{}) interface{} {
	if key == nil {
		return nil
	}
	less := skm.mostRightLessNodeInTree(key)
	next := less.NextNodes[0]
	if next != nil {
		return next.Key
	} else {
		return nil
	}
}

func (skm *SkipListMap) FloorKey(key interface{}) interface{} {
	if key == nil {
		return nil
	}
	less := skm.mostRightLessNodeInTree(key)
	next := less.NextNodes[0]
	if next != nil && next.IsKeyEqual(key, skm.Comparator) {
		return next.Key
	} else {
		return less.Key
	}
}

func (skm *SkipListMap) Len() int {
	return skm.Size
}

func main() {
	nums := []int{1,3,5,4,7};
	ans := findNumberOfLIS(nums)
	ans2 := findNumberOfLIS2(nums)
	fmt.Println(ans)
	fmt.Println(ans2)

}
