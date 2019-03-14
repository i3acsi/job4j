package ru.job4j.tracker;

/**
 * Класс для добавления новой заявки.
 */
public class AddItem implements UserAction {
    private int key;
    private String info;

    public AddItem(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
	public int key() {
	    return key;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
        System.out.println("----------Добавление новой заявки----------");
        String task = input.ask("Введите название заявки ");
        String desc = input.ask("Введите описание заявки ");
        System.out.println("-------------------------------------------");
        Item temp = new Item(task, desc);
        tracker.add(temp);
        System.out.println("Заявка добавлена." + temp.show());
	}
	
	@Override
    public String info() {
        return info;
    }
}