package ru.job4j.storage;

import ru.job4j.storage.product.Food;

import java.util.List;

public class ControlQuality {
    private List<SimpleStorage> storages;

    public ControlQuality(List<SimpleStorage> storages) {
        this.storages = storages;
    }

    public boolean distribute(Food food) {
        boolean result = false;
        for (SimpleStorage s : storages) {
            result = s.add(food);
            if (result) {
                break;
            }
        }
        return result;
    }

//    public List<SimpleStorage> getStorages() {
//        return this.storages;
//    }

}
