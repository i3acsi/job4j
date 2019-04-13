package ru.job4j.addresslist;

import org.junit.Before;
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
            add(new Address("Krasnoyarsk", "Pushkina", 32, 67));
            add(new Address("Novosibirsk", "Lenina", 10, 11));
        }
    };
    private static String[] names = new String[]{
            "Vailiy", "Vladimir", "Anatoliy", "Olga", "Masha", "Sergey"
    };
    private static List<Profile> profileList = new ArrayList<>();
    private static final Random RN = new Random();

    @Before
    public void clean() {
        addresses.clear();
    }

    @Test
    public void collectTest() {
        int i = 0;
        addresses.forEach(x -> profileList.add(new Profile(x, names[i], RN.nextInt(100))));
        Profiles profiles = new Profiles(profileList);
        List<Address> result = profiles.collect();
        assertThat(result, is(addresses));
    }

    @Test
    public void collectSortedTest() {

        int i = 0;
        addresses.forEach(x -> profileList.add(new Profile(x, names[i], RN.nextInt(100))));
        Profiles profiles = new Profiles(profileList);
        List<Address> result = profiles.collectSorted();
        for (int j = 0; j < addresses.size() - 1; j++) {
            int temp = addresses.get(j).getCity().compareTo(addresses.get(j + 1).getCity());
            System.out.println(addresses.get(j).getCity() + " " + addresses.get(j + 1).getCity() + " " + temp);
            assert (temp > 0);
        }
    }
}
