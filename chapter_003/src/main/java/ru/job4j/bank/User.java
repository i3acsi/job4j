package ru.job4j.bank;

public class User {

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ". Паспорт: " + passport + ".";
    }

    public User(String name) {
        this.name = name;
        passport = "no passport yet";
    }

    @Override
    public int hashCode() {
        return passport.hashCode();
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
        User other = (User) obj;
        return this.passport.equals(other.passport);
    }

    private String name, passport;
}
