package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    private final String menu = ("0. Add new Item." + ln
            + "1. Find all items." + ln
            + "2. Edit Item." + ln
            + "3. Delete Item." + ln
            + "4. Find Item by ID." + ln
            + "5. Find Items by Name." + ln
            + "6. Comment." + ln
            + "7. Exit." + ln);
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
     * Тест поиска заявки по ID.
     */
    @Test
    public void whenFindByIDThenItemShown() {
        Input input = new StubInput(new String[]{"4", id, "y"});
        new StartUI(input, tracker).init();
        assertThat(this.out.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявки по ID----------" + ln)
                                .append("Найдена заявка " + item.show() + ln)
                                .toString()
                )
        );

    }

    /**
     * Тест поиска заявок по названию задачи.
     */
    @Test
    public void whenFindTaskThenItemsShown() {
        Item[] ex = {
                tracker.add(new Item("testEx", "testDescription")),
                tracker.add(new Item("testEx", "testDescription"))
        };
        Input input = new StubInput(new String[]{"5", "testEx", "y"});
        new StartUI(input, tracker).init();
        assertThat(this.out.toString(),
                is(
                        new StringBuilder()
                                .append(menu)
                                .append("----------Поиск заявок по названию----------" + ln)
                                .append("Найденные заявки: " + ln)
                                .append(ex[0].show() + ln)
                                .append(ex[1].show() + ln)
                                .toString()
                )
        );

    }

    /**
     * Тест отображения всех заявок.
     */
    @Test
    public void whenShowThenAllItemsShown() {
        Input input = new StubInput(new String[]{"1", "y"});
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
                                .toString()
                )
        );
    }

    /**
     * Тест добавления нового элемнета.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[3].getTask(), is("test name"));
    }


    /**
     * Тест редактирования заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Input input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getTask(), is("test replace"));
    }

    /**
     * Тест удаления заявки.
     */
    @Test
    public void whenDeleteThenTrackerHasNoValue() {
        Input input = new StubInput(new String[]{"3", id, "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тест комментария к заявке.
     */
    @Test
    public void whenFindByIdThenTrackerHasItem() {
        Input input = new StubInput(new String[]{"6", id, "comment", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getComments(), is("comment"));
    }
}
