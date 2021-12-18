package main

import (
	"fmt"
	"time"
)

func main() {
	done := make(chan struct{}, 1)

	go func() {
		// simulate long running task
		time.Sleep(4 * time.Second)
		done <- struct{}{}
		fmt.Println("long running task is done")
	}()

	<-done
	close(done)

	fmt.Println("whole program is done.")
}