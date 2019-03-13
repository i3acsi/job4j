package ru.job4j.tracker;

/**
 * Класс для добавления новой заявки.
 */
public class AddItem implements UserAction {
    @Override
	public int key() {
	    return ADD;
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
        return "Add new Item.";
    }
}