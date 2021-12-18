package main
import "fmt"
func main(){
	a := [3]int {1, 2, 3}
	for _, v := range a{ //复制一份a遍历[1, 2, 3]
		v += 100 //v是复制对象中的值，不会改变a数组元素的值
	}
	fmt.Println(a) //1 2 3
}
