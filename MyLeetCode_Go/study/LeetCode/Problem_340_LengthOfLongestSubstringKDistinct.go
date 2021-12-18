package main

import (
	"fmt"
	"math/rand"
	"time"
)

func lengthOfLongestSubstringKDistinct(s string, k int) int {
	if len(s) == 0 || k < 1 {
		return 0
	}
	str := []byte(s)
	N := len(str)
	count := make([]int, 256)
	diff := 0
	R := 0
	ans := 0
	for i := 0; i < N; i++ {
		// R 窗口的右边界
		for R < N && (diff < k || diff == k && count[str[R]] > 0) {
			if count[str[R]] == 0 {
				diff++
			}
			count[str[R]]++
			R++
		}
		// R 来到违规的第一个位置
		ans = max(ans, R-i)
		if count[str[i]] == 1 {
			diff--
		}
		count[str[i]]--
	}
	return ans
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func randomString(length, variety int) string {
	rand.Seed(time.Now().UnixNano())
	str := make([]byte, length)
	for i := 0; i < length; i++ {
		str[i] = byte(rand.Intn(variety) + 'a')
	}
	return string(str)
}

func main() {
	str := "eceba"
	k := 2
	ans := lengthOfLongestSubstringKDistinct(str, k)
	fmt.Println(ans)

	times:=1000
	length :=10
	variety := 5
	for i:=0;i<times;i++{
		str = randomString(length, variety)
		k = rand.Intn(variety)
		ans = lengthOfLongestSubstringKDistinct(str, k)
	}
}
