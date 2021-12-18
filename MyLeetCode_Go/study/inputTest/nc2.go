package main

import (
	"bufio"
	"fmt"
	"os"
)

var in, out = bufio.NewReader(os.Stdin), bufio.NewWriter(os.Stdout)
func read() (x int) {
	c, e := in.ReadByte()
	for ; c < '0'; c, e = in.ReadByte() {}
	for ; c >= '0' && e == nil; c, e = in.ReadByte() {
		x = x * 10 + int(c) & 15
	}
	return
}

func main() {
	var n, k int
	var a1, a2 [1e5 + 5]int
	n = read()
	k = read()
	for i := 0; i < n; i++ {
		a1[i] = read()
	}
	for i := 0; i < n; i++ {
		a2[i] = read()
	}
	fmt.Println(k)
	out.WriteByte(' ')

	out.Flush()
}
