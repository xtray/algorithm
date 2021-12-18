package main
import "fmt"
func main(){
	a := []int {1, 2, 3} //改成slice
	for i, v := range a{
		if i == 0{
			a[1], a[2] = 200, 300
			fmt.Println(a) //[1 200 300]
		}
		a[i] = v + 100
	}
	fmt.Println(a)
}
