package ru.job4j.tracker;

class StubInput implements Input {
    /**
     * Поле value содержит послел\довательность
     * ответоов пользователя. Например для добавлния новой заявки
     * первое значение  последовательности - 0,
     * далее название заявки - task name
     * и описание заявки - description.
     * Последовательность {"0", "task name", "description"}
     */
    private final String[] value;

    /**
     * Счетчик вызовов метода ask
     */
    private int position;

    /**
     * Конструктор с инициализацией value
     * @param value
     */
    StubInput(String[] value) {
        this.value = value;
    }

    /**
     * При вызове метода ask - возвращается значение, которое хранится
     * по адресу value с нулевым индексом
     * и происходит инкремент счетчика, следовательно следующий вызов метода
     * ask вернет значение следующео элемента value
     * @param question
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        //throw new UnsupportedOperationException("UnsupportedOperationException");
        return -1;
    }
}
