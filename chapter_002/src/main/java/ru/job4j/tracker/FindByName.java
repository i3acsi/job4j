package ru.job4j.tracker;

/**
 * Класс для поиска заявок по названию.
 */
public class FindByName extends  BaseAction {
    
    public FindByName(int key, String info) {
        super(key, info);
    }
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    System.out.println("----------Поиск заявок по названию----------");
        String task = input.ask("Введите название заявки для поиска ");
        Item[] temp = tracker.findByName(task);
        if (temp.length != 0) {
            System.out.println("Найденные заявки: ");
            for (Item item : temp) {
                System.out.println(item.show());
            }
        } else {
            System.out.println("---Заявок с таким названием не найдено---");
        }
	}
}