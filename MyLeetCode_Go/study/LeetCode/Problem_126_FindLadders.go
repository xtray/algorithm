package main

import "fmt"

func findLadders(start string, end string, list []string) [][]string {
	list = append(list, start)
	nexts := getNexts(list)
	distances := getDistances(start, nexts)
	var pathList []string
	var res [][]string
	getShortestPaths(start, end, nexts, distances, pathList, &res)
	return res
}

func getNexts(words []string) map[string][]string {
	dict := make(map[string]struct{})
	for _, word := range words {
		dict[word] = struct{}{}
	}
	nexts := make(map[string][]string)
	for i := 0; i < len(words); i++ {
		nexts[words[i]] = getNext(words[i], dict)
	}
	return nexts
}

// word, 在表中，有哪些邻居，把邻居们，生成list返回
func getNext(word string, dict map[string]struct{}) []string {
	var res []string
	chs := []byte(word)
	var cur byte
	for cur = 'a'; cur <= 'z'; cur++ {
		for i := 0; i < len(chs); i++ {
			if chs[i] != cur {
				tmp := chs[i]
				chs[i] = cur
				if _, ok := dict[string(chs)]; ok {
					res = append(res, string(chs))
				}
				chs[i] = tmp
			}
		}
	}
	return res
}

// 生成距离表，从start开始，根据邻居表，宽度优先遍历，对于能够遇到的所有字符串，生成(字符串，距离)这条记录，放入距离表中
func getDistances(start string, nexts map[string][]string) map[string]int {
	distances := make(map[string]int)
	distances[start] = 0
	var queue []string
	queue = append(queue, start)
	set := make(map[string]struct{})
	set[start] = struct{}{}
	for len(queue) != 0 {
		cur := queue[0]
		queue = queue[1:]
		for _, next := range nexts[cur] {
			if _, ok := set[next]; !ok {
				distances[next] = distances[cur] + 1
				queue = append(queue, next)
				set[next] = struct{}{}
			}
		}
	}
	return distances
}

// cur 当前来到的字符串 可变
// to 目标，固定参数
// nexts 每一个字符串的邻居表
// cur 到开头距离5 -> 到开头距离是6的支路 distances距离表
// path : 来到cur之前，深度优先遍历之前的历史是什么
// res : 当遇到cur，把历史，放入res，作为一个结果
func getShortestPaths(cur string, to string, nexts map[string][]string,
	distances map[string]int, path []string, res *[][]string) {
	path = append(path, cur)
	if to == cur {
		tmp:=make([]string,len(path))
		copy(tmp,path)
		*res = append(*res, tmp)
	} else {
		for _, next := range nexts[cur] {
			if distances[next] == distances[cur]+1 {
				getShortestPaths(next, to, nexts, distances, path, res)
			}
		}
	}
	path = path[:len(path)-1]
}

func main() {
	//begin := "hit"
	//end := "cog"
	//wordList := []string{"hot","dot","dog","lot","log","cog"}

	begin := "teach"
	end := "place"
	wordList :=
		[]string{"peale", "wilts", "place", "fetch", "purer", "pooch", "peace", "poach", "berra", "teach", "rheum", "peach"}

	res := findLadders(begin, end, wordList)

	for i, _ := range res {
		for _, val := range res[i] {
			fmt.Printf("%v ", val)
		}
		fmt.Println()
	}

}

//ref: https://leetcode-cn.com/problems/word-ladder-ii/solution/golang-bao-jiao-bao-hui-jian-dan-yi-dong-jie-fa-by/
