package main

import (
	"fmt"
	"math/rand"
)

func main() {
	for i := 0; i < 100; i++ {
		fmt.Println("My first lucky number is", rand.Intn(10))
		fmt.Println("My senond lucky number is", rand.Intn(10))
	}

}