// package _DailyTarget;
//
// import java.util.List;
//
// public class Problem_385_MiniParser {
//
//     class NestedInteger {
//         // // Constructor initializes an empty nested list.
//         public NestedInteger() {
//         }
//
//         //
//         // // Constructor initializes a single integer.
//         public NestedInteger(int value) {
//         }
//
//         // @return true if this NestedInteger holds a single integer, rather than a nested list.
//         public boolean isInteger() {
//             return false;
//         }
//
//         // @return the single integer that this NestedInteger holds, if it holds a single integer
//         // Return null if this NestedInteger holds a nested list
//         public Integer getInteger() {
//             return null;
//         }
//
//         // Set this NestedInteger to hold a single integer.
//         public void setInteger(int value) {
//         }
//
//         // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//         public void add(NestedInteger ni) {
//         }
//
//         // @return the nested list that this NestedInteger holds, if it holds a nested list
//         // Return empty list if this NestedInteger holds a single integer
//         public List<NestedInteger> getList() {
//             return null;
//         }
//
//     }
//
//     public class Info {
//         public NestedInteger res;
//         public int pos; // 处理到的位置
//
//         public Info(NestedInteger r, int p) {
//             res = r;
//             pos = p;
//         }
//     }
//
//     public NestedInteger deserialize(String s) {
//         NestedInteger ans = new NestedInteger();
//         if (s == null || s.length() == 0) {
//             return ans;
//         }
//         char[] str = s.toCharArray();
//         int N = str.length;
//         int cur = 0;
//         for(int i = 0; i<N; i++) {
//             if(str[i] >= 0 && str[i] <= 9) {
//                 cur = cur * 10 + str[i++] - '0';
//             } else if (str[i] == ',') {
//                 ans.add(cur);
//                 cur = 0;
//             }
//         }
//
//     }
// }
