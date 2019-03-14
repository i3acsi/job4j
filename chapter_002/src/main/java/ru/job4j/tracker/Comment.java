package ru.job4j.tracker;

/**
 * Класс для добавления комментария к заявке.
 */
public class Comment implements UserAction {
    private int key;
    private String info;

    public Comment(int key, String info) {
        this.key = key;
        this.info = info;
    }
    @Override
	public int key() {
	    return key;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    System.out.println("----------Комментарий к заявке по ID----------");
        String id = input.ask("Введите id комментируемой заявки ");
        Item temp = tracker.findById(id);
        String comment = null;
        if (temp != null) {
            comment = input.ask("Введите комментарий к заявке ");
            temp.setComments(comment);
            System.out.println("Комментакрий добавлен: " + temp.show());
        } else {
            System.out.println("-----Заявки с таким ID не существует-----");
        }
	}
	
	@Override
    public String info() {
        return info;
    }
}