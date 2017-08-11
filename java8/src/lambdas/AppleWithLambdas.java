package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleWithLambdas {

  public static void main(String[] args) {
    // Create a list of apples
    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
        new Apple(120, "red"), new Apple(130, "light green"), new Apple(150, "dark red"));
    System.out.println("***** Print the apple types *****");
    for (Apple item : inventory) {
      item.printApple((Apple a) -> {
        String appleType = null;
        if (a.getWeight() > 150) {
          appleType = "good apple";
        } else {
          appleType = "bad apple";
        }
        return "Apple : " + a.getWeight() + "grams. This is " + appleType;
      });
    }
    System.out.println("***** Print the apples with colors *****");
    for (Apple item : inventory) {
      item.printApple((Apple a) -> {
        return a.getColor() + " apple";
      });// Both the lines are same
      item.printApple(a -> a.getColor() + " apple");
    }
    System.out.println("***** Filter apples based on criteria *****");
    // Just get me red apples
    List<Apple> redApples =
        appleFilter(inventory, a -> a.getColor().equalsIgnoreCase("red"));
    // Using the forEach(Consumer<? extends Apple>action)
    redApples.forEach(a->System.out.println(a.toString()));

  }

  // This method filters apples based on how Predicate#test is implemented
  static List<Apple> appleFilter(List<Apple> apples, Predicate<Apple> p) {
    List<Apple> filteredApples = new ArrayList<>();
    for (Apple apple : apples) {
      if (p.test(apple)) {
        filteredApples.add(apple);
      }
    }
    return filteredApples;
  }

}
