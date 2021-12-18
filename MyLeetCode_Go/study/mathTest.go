package main

import (
	"fmt"
	"math"
)

func powerBaseOf10(base int) int {
	return int(math.Pow10(base))
}

func main() {
	res := powerBaseOf10(3)
	fmt.Println(res)
}