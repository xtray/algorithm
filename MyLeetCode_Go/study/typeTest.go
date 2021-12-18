package main

import (
	"fmt"
	"reflect"
	"unsafe"
)

func main() {
	v := "hello world"
	fmt.Println(typeof(v))

	var aaa = 1
	var bbb int32 = 2
	fmt.Println(reflect.TypeOf(aaa).String())
	fmt.Println(unsafe.Sizeof(aaa))
	fmt.Println(reflect.TypeOf(bbb).String())
	fmt.Println(unsafe.Sizeof(bbb))
	fmt.Println("----------------------")

	var i1 int = 1
	var i2 int8 = 2
	var i3 int16 = 3
	var i4 int32 = 4
	var i5 int64 = 5
	fmt.Println(unsafe.Sizeof(i1))
	fmt.Println(unsafe.Sizeof(i2))
	fmt.Println(unsafe.Sizeof(i3))
	fmt.Println(unsafe.Sizeof(i4))
	fmt.Println(unsafe.Sizeof(i5))


}
func typeof(v interface{}) string {
	switch t := v.(type) {
	case int:
		return "int"
	case float64:
		return "float64"
		//... etc
	default:
		_ = t
		return "unknown"
	}
}
