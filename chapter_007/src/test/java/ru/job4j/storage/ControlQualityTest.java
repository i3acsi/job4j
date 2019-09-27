package ru.job4j.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.storage.product.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private List<Food> foods;
    private ControlQuality controlQuality;
    private LocalDate now;
    private List<SimpleStorage> storages;

    @Before
    public void init() {
        this.now = LocalDate.now();
        this.initFood();
        this.initStorage();
        this.fillStorage();
    }

    private void initFood() {
        foods = new ArrayList<>();
        Food yog1 = new Yogurt("yog1",
                now.plusWeeks(1),
                now.minusDays(15).minusMonths(1),
                54.5);
        foods.add(yog1);
        Food yog2 = new Yogurt("yog2",
                now.plusMonths(2),
                now.minusDays(13),
                46.6);
        foods.add(yog2);
        Food ice1 = new IceCream("ice1",
                now.plusMonths(3).minusWeeks(2),
                now.minusMonths(1),
                35.7);
        foods.add(ice1);
        Food bread1 = new Bread("bread1",
                now.plusDays(10),
                now,
                31.8);
        foods.add(bread1);
        Food bread2 = new Bread("bread2",
                now.plusDays(1),
                now.minusDays(9),
                32);
        foods.add(bread2);
        Food bread3 = new Bread("bread3",
                now.minusDays(2),
                now.minusDays(14),
                32);
        foods.add(bread3);
        Food ice2 = new IceCream("ice2",
                now.plusMonths(2).minusWeeks(2),
                now.minusDays(5),
                45.6);
        foods.add(ice2);
        Food carrot = new Carrot("carrot1",
                LocalDate.of(2020, 8, 3),
                LocalDate.of(2019, 9, 3),
                20);
        foods.add(carrot);
        Food cracker = new Cracker("cracker1",
                now.plusMonths(5),
                now.minusWeeks(2),
                21.5);
        foods.add(cracker);
        Food badYogurt = new Yogurt("badYog3",
                now.minusDays(4),
                now.minusMonths(1).minusWeeks(2),
                45.5);
        foods.add(badYogurt);
    }

    private void initStorage() {
        SimpleStorage warehouse = new Warehouse();
        SimpleStorage shop = new Shop();
        SimpleStorage trash = new Trash();

        storages = List.of(warehouse, shop, trash);
        controlQuality = new ControlQuality(storages);
    }

    private void fillStorage() {
        for (Food f : foods) {
            controlQuality.distribute(f);
        }
    }

    @After
    public void clear() {
        System.out.println("start 'after'");
        storages.forEach(storage -> {
            System.out.println(storage);
            storage.findAll().forEach(System.out::println);
        });
        foods.clear();
    }

    @Test
    public void test() {
        SimpleStorage warehouse = storages.get(0);
        System.out.println("Warehouse:");
        warehouse.findAll().forEach(food -> {
            System.out.println(food);
            double expDate = food.getExpDate();
            double lifetime = food.getLifeTime();
            assertThat((lifetime / expDate) < 0.25, is(true));
        });

        SimpleStorage shop = storages.get(1);
        System.out.println("Shop:");
        shop.findAll().forEach(food -> {
            System.out.println(food);
            double expDate = food.getExpDate();
            double lifetime = food.getLifeTime();
            double dateRelation = lifetime / expDate;
            assertThat((dateRelation >= 0.25 && dateRelation < 1), is(true));
            if (dateRelation > 0.75) {
                assertThat(food.getDiscount() > 0, is(true));
            }
        });

        SimpleStorage trash = storages.get(2);
        System.out.println("Trash:");
        trash.findAll().forEach(food -> {
            System.out.println(food);
            double expDate = food.getExpDate();
            double lifetime = food.getLifeTime();
            double dateRelation = lifetime / expDate;
            assertThat((dateRelation >= 1), is(true));
        });

    }
}
