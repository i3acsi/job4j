package ru.job4j.tracker;

/**
 * Класс для добавления комментария к заявке.
 */
public class Exit imlements UserAction {
    @Override
	public int key() {
	    return EXIT;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	    exit = true;
	}
	
	@Override
    public String info() {
        return "Exit.";
    }
}