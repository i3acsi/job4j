package ru.job4j.tracker;

/**
 * Класс для удаления заявки.
 */
public class Delete implements UserAction {
	private final String ln = System.lineSeparator();
    private int key;
    private String info;

    public Delete(int key, String info) {
        this.key = key;
        this.info = info;
    }
	 
    @Override
	public int key() {
	    return key;
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
        return info;
    }
}