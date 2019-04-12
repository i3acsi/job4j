package ru.job4j.addresslist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {
    private static List<Address> addresses = new ArrayList<Address>() {
        {
            add(new Address("Novosibirsk", "Lenina", 10, 11));
            add(new Address("Moscow", "Kremlin", 1, 1));
            add(new Address("Saint_Petersburg", "Nevsky", 26, 23));
            add(new Address("Krasnoyarsk", "Pushkina", 32, 67));
        }
    };
    private static String[] names = new String[]{"Vailiy", "Vladimir", "Anatoliy", "Olga"};
    private static List<Profile> profileList = new ArrayList<>();
    private static final Random RN = new Random();

    @Test
    public void convertTest() {
        int i = 0;
        addresses.forEach(x -> profileList.add(new Profile(x, names[i], RN.nextInt(100))));
        Profiles profiles = new Profiles(profileList);
        List<Address> result = profiles.collect();
        assertThat(result, is(addresses));
    }
}
