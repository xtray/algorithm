package main

import (
	"fmt"
	"sort"
)

type IntSlice []int

func (s IntSlice) Len() int { return len(s) }
func (s IntSlice) Swap(i, j int){ s[i], s[j] = s[j], s[i] }
func (s IntSlice) Less(i, j int) bool { return s[i] - s[j]< 0 }

func main() {
	a := []int{4,3,2,1,5,9,8,7,6}
	sort.Sort(IntSlice(a))
	fmt.Println("After sorted: ", a)
}