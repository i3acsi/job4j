package ru.job4j.tracker;

import org.junit.Test;

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
    Tracker tracker = new Tracker();
    Item item = tracker.add(new Item("test name", "desc"));
    Item item2 = tracker.add(new Item("test name 2", "desc2"));
    Item item3 = tracker.add(new Item("test name 3", "desc3"));
    String id = item.getId();

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
        Input input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getTask(), is("test replace"));
    }

    /**
     * Тест удаления заявки.
     */
    @Test
    public void whenDeleteThenTrackerHasNoValue() {
        Input input = new StubInput(new String[]{"3", id, "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Тест комментария к заявке.
     */
    @Test
    public void whenFindByIdThenTrackerHasItem() {
        Input input = new StubInput(new String[]{"6", id, "comment", "7"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(id).getComments(), is("comment"));
    }
}
