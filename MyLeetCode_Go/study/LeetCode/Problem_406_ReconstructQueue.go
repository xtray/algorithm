package main

import (
	"fmt"
	"sort"
)

func reconstructQueue1(people [][]int) [][]int {
	N := len(people)
	items := make([]*Unit, N)
	for i:=0; i< N;i++{
		items[i] = NewUnit(people[i][0], people[i][1])
	}
	var units []interface{}
	for _, v := range items {
		units = append(units, v)
	}
	Sort(units, UnitComparator)
	var arrList []*Unit
	for _, item := range units {
		unit := item.(*Unit)
		idx := unit.K
		arrList = append(arrList[:idx], append([]*Unit{unit}, arrList[idx:]...)...)
	}
	ans := make([][]int, N)
	for i := range ans {
		ans[i] = make([]int, 2)
	}
	index := 0
	for _,unit := range arrList {
		ans[index][0] = unit.H
		ans[index][1] = unit.K
		index++
	}
	return ans
}

type Unit struct {
	H int
	K int
}

func NewUnit(height, greater int) *Unit{
	return &Unit{height, greater}
}

type Comparator func(a, b interface{}) int

func UnitComparator(a, b interface{}) int {
	o1 := a.(*Unit)
	o2 := b.(*Unit)
	return ternary(o1.H != o2.H , o2.H - o1.H,o1.K - o2.K).(int)
}

// 三目运算
func ternary(a bool, b, c interface{}) interface{} {
	if a {
		return b
	}
	return c
}

func Sort(values []interface{}, comparator Comparator) {
	sort.Sort(sortable{values, comparator})
}

type sortable struct {
	values     []interface{}
	comparator Comparator
}

func (s sortable) Len() int {
	return len(s.values)
}
func (s sortable) Swap(i, j int) {
	s.values[i], s.values[j] = s.values[j], s.values[i]
}
func (s sortable) Less(i, j int) bool {
	return s.comparator(s.values[i], s.values[j]) < 0
}

func main() {
	people := [][]int{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}}
	res := reconstructQueue1(people)
	fmt.Println(res)

}