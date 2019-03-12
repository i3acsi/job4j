package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class StartUITest {
    private final String ln = System.lineSeparator();
    private final String menu = ("0. Add new Item" + ln
                                + "1. Show all items" + ln
                                + "2. Edit item" + ln
                                + "3. Delete item" + ln
                                + "4. Find item by Id" + ln
                                + "5. Find items by name" + ln
                                + "6. Add comment" + ln
                                + "7. Exit Program" + ln);
    private final Tracker tracker = new Tracker();
    private final Item item = tracker.add(new Item("test name", "desc"));
    private final Item item2 = tracker.add(new Item("test name 2", "desc2"));
    private final Item item3 = tracker.add(new Item("test name 3", "desc3"));
    private final String id = item.getId();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Тест отображения всех заявок.
     */
    @Test
    public void whenShowThenAllItemsShown() {
        Input input = new StubInput(new String[]{"1", "7"});
        new StartUI(input, tracker).init();
        assertThat(
                this.out.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Все существующие заявки---------" + ln)
                                .append(item.show() + ln)
                                .append(item2.show() + ln)
                                .append(item3.show() + ln)
                                .append("------------------------------------------" + ln)
                                .append(menu)
                                .toString()
                )
        );

    }

    /**
     * Тест добавления нового элемнета.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[3].getTask(), is("test name"));
    }


    /**
     * Тест редактирования заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
//        Tracker tracker = new Tracker();
//        Item item = tracker.add(new Item("test name", "desc"));
//        Item item2 = tracker.add(new Item("test name 2", "desc2"));
//        Item item3 = tracker.add(new Item("test name 3", "desc3"));
//        String id = item.getId();
        Input input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getTask(), is("test replace"));
    }

    /**
     * Тест удаления заявки.
     */
    @Test
    public void whenDeleteThenTrackerHasNoValue() {
//        Tracker tracker = new Tracker();
//        Item item = tracker.add(new Item("test name", "desc"));
//        Item item2 = tracker.add(new Item("test name 2", "desc2"));
//        Item item3 = tracker.add(new Item("test name 3", "desc3"));
//        String id = item.getId();
        Input input = new StubInput(new String[]{"3", id, "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тест комментария к заявке.
     */
    @Test
    public void whenFindByIdThenTrackerHasItem() {
//        Tracker tracker = new Tracker();
//        Item item = tracker.add(new Item("test name", "desc"));
//        Item item2 = tracker.add(new Item("test name 2", "desc2"));
//        Item item3 = tracker.add(new Item("test name 3", "desc3"));
//        String id = item.getId();
        Input input = new StubInput(new String[]{"6", id, "comment", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getComments(), is("comment"));
    }
}
