package ru.job4j.tracker;

/**
 * Класс для отображения в консоли всех существующих заявок.
 */
public class FindAll imlements UserAction {
    @Override
	public int key() {
	    return SHOW;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    Item[] temp = tracker.findAll();
        if (temp.length != 0) {
            System.out.println("----------Все существующие заявки---------");
            for (Item item : temp) {
                System.out.println(item.show());
            }
            System.out.println("------------------------------------------");
        } else {
            System.out.println("----------------Заявок нет----------------");
        }
	}
	
    @Override
    public String info() {
        return "Find all items.";
    }
 }