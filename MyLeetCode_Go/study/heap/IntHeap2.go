package main
//堆的简单使用
import (
	"container/heap"
	"fmt"
)
type intHeap[]int  //定义堆对应的数据结构

//实现Interface对应的sort.Interface中的Len
func (h intHeap)Len()int  {
	return len(h)
}
//实现Interface对应的sort.Interface中的Less
func (h intHeap)Less(i, j int) bool {
	return h[i]<h[j]  //确定最大堆和最小堆
}
//实现Interface对应的sort.Interface中的Swap
func (h intHeap)Swap(i, j int)  {
	h[i],h[j]=h[j],h[i]
}
//实现Interface对应的Push
func (h *intHeap)Push(x interface{})  {  //要改变对应的数据，所以接受者为指针（地址传递）
	*h=append(*h,x.(int))
}
//实现Interface对应的Pop
func (h *intHeap)Pop()interface{}{
	old:=*h
	n:=len(old)
	x:=old[n-1]
	*h=old[0:n-1]
	return x
}
//自定义 更新数据方法
func (h *intHeap)update(i int,data int)  {
	(*h)[i]=data //将下标为i的元素的数据改为 data
	//只起修复作用，不用以下语句，也可完成修改，不过堆没有进行相应的移位变化（堆的修复）
	heap.Fix(h,0)
}
//自定义 删除数据方法
func (h *intHeap)delete(i int)  {
	heap.Remove(h,i)
}
func main() {
	h:=intHeap{2,1,5}
	heap.Init(&h)  //传入的参数必须是Interface类型，因此需要继承Interface（heap包自定义的）
	heap.Push(&h,3)
	fmt.Println(h)
	h.update(0,33)
	fmt.Println("更新之后的数据是：",h)
	h.delete(0)
	fmt.Println("删除之后的数据是：",h)
	fmt.Println("最小值为：",h[0])
	for len(h)>0{
		fmt.Println(heap.Pop(&h))
	}
	//fmt.Println(h)
}