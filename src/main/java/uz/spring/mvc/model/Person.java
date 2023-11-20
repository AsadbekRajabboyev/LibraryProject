package uz.spring.mvc.model;

import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Ism bosh kiritildi")
    @Size(min = 2, max = 20, message = "Ism 2tdan kop 20dan kam bolishi kerak ")
    private String name;
    @Min(value = 0, message = " 0dan katta yosh kriiting")
    @Max(value = 100, message = "Dinozavr")
    private int age;



    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
