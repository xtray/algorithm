package main

import "fmt"

//func main() {
//	var array = [3]int{0, 1, 2}
//	var array2 = array
//	array2[2] = 5
//	fmt.Println(array, array2)
//}

func main() {
	var array = [3]int{0, 1, 2}
	var array2 = &array
	array2[2] = 5
	fmt.Println(array, *array2)
}