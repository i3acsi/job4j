package ru.job4j.addresslist;

import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
private List<Profile> profiles;

public Profiles(List<Profile> profiles) {
    this.profiles = profiles;
}
    public List<Address> collect() {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    public List<Profile> getProfiles() {
        return profiles;
    }
}
