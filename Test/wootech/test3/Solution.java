package Test.wootech.test3;

import java.util.HashMap;

public class Solution {

    static class Price {
        int sellPrice, recipePrice;

        public Price(int sellPrice, int recipePrice) {
            this.sellPrice = sellPrice;
            this.recipePrice = recipePrice;
        }
    }

    public int solution(String[] ings, String[] menu, String[] sell) {
        HashMap<Character, Integer> ingredientPrice = setIngredientPrice(ings);
        HashMap<String, Price> menuList = setMenu(ingredientPrice, menu);
        int answer = calculateTotalSellPrice(menuList, sell);
        return answer;
    }

    private HashMap<String, Price> setMenu(HashMap<Character, Integer> ingredientPrice, String[] menu) {
        HashMap<String, Price> menuList = new HashMap<>();
        for (String food : menu) {
            String[] tmp = food.split(" ");
            String foodName = tmp[0];
            int recipePrice = calculatePrice(ingredientPrice, tmp[1]);
            int sellPrice = Integer.parseInt(tmp[2]);
            menuList.put(foodName, new Price(sellPrice, recipePrice));
        }
        return menuList;
    }

    private HashMap<Character, Integer> setIngredientPrice(String[] ings) {
        HashMap<Character, Integer> ingredientPrice = new HashMap<>();
        for (String ingredient : ings) {
            String[] tmp = ingredient.split(" ");
            ingredientPrice.put(tmp[0].charAt(0), Integer.parseInt(tmp[1]));
        }
        return ingredientPrice;
    }

    private int calculateTotalSellPrice(HashMap<String, Price> menuList, String[] sell) {
        int totalPrice = 0;
        for (String menu : sell) {
            String[] tmp = menu.split(" ");
            String foodName = tmp[0];
            int count = Integer.parseInt(tmp[1]);
            int profit = (menuList.get(foodName).sellPrice - menuList.get(foodName).recipePrice) * count;
            totalPrice += profit;
        }
        return totalPrice;
    }

    private int calculatePrice(HashMap<Character, Integer> ingredientPrice, String input) {
        int price = 0;
        char[] recipe = input.toCharArray();
        for (char ingredient : recipe) {
            price += ingredientPrice.get(ingredient);
        }
        return price;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] ings = {"r 10", "a 23", "t 124", "k 9"};
        String[] menu = {"PIZZA arraak 145", "HAMBURGER tkar 180", "BREAD kkk 30", "ICECREAM rar 50", "SHAVEDICE rar 45", "JUICE rra 55", "WATER a 20"};
        String[] sell = {"BREAD 5", "ICECREAM 100", "PIZZA 7", "JUICE 10", "WATER 1"};
        System.out.println(sol.solution(ings, menu, sell));
    }
}
