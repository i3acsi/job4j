package ru.job4j.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Vasiliy", "Gasevskiy", "3366126", "Novosibirsk"));
        phones.add(new Person("Ivan","Ivanov", "03", "Bryansk"));
        List<Person> persons = phones.find("Bryansk");
        String[] values = new String[]{"Arsentev", "Ivanov"};
        int i = 0;
        for (Person x : persons) {
            assertThat(x.getSurname(), is(values[i++]));
        }
    }
}
