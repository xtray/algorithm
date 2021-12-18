package main

import "fmt"

func main() {
	m := map[string]int{"value": 0}
	m1 := m
	fmt.Println("m =", m)
	fmt.Println("m1 =", m1)
	m1["value"] = 1
	fmt.Println("m =", m)
	fmt.Println("m1 =", m1)
}

