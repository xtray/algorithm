package _Test;

import java.util.HashMap;

// computeIfAbsent() 方法对 hashMap 中指定 key
// 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。

public class MapComputeIfAbsent {

    public static void main(String[] args) {
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();

        // 往HashMap中添加映射项
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 计算 Shirt 的值
        // 使用了匿名函数 lambda 表达式 key-> 280 作为重新映射函数，
        // prices.computeIfAbsent() 将 lambda 表达式返回的新值关联到 Shirt
        // 因为 Shirt 在 HashMap 中不存在，所以是新增了 key/value 对。
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);

        // Shoes中的映射关系已经存在
        // Shoes并没有计算新值
        int shoePrice = prices.computeIfAbsent("Shoes", (key) -> 800);
        System.out.println("Price of Shoes: " + shoePrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }
}
