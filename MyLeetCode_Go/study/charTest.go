package main

import "fmt"

func main()  {

	var c rune='a'
	var i int =98
	i1:=int(c)
	fmt.Println("'a' convert to",i1)
	c1:=rune(i)
	fmt.Println("98 convert to",string(c1))

	//string to rune
	for _, char := range []rune("世界你好") {
		fmt.Println(string(char))
	}

	cc:='a'
	fmt.Println(cc)
	fmt.Println(string(cc))
	fmt.Println(string(97))
}
