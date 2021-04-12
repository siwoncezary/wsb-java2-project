package pl.wsb.exercises.generics;

public class PizzaDemo {
    public static void main(String[] args) {
        PizzaBox<Pizza> box = new PizzaBox<>(new Pizza("marger"));
        PizzaBox<PepperoniPizza> pizzaBox = new PizzaBox<>(new PepperoniPizza("margerita"));
        boolean sameName = box.isSameName(pizzaBox);
        System.out.println(sameName);
    }
}
