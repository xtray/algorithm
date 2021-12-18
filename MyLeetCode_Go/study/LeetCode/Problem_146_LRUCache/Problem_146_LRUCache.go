package main

import "fmt"

// 本题测试链接 : https://leetcode.com/problems/lru-cache/

type LRUCache struct {
	cache MyCache
}

func Constructor(capacity int) LRUCache {
	return LRUCache{cache: NewMyCache(capacity)}
}

func (this *LRUCache) Get(key int) int {
	ans := this.cache.Get(key)
	if ans == nil {
		return -1
	} else {
		return ans.(int)
	}
}

func (this *LRUCache) Put(key int, value int) {
	this.cache.Set(key, value)
}

type Node struct {
	Key   interface{}
	Value interface{}
	Last  *Node
	Next  *Node
}

func NewNode(key, value interface{}) *Node {
	return &Node{Key: key, Value: value}
}

type NodeDoubleLinkedList struct {
	Head *Node
	Tail *Node
}

func NewNodeDoubleLinkedList() *NodeDoubleLinkedList {
	return &NodeDoubleLinkedList{}
}

// 现在来了一个新的node，请挂到尾巴上去
func (linkedlist *NodeDoubleLinkedList) AddNode(newNode *Node) {
	if newNode == nil {
		return
	}
	if linkedlist.Head == nil {
		linkedlist.Head = newNode
		linkedlist.Tail = newNode
	} else {
		linkedlist.Tail.Next = newNode
		newNode.Last = linkedlist.Tail
		linkedlist.Tail = newNode
	}
}

// node 入参，一定保证！node在双向链表里！
// node原始的位置，左右重新连好，然后把node分离出来
// 挂到整个链表的尾巴上
func (linkedlist *NodeDoubleLinkedList) moveNodeToTail(node *Node) {
	if linkedlist.Tail == node {
		return
	}
	if linkedlist.Head == node {
		linkedlist.Head = node.Next
		linkedlist.Head.Last = nil
	} else {
		node.Last.Next = node.Next
		node.Next.Last = node.Last
	}
	node.Last = linkedlist.Tail
	node.Next = nil
	linkedlist.Tail.Next = node
	linkedlist.Tail = node
}

func (linkedlist *NodeDoubleLinkedList) RemoveHead() *Node {
	if linkedlist.Head == nil {
		return nil
	}
	res := linkedlist.Head
	if linkedlist.Head == linkedlist.Tail {
		linkedlist.Head = nil
		linkedlist.Tail = nil
	} else {
		linkedlist.Head = res.Next
		res.Next = nil
		linkedlist.Head.Last = nil
	}
	return res
}

type MyCache struct {
	KeyNodeMap map[interface{}]interface{}
	NodeList   *NodeDoubleLinkedList
	capacity   int
}

func NewMyCache(cap int) MyCache {
	return MyCache{
		KeyNodeMap: make(map[interface{}]interface{}),
		NodeList:   NewNodeDoubleLinkedList(),
		capacity:   cap,
	}
}

func (mc *MyCache) Get(key interface{}) interface{} {
	if _, ok := mc.KeyNodeMap[key]; ok {
		res := mc.KeyNodeMap[key].(*Node)
		mc.NodeList.moveNodeToTail(res)
		return res.Value
	}
	return nil
}

// Set(Key, Value)
// 新增  更新value的操作
func (mc *MyCache) Set(key, value interface{}) {
	if _, ok := mc.KeyNodeMap[key]; ok {
		node := mc.KeyNodeMap[key].(*Node)
		node.Value = value
		mc.NodeList.moveNodeToTail(node)
	} else { // 新增！
		newNode := NewNode(key, value)
		mc.KeyNodeMap[key] = newNode
		mc.NodeList.AddNode(newNode)
		if len(mc.KeyNodeMap) == mc.capacity+1 {
			mc.removeMostUnusedCache()
		}
	}
}

func (mc *MyCache) removeMostUnusedCache() {
	removeNode := mc.NodeList.RemoveHead()
	delete(mc.KeyNodeMap, removeNode.Key)
}

func main() {
	lRUCache := Constructor(2)
	lRUCache.Put(1, 1)
	lRUCache.Put(2, 2)
	ans := lRUCache.Get(1)
	fmt.Println(ans)
	lRUCache.Put(3, 3)
	ans = lRUCache.Get(2)
	fmt.Println(ans)
	lRUCache.Put(4, 4)
	ans = lRUCache.Get(1)
	fmt.Println(ans)
	ans = lRUCache.Get(3)
	fmt.Println(ans)
	ans = lRUCache.Get(4)
	fmt.Println(ans)
}
