package main
import "fmt"
func main(){
	//数组的遍历
	a := [3]int {1, 2, 3}
	for i, n := range a{
		fmt.Println(i, n)
	}
	//切片的遍历
	b := []int{2, 3, 4}
	for i, n := range b{
		fmt.Println(i, n)
	}
	//map的遍历
	c := map[string]int{"Hello":1, "World":2}
	for k, v := range c{
		fmt.Println(k, v)
	}
}