package ru.job4j.search;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Vasiliy", "Gasevskiy", "3366126", "Novosibirsk"));
        phones.add(new Person("Ivan", "Ivanov", "03", "Bryansk"));
        var persons = phones.find("Bryansk");
        var values = List.of("Arsentev", "Ivanov").iterator();
        persons.forEach(x->assertThat(x.getSurname(), is(values.next())));
    }
}
