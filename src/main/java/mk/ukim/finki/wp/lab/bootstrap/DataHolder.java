package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        dishes.add(new Dish("1", "Pasta Carbonara", "Italian", 20));
        dishes.add(new Dish("2", "Beef Wellington", "British", 45));
        dishes.add(new Dish("3", "Chicken Tikka Masala", "Indian", 35));
        dishes.add(new Dish("4", "Sushi", "Japanese", 50));
        dishes.add(new Dish("5", "Moussaka", "Greek", 40));

        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef"));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "Famous for simple cooking"));
        chefs.add(new Chef(3L, "Massimo", "Bottura", "Innovative Italian chef"));
        chefs.add(new Chef(4L, "Heston", "Blumenthal", "Experimental chef"));
        chefs.add(new Chef(5L, "Nigella", "Lawson", "Comfort food queen"));
    }
}
