package ru.job4j.tracker;

/**
 * Класс для удаления заявки.
 */
public class Delete imlements UserAction {
	private final String ln = System.lineSeparator();
	 
    @Override
	public int key() {
	    return DELETE;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    System.out.println("----------Удаление заявки----------");
        String id = input.ask("Введите id удаляемой заявки ");
        if (tracker.delete(id)) {
            System.out.printf("-------Заявка с ID %s удалена------" + ln, id);
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
	}
	
	@Override
    public String info() {
        return "Delete Item.";
    }
}