package main

import (
	"container/heap"
	"fmt"
)

type stu struct {
	name string
	age  int
}
type Stu []stu

func (t *Stu) Len() int {
	return len(*t) //
}

func (t *Stu) Less(i, j int) bool {
	return (*t)[i].age < (*t)[j].age
}

func (t *Stu) Swap(i, j int) {
	(*t)[i], (*t)[j] = (*t)[j], (*t)[i]
}

func (t *Stu) Push(x interface{}) {
	*t = append(*t, x.(stu))
}

func (t *Stu) Pop() interface{} {
	n := len(*t)
	x := (*t)[n-1]
	*t = (*t)[:n-1]
	return x
}

func main() {
	student := &Stu{{"Amy", 21}, {"Dav", 15}, {"Spo", 22}, {"Reb", 11}}
	heap.Init(student)
	one := stu{"hund", 9}
	as := stu{"ty", 3}
	ff := stu{"ff", 55}
	heap.Push(student, one)
	heap.Push(student, as)
	heap.Push(student, ff)
	for student.Len() > 0 {
		fmt.Printf("%v\n", heap.Pop(student))
	}

}