package ru.job4j.tracker;

/**
 * Класс для поиска заявки по ID.
 */
public class FindById imlements UserAction {
    @Override
	public int key() {
	    return FIND_BY_ID;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    System.out.println("----------Поиск заявки по ID----------");
        String id = this.input.ask("Введите id искомой заявки ");
        Item temp = tracker.findById(id);
        if (temp != null) {
            System.out.println("Найдена заявка " + temp.show());
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
	}
	
	@Override
    public String info() {
        return "Find Item by ID.";
    }
}