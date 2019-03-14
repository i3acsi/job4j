package ru.job4j.tracker;

public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Создание хранилища для заявок.
     */
    private final Tracker tracker;

    /**
     * Конструктор с инициалицацией полей.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int length = menu.getActionsLentgh();
        int[] range = new int[length];
        for (int i = 0; i < length; i++) {
            range[i] = range[i] + i;
        }
        do {
            menu.show();
            menu.select(input.ask("select", range));
        } while (!"y".equals(this.input.ask("Exit?(y) ")));
    }


    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(
		    new ValidateInput(
			    new ConsoleInput()
		        ),
				new Tracker()
				).init();
    }
}


