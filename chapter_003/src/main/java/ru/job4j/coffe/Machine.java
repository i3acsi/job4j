package ru.job4j.coffe;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

public class Machine {
    private Input input;
    private int money;
    private List<Drink> drinks = new ArrayList<>();

    public Machine(Input input) {
        this.input = input;
    }

    public void init() {
        drinks.add(new Drink("Coffe", 30));
        drinks.add(new Drink("Tea", 20));
        drinks.add(new Drink("Water", 5));
    }

    public void make() {
        showMenu();
        int chose = input.accept("Выберете напиток:", p11);
        Drink drink = drinks.get(chose - 1);
        System.out.println("Вы выбрали " + drink.name);
        int price = drink.price;
        while (money < price) {
            System.out.println("Не хватает " + (price - money) + " руб");
            takeMoney();
        }
        System.out.println("Ваш кофе готов. Возьмите сдачу");
        int[] cashback = changes(money, price);
        for (int i : cashback) {
            System.out.print(i + "руб ");
        }

    }

    int[] changes(int money, int price) {
        money -= price;
        int[] coins = new int[]{10, 5, 2, 1};
        int[] temp = new int[coins.length];
        int length = 0, i = 0;
        for (int coin : coins) {
            temp[i] = money / coin;
            length += temp[i++];
            money = money % coin;
        }
        int[] result = new int[length];
        length = 0;
        for (i = 0; i < coins.length; i++) {
            int[] change = new int[temp[i]];
            Arrays.fill(change, coins[i]);
            System.arraycopy(change, 0, result, length, temp[i]);
            length += temp[i];
        }
        return result;
    }

    private void takeMoney() {
        money += input.accept("Внесите деньги", p1);
        System.out.println("Вы внесли " + money + "рублей.");
    }

    private void showMenu() {
        int i = 1;
        for (Drink drink : drinks) {
            System.out.println(i++ + ". " + drink.name + " цена: " + drink.price + "рублей");
        }
    }

    private Predicate<Integer> p1 = i -> i > 0;

    private Predicate<Integer> p11 = i -> (i > 0) & (i <= drinks.size());

}
