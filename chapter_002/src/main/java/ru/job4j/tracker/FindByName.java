package ru.job4j.tracker;

/**
 * Класс для поиска заявок по названию.
 */
public class FindByName implements UserAction {
    private int key;
    private String info;

    public FindByName(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
	public int key() {
	    return key;
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
	
	@Override
    public String info() {
        return info;
    }
}