package ru.job4j.tracker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackerMockTest {
    private String ln = System.lineSeparator();
    private ITracker tracker = Tracker.getInstance();
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
        tracker.clean();
    }

    @Test
    public void whenAddNewItemThenTrackerHasIt() {
        Input input = mock(Input.class);
        String itemName = "new item test";
        when(input.ask("Введите название заявки ")).thenReturn(itemName);
        String itemDesc = "new item test desc";
        when(input.ask("Введите описание заявки ")).thenReturn(itemDesc);
        UserAction action = new AddItem(1, "test info");
        action.execute(input, tracker);
        Item result = tracker.findAll().get(0);
        String expected = "----------Добавление новой заявки----------\r\n"
                + "-------------------------------------------\r\n"
                + "Заявка добавлена." + result.show() + "\r\n";
        Assert.assertEquals(mem.toString(), expected);
        Assert.assertEquals(result.getTask(), itemName);
        Assert.assertEquals(result.getDesk(), itemDesc);
    }

    @Test
    public void whenDeleteItemThenTrackerDontHaveIt() {
        String task = "test task";
        String desc = "test desc";
        Item item = new Item(task, desc);
        String id = tracker.add(item).getId();
        Input input = mock(Input.class);
        when(input.ask("Введите id удаляемой заявки ")).thenReturn(id);
        UserAction action = new Delete(1, "test info");
        action.execute(input, tracker);
        List<Item> result = tracker.findAll();
        String expected = String.format("----------Удаление заявки----------" + ln
                + "-------Заявка с ID %s удалена------" + ln, id);
        Assert.assertEquals(mem.toString(), expected);
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void whenFindByIdThenOnExistReturnItem() {
        Input input = mock(Input.class);
        String task = "test task";
        String desc = "test desc";
        Item item = new Item(task, desc);
        String id = tracker.add(item).getId();
        when(input.ask("Введите id искомой заявки ")).thenReturn(id);
        UserAction action = new FindById(1, "test info");
        action.execute(input, tracker);
        String expected = "----------Поиск заявки по ID----------\r\n"
                + "Найдена заявка " + item.show() + ln;
        Assert.assertEquals(mem.toString(), expected);
    }

    @Test
    public void whenFindByNameThenOnExistReturnItem() {
        Input input = mock(Input.class);
        String task = "test task";
        String desc = "test desc";
        Item item = new Item(task, desc);
        item = tracker.add(item);
        when(input.ask("Введите название заявки для поиска ")).thenReturn(task);
        UserAction action = new FindByName(1, "test info");
        action.execute(input, tracker);
        String expected = "----------Поиск заявок по названию----------" + ln
                + "Найденные заявки: " + ln + item.show() + ln;
        Assert.assertEquals(mem.toString(), expected);
    }
}
