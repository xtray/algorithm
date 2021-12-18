package main

import (
	"fmt"
	"time"
)

func main() {
	c := make(chan int)
	c2 := make(chan int)
	go func() {
		time.Sleep(2 * time.Second)
		c <- 1
	}()
	go func() {
		time.Sleep(1 * time.Second)
		c2 <- 2
	}()

	select {
	case i := <-c:
		fmt.Printf("receive from c: %d\n", i)
	case i := <- c2:
		fmt.Printf("receive from c2: %d\n", i)
	}
}