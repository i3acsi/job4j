package ru.job4j.tracker;

public interface UserAction {
    /**
     * ����� ���������� ���� �����.
     * @return key - ����
     */
    int key();

    /**
     * �������� �����.
     * @param input - ������ ���� Input.
     * @param tracker - ������ ���� Tracker.
     */
    void execute(Input input, Tracker tracker);

    /**
     * ����� ���������� ���������� � ������ ������ ����.
     * @return info - ������ ����.
     */
    String info();
}