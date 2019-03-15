package ru.job4j.tracker;

/**
 * Класс для добавления комментария к заявке.
 */
public class Comment extends  BaseAction {
    
    public Comment(int key, String info) {
        super(key, info);
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
	
}