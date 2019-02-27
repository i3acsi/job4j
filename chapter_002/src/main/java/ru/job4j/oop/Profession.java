package ru.job4j.oop;

public class Profession {
    private String name;
    private String profession;

    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}

class Doctor extends Profession {
    public Doctor(String name, String profession) {
        super(name, profession);
    }

    public void heal(Patient patient) {
        patient.setHealthy();
    }
}

class Patient {
    private String name;
    private boolean isHealthy;

    public Patient(String name, boolean isHealthy) {
        this.name = name;
        this.isHealthy = isHealthy;
    }

    public void setHealthy() {
        this.isHealthy = true;
    }

    public String getName() {
        return name;
    }

    public boolean getIsHealthy() {
        return isHealthy;
    }
}

class Engineer extends Profession {
    public Engineer(String name, String profession) {
        super(name, profession);
    }

    public void build(House house) {
        house.setConstructed();
    }
}

class House {
    private String address;
    private boolean isConstructed;

    public House(String address, boolean isConstructed) {
        this.isConstructed = isConstructed;
        this.address = address;
    }

    public void setConstructed() {
        this.isConstructed = true;
    }

    public String getAddress() {
        return address;
    }

    public boolean getConstructed() {
        return isConstructed;
    }
}

class Teacher extends Profession {
    public Teacher(String name, String profession) {
        super(name, profession);
    }

    public void teach(Student student) {
        student.setGraduated();
    }
}

class Student {
    private String name;
    private boolean isGraduated;

    public Student(String name, boolean isGraduated) {
        this.isGraduated = isGraduated;
        this.name = name;
    }

    public void setGraduated() {
        this.isGraduated = true;
    }

    public String getName() {
        return name;
    }

    public boolean getGraduated() {
        return this.isGraduated;
    }
}

