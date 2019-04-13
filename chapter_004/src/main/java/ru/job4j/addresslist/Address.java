package ru.job4j.addresslist;

public class Address implements Comparable<Address> {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public int getHome() {
        return this.home;
    }

    public int getApartment() {
        return this.apartment;
    }

    @Override
    public String toString() {
        return String.format("City: %s, Street: %s, Home: %s, Apartment: %s.",
                this.city,
                this.street,
                this.home,
                this.apartment
        );
    }

    @Override
    public int compareTo(Address o) {
        return this.city.compareTo(o.city);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result;
        result = this.city.hashCode();
        result = prime * result + this.street.hashCode();
        result = prime * result + Integer.hashCode(this.apartment);
        result = prime * result + Integer.hashCode(this.home);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        if (!this.city.equals(other.city)) {
            return false;
        }
        if (!this.street.equals(other.street)) {
            return false;
        }
        if (this.home != other.home) {
            return false;
        }
        if (this.apartment != other.apartment) {
            return false;
        }
        return true;
    }
}
