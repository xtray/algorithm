package main

import "fmt"

type Person struct {
	Name string
	Age  int
}
type Student struct {
	per   Person
	Major string
}

func main() {
	s1 := Student{Person{"demo", 99}, "php"}
	fmt.Println(s1)

	s2 := Student{}
	s2.per.Name = "test"
	s2.per.Age = 88
	s2.Major = "Java"
	fmt.Println(s2)
	/*
	   //错误
	   s3 := Student{}
	   s3.Name = "hello"
	   s3.Age = 77
	   s3.Major = "C#"
	   fmt.Println(s3)
	*/
	p1 := Person{"world", 66}
	s4 := Student{p1, "JS"}
	fmt.Println(s4)

}