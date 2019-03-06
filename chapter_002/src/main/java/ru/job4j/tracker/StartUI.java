package ru.job4j.tracker;

public class StartUI {
    private final String ln = System.lineSeparator();
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
        }
    }

    private void showMenu() {
        System.out.println("0. Add new Item"
                + ln + "1. Show all items"
                + ln + "2. Edit item"
                + ln + "3. Delete item"
                + ln + "4. Find item by Id"
                + ln + "5. Find items by name"
                + ln + "6. Add comment"
                + ln + "7. Exit Program"
        );
    }
    public static void main(String[] args) {

        ConsoleInput consoleInput = new ConsoleInput();
        Tracker tracker = new Tracker();

        boolean exit = true;
        while (exit) {

            String answer = consoleInput.ask("Select");
            if (answer.equals("0")) {
                String task = consoleInput.ask("Task");
                String desc = consoleInput.ask("Description");
                tracker.add(new Item(task, desc));
            }
            if (answer.equals("1")) {
                for (Item item: tracker.findAll()) {
                    System.out.println(item.show());
                }
            }
            if (answer.equals("2")) {
                String id = consoleInput.ask("id");
                String task = consoleInput.ask("Task");
                String desc = consoleInput.ask("Description");
                tracker.replace(id, new Item(task, desc));
            }
            if (answer.equals("3")) {
                String id = consoleInput.ask("id");
                tracker.delete(id);
            }
            if (answer.equals("4")) {
                String id = consoleInput.ask("id");
                System.out.println(tracker.findById(id).show());
            }
            if (answer.equals("5")) {
                String task = consoleInput.ask("Task?");
                for (Item item: tracker.findByName(task)) {
                    System.out.println(item.show());
                }
            }
            if (answer.equals("6")) {
                String id = consoleInput.ask("id");
                String comment = consoleInput.ask("comment")
                tracker.findById(id).setComments(comment);
            }
            if (answer.equals("7")) {
                exit = false;
            }
        }
    }
}


