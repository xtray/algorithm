package main

import "fmt"

//	// m -> 二维数组， 不是0就是1
//	//
func infect(m [][]int, i int, j int) {
	if i < 0 || i == len(m) || j < 0 || j == len(m[0]) || m[i][j] != 1 {
		return
	}
	// m[i][j] == 1
	m[i][j] = 2
	infect(m, i-1, j)
	infect(m, i+1, j)
	infect(m, i, j-1)
	infect(m, i, j+1)
}

func main() {
	matrix := [][]int{{0,0,0,0}, {0,1,1,0},{0,0,1,0},{0,1,0,0}}
	N := len(matrix)
	M := len(matrix[0])
	for i:=0;i<N;i++ {
		for j:=0;j<M;j++ {
			infect(matrix,i,j)
		}
	}
	fmt.Println(matrix)
}
