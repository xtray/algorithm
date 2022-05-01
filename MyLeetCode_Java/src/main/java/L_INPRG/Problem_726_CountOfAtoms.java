// package L_INPRG;
//
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.Map;
// import java.util.TreeMap;
//
// public class Problem_726_CountOfAtoms {
//
//     public String countOfAtoms(String formula) {
//         Info info = process(formula.toCharArray(), 0);
//         return "";
//     }
//
//     public static class Info {
//         public Map<String, Integer> map;
//         public int index;
//
//         public Info(String v, int i) {
//             map = new HashMap<>();
//             index = i;
//         }
//     }
//
//     // 从 str[i...]往下计算, 遇到字符串终止位置或者右括号就停止, 返回两个值:
//     // map: 负责的这一段的结果
//     // index: 负责的这一段计算到了哪个位置, 如果) 外有数字, 到数字结束的位置
//     // 每一个递归层是一个Map
//     private Info process(char[] str, int i) {
//         LinkedList<Map<String, Integer>> queue = new LinkedList<>();
//         queue.add(new HashMap<>());
//         int cur = 0;
//         StringBuilder sb = new StringBuilder();
//         Info info = null;
//         // 从i位置开始撸串
//         while (i < str.length && str[i] != ')') {
//             if (str[i] >= '0' && str[i] <= '9') {
//                 // 出现数字, 代表字母收集结束, 放入队列
//                 cur = cur * 10 + str[i++] - '0';
//                 Map<String, Integer> curMap = new HashMap<>();
//                 curMap.put(sb.toString(), 1);
//                 queue.addLast(curMap);
//                 sb.setLength(0);
//             } else if (Character.isAlphabetic(str[i])) {
//                 // 出现字母, 代表数字收集结束, 弹出队列结算
//                 if(str[i] >= 'a' && str[i] <= 'z') { // 小写字母
//                     sb.append(str[i]);
//                 } else { // 大写字母
//
//                 }
//                 compute(queue, cur);
//                 cur = 0;
//             } else { // 遇到左括号了
//                 compute(queue, cur);
//                 cur = 0;
//                 info = process(str, i + 1);
//                 i = info.index + 1;
//             }
//         }
//         // 处理括号外的数字
//         if (str[i] == ')') { // 获取后面的数字
//             cur = 0;
//             while (i < str.length && (str[i] >= '0' && str[i] <= '9')) {
//                 cur = cur * 10 + str[i++] - '0';
//             }
//             cur = cur == 0 ? 1 : cur;
//         }
//         // 对() 内的东西按数字cur翻倍
//
//
//     }
//
//     private void compute(LinkedList<Map<String, Integer>> queue, int cur) {
//         if(queue.isEmpty()) return;
//         Map<String, Integer> topMap = queue.pollLast();
//         if (cur != 0) {
//             for (String key : topMap.keySet()) {
//                 topMap.put(key, topMap.get(key) * cur);
//             }
//         }
//         queue.addLast(topMap);
//     }
//
//
// }
