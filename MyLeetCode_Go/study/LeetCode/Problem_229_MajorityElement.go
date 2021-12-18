package main

import "fmt"

func majorityElement(nums []int) []int {
   return findKMajor(nums, 3)
}

// 所有占比例超过 1/k 的, 出现次数为 > N/K
func findKMajor(arr []int, K int) []int {
	// 攒候选，cands，候选表，最多K-1条记录！ > N / K次的数字，最多有K-1个
	cands := map[int]int{}
	for i:= 0; i!= len(arr);i++ {
		if _, ok := cands[arr[i]]; ok {
			cands[arr[i]]++
		} else { // arr[i] 不是候选
			if len(cands) == K -1 { // 当前数肯定不要！，每一个候选付出1点血量，血量变成0的候选，要删掉！
				allCandsMinusOne(cands)
			} else {
				cands[arr[i]]=1
			}
		}
	}
	// 所有可能的候选，都在cands表中！遍历一遍arr，每个候选收集真实次数
	reals := getReals(arr, cands)
	ans := []int{}
	for key := range reals {
		if reals[key] > len(arr)/K {
			ans = append(ans, key)
		}
	}
	return ans
}


func getReals(arr []int, cands map[int]int) map[int]int {
	reals := map[int]int{}
	for i:=0;i< len(arr);i++{
		curNum := arr[i]
		if _, ok := cands[curNum]; ok {
			if _, ok := reals[curNum]; ok {
				reals[curNum]++
			} else {
				reals[curNum] = 1
			}
		}
	}
	return reals
}

func allCandsMinusOne(hashmap map[int]int) {
	for key, value := range hashmap {
		if value == 1 {
			delete(hashmap, key)
			continue
		}
		hashmap[key]--
	}
}

func main() {
	arr := []int{ 4, 2, 1, 1 }
	ans := majorityElement(arr)
	for _, num := range ans {
		fmt.Println(num)
	}
}