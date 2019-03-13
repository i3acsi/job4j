package ru.job4j.tracker;

/**
 * Класс для редактирования заявки.
 */
public class Edit imlements UserAction {
    @Override
	public int key() {
	    return EDIT;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	System.out.println("----------Редактирование заявки----------");
        String id = input.ask("Введите id редактируемой заявки ");
        Item temp = tracker.findById(id);
        if (temp != null) {
            String task = input.ask("Введите новое название заявки ");
            String desc = input.ask("Введите новое описание заявки ");
            String newComment = null;
            if (!temp.getComments().equals("")) {
                newComment = this.input.ask("Введите новый коииентарий к заявке ");
            }
            System.out.println("------------------------------------------");
            Item item = new Item(task, desc);
            tracker.replace(id, item);
            if (newComment != null) {
               temp.setComments(newComment);
            }
            System.out.print("Заявка отредактированна." + temp.show());
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
	}
	
    @Override
    public String info() {
        return "Edit Item.";
    }
}