package _Contest.LC.W303;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// NOTE: 优先队列过不了!!

public class Problem_2353_DesignFoodRatingSystem {

    class FoodRatings {

        public class Dish {
            public String name;
            public String way;
            public int score;

            public Dish(String n, String w, int s) {
                name = n;
                way = w;
                score = s;
            }
        }

        // [ways, dish]
        private Map<String, TreeSet<Dish>> waysMap;
        // [name, dish]
        private Map<String, Dish> dishMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            waysMap = new HashMap<>();
            dishMap = new HashMap<>();

            int n = foods.length;
            for (int i = 0; i < n; i++) {
                Dish dish = new Dish(foods[i], cuisines[i], ratings[i]);
                waysMap.computeIfAbsent(cuisines[i], key -> new TreeSet<>(
                        (o1, o2) -> o2.score != o1.score ? o2.score - o1.score : o1.name.compareTo(o2.name)
                )).add(dish);
                dishMap.put(foods[i], dish);
            }

        }

        public void changeRating(String food, int newRating) {
            Dish dish = dishMap.get(food);
            TreeSet<Dish> treeSet = waysMap.get(dish.way);
            treeSet.remove(dish);
            dish.score = newRating;
            treeSet.add(dish);
        }

        public String highestRated(String cuisine) {
            return waysMap.get(cuisine).first().name;
        }
    }
}
