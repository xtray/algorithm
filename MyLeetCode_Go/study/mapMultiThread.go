package main

import (
	"fmt"
	"time"
)

func main() {
	c := make(map[string]int)

	for i := 0; i < 100; i++ {
		go func() {
			for j := 0; j < 100; j++ {
				c[fmt.Sprintf("%d", j)] = j
			}
		}()
	}

	time.Sleep(3 * time.Second)
	fmt.Println(c)
}

