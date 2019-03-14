package ru.job4j.tracker;

/**
 * Класс для добавления комментария к заявке.
 */
public class Exit implements UserAction {
    @Override
	public int key() {
	    return 7;
	}
	
	@Override
	public void execute(Input input, Tracker tracker) {
	}
	
	@Override
    public String info() {
        return "Exit.";
    }
}