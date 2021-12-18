package main

import "fmt"

// 测试链接：https://leetcode.com/problems/range-sum-query-2d-mutable
// 但这个题是付费题目

type NumMatrix struct {
	Tree [][]int
	Nums [][]int
	N    int
	M    int
}

func Constructor(matrix [][]int) NumMatrix {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		panic("invalid parameter.")
	}
	N := len(matrix)
	M := len(matrix[0])
	tree := make([][]int, N+1)
	for i := range tree {
		tree[i] = make([]int, M+1)
	}
	nums := make([][]int, N)
	for i := range nums {
		nums[i] = make([]int, M)
	}
	this := NumMatrix{tree, nums, N, M}
	for i := 0; i < N; i++ {
		for j := 0; j < M; j++ {
			this.Update(i, j, matrix[i][j])
		}
	}
	return this
}

func (this *NumMatrix) sum(row int, col int) int {
	sum := 0
	for i := row + 1; i > 0; i -= i & (-i) {
		for j := col + 1; j > 0; j -= j & (-j) {
			sum += this.Tree[i][j]
		}
	}
	return sum
}

func (this *NumMatrix) Update(row int, col int, val int) {
	if this.N == 0 || this.M == 0 {
		return
	}
	add := val - this.Nums[row][col]
	this.Nums[row][col] = val
	for i := row + 1; i <= this.N; i += i & (-i) {
		for j := col + 1; j <= this.M; j += j & (-j) {
			this.Tree[i][j] += add
		}
	}
}

func (this *NumMatrix) SumRegion(row1 int, col1 int, row2 int, col2 int) int {
	if this.N == 0 || this.M == 0 {
		return 0
	}
	return this.sum(row2, col2) + this.sum(row1-1, col1-1) - this.sum(row1-1, col2) - this.sum(row2, col1-1)
}


func main()  {
	//matrix := [][]int{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}}
	matrix := [][]int{{1}}
	numMatrix := Constructor(matrix)
	//ans:= numMatrix.SumRegion(2, 1, 4, 3); // 返回 8 (即, 左侧红色矩形的和)
	ans:= numMatrix.SumRegion(0, 0, 0, 0); // 返回 8 (即, 左侧红色矩形的和)
	fmt.Println(ans)
	//numMatrix.Update(3, 2, 2);       // 矩阵从左图变为右图
	numMatrix.Update(0, 0, -1);       // 矩阵从左图变为右图
	//ans = numMatrix.SumRegion(2, 1, 4, 3); // 返回 10 (即，右侧红色矩形的和)
	ans = numMatrix.SumRegion(0, 0, 0, 0); // 返回 10 (即，右侧红色矩形的和)
	fmt.Println(ans)
}