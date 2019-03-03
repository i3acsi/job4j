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
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getTask(), is("test2"));
    }

    @Test
    public void whenFindByNameThenReturnArrayOfItems() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        Item item2 = new Item("test1", "testDescription");
        Item item3 = new Item("test2", "testDescription");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("test1")[0], is(item1));
        assertThat(tracker.findByName("test1")[1], is(item2));
        assertThat(tracker.findByName("test1").length, is(2));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription");
        Item item2 = new Item("test1", "testDescription");
        Item item3 = new Item("test2", "testDescription");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.delete(tracker.findByName("test1")[0].getId()), is(true));
//        assertThat(tracker.findAll()[0], is(item2));
//        assertThat(tracker.findAll()[1], is(item3));
        assertThat(tracker.findAll().length, is(2));
    }
 }