package ru.job4j.tracker;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для отображения в консоли всех существующих заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска заявок по названию.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа меню для добавления комментария к заявке.
     */
    private static final String COMMENT = "6";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "7";
    /**
     * Универсальный разделитель строк.
     */
    private final String ln = System.lineSeparator();

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
    private void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню ");
            if (answer.equals(ADD)) {
                this.createItem();
            } else if (answer.equals(SHOW)) {
                this.show();
            } else if (answer.equals(EDIT)) {
                this.edit();
            } else  if (answer.equals(DELETE)) {
                this.delete();
            } else  if (answer.equals(FIND_BY_ID)) {
                this.findById();
            } else  if (answer.equals(FIND_BY_NAME)) {
                this.findByName();
            } else  if (answer.equals(COMMENT)) {
                this.comment();
            } else if (answer.equals(EXIT)) {
                exit = true;
            }
        }

    }

    /**
     * Метод выводящий на экран основное меню.
     */
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

    /**
     * Метод для добавления новой заявки..
     */
    private void createItem() {
        System.out.println("----------Добавление новой заявки----------");
        String task = this.input.ask("Введите название заявки ");
        String desc = this.input.ask("Введите описание заявки ");
        System.out.println("-------------------------------------------");
        Item temp = new Item(task, desc);
        this.tracker.add(temp);
        System.out.println("Заявка добавлена." + temp.show());
    }

    /**
     * Метод для отображения в консоли всех существующих заявок.
     */
    private void show() {
        Item[] temp = this.tracker.findAll();
        if (temp.length != 0) {
            System.out.println("----------Все существующие заявки---------");
            for (Item item : temp) {
                System.out.println(item.show());
            }
            System.out.println("------------------------------------------");
        } else {
            System.out.println("----------------Заявок нет----------------");
        }
    }

    /**
     * Метод для редактирования заявки.
     */
    private void edit() {
        System.out.println("----------Редактирование заявки----------");
        String id = this.input.ask("Введите id редактируемой заявки ");
        Item temp = tracker.findById(id);
        if (temp != null) {
            String task = this.input.ask("Введите новое название заявки ");
            String desc = this.input.ask("Введите новое описание заявки ");
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

    /**
     * Метод для удаления заявки.
     */
    private void delete() {
        System.out.println("----------Удаление заявки----------");
        String id = this.input.ask("Введите id удаляемой заявки ");
        if (this.tracker.delete(id)) {
            System.out.printf("-------Заявка с ID %s удалена------" + ln, id);
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
    }

    /**
     * Метод для поиска заявки по ID.
     */
    private void findById() {
        System.out.println("----------Поиск заявки по ID----------");
        String id = this.input.ask("Введите id искомой заявки ");
        Item temp = tracker.findById(id);
        if (temp != null) {
            System.out.println("Найдена заявка " + temp.show());
        } else {
            System.out.println("---------Заявка не найдена---------");
        }
    }

    /**
     * Метод для поиска заявок по названию.
     */
    private void findByName() {
        System.out.println("----------Поиск заявок по названию----------");
        String task = this.input.ask("Введите название заявки для поиска ");
        Item[] temp = tracker.findByName(task);
        if (temp.length != 0) {
            System.out.println("Найденные заявки: ");
            for (Item item : temp) {
                System.out.println(item.show());
            }
        } else {
            System.out.println("---Заявок с таким названием не найдено---");
        }
    }

    /**
     * Метод для добавления комментария к заявке.
     */
     private void comment() {
         System.out.println("----------Комментарий к заявке по ID----------");
         String id = this.input.ask("Введите id комментируемой заявки ");
         Item temp = tracker.findById(id);
         String comment = null;
         if (temp != null) {
             comment = this.input.ask("Введите комментарий к заявке ");
             temp.setComments(comment);
             System.out.println("Комментакрий добавлен: " + temp.show());
         } else {
             System.out.println("-----Заявки с таким ID не существует-----");
         }
     }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}


