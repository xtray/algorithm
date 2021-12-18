package main

import (
	"fmt"
	"strconv"
)


func evalRPN(tokens []string) int {
	var stack []int
	for _, str := range tokens {
		if str == "+" || str == "-" || str == "*" || str == "/" {
			compute(&stack, str)
		} else {
			num, _ := strconv.Atoi(str)
			stack = append(stack, num)
		}
	}
	return stack[0]
}

func compute(stack *[]int, op string) {
	num2 := (*stack)[len(*stack)-1]
	num1 := (*stack)[len(*stack)-2]
	*stack = (*stack)[:len(*stack)-2]
	ans := 0
	switch op {
	case "+":
		ans = num1 + num2
	case "-":
		ans = num1 - num2
	case "*":
		ans = num1 * num2
	case "/":
		ans = num1 / num2
	}
	*stack = append(*stack, ans)
}

func main() {
	input := []string{"2","1","+","3","*"}
	res := evalRPN(input)
	fmt.Println(res)

}