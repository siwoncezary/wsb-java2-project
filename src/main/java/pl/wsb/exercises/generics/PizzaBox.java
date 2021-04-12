package pl.wsb.exercises.generics;

public class PizzaBox<T extends Pizza> {
    private final T pizza;

    public PizzaBox(T pizza) {
        this.pizza = pizza;
    }

    public T getPizza() {
        return pizza;
    }

    public boolean isSameName(PizzaBox<?> otherBox){
        return pizza.getName().equals(otherBox.pizza.getName());
    }
}
