package main

import (
	"fmt"
	"time"
)


func main() {
	go fmt.Println("goroutine message")
	fmt.Println("main function message")
	time.Sleep(time.Second*10)		//休眠1s
}