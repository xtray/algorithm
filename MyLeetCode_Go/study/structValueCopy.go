package main

import (
	"fmt"
)
type Coordinate struct {
	X, Y float32
}

func (coo *Coordinate) GetCoordinate() {
	fmt.Printf("(%.2f,%.2f)\n", coo.X, coo.Y)
	return
}
//值拷贝对象方法
func (coo Coordinate) SetPosition01(a float32,b float32) {
	coo.X = a
	coo.Y = b
}

//指针变量对象方法
func (coo *Coordinate) SetPosition02(a float32,b float32) {
	coo.X = a
	coo.Y = b
}
func main(){
	p0 := Coordinate{1, 2}
	fmt.Print("SetPosition02调用前:")
	p0.GetCoordinate()
	p0.SetPosition02(0, 0)
	fmt.Print("SetPosition02调用后:")
	p0.GetCoordinate()
}