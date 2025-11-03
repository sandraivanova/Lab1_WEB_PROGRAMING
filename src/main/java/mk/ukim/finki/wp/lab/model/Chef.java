package mk.ukim.finki.wp.lab.model;

import java.util.ArrayList;
import java.util.List;

public class Chef {
    Long id;
    String firstName;
    String lastName;
    String bio;
    List<Dish> dishes;

    public Chef(Long id, String firstName, String lastName, String bio) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dishes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
