package ru.job4j.tracker;

/**
 * Класс для поиска заявки по ID.
 */
public class FindById implements UserAction {
    private int key;
    private String info;

    public FindById(int key, String info) {
        this.key = key;
        this.info = info;
    }
    @Override
	public int key() {
	    return key;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    System.out.println("----------Поиск заявки по ID----------");
        String id = input.ask("Введите id искомой заявки ");
        Item temp = tracker.findById(id);
        if (temp != null) {
            System.out.println("Найдена заявка " + temp.show());
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
	}
	
	@Override
    public String info() {
        return info;
    }
}