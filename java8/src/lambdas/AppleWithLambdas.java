package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
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
    // Create a lambda expression to display apples in the console
    // This uses the Consumer interface
    Consumer<List<Apple>> applesConsoleDisplay = (appleList) -> {
      for (Apple apple : appleList) {
        System.out.println(apple);
      }
    };
    System.out.println("-----All apples-----");
    consumeList(inventory, applesConsoleDisplay);
    System.out.println("***** Filter apples based on criteria *****");
    // Just get me red apples
    // Here we are using the Predicate interface provided by the JDK. This interface is called
    // functional interface
    List<Apple> redAppleList = appleFilter(inventory, a -> a.getColor().equalsIgnoreCase("red"));
    // Using the forEach(Consumer<? extends Apple>action)
    redAppleList.forEach(a -> System.out.println(a.toString()));
    // Method references
    redAppleList.forEach(System.out::println);
    // Predefined predicates
    Predicate<Apple> redApples = redApple -> redApple.getColor().equalsIgnoreCase("red");
    Predicate<Apple> greenApples = greenApple -> greenApple.getColor().equalsIgnoreCase("green");
    Predicate<Apple> moreThan150 = heavyApple -> heavyApple.getWeight() > 150;
    System.out.println("*** Getting NOT GREEN apples **");
    appleFilter(inventory, greenApples.negate()).forEach(System.out::println);
    System.out.println("*** Getting apples that are either GREEN or RED **");
    appleFilter(inventory, redApples.or(greenApples)).forEach(System.out::println);
    System.out.println("*** Getting apples that are GREEN and more than 150kg**");
    appleFilter(inventory, greenApples.and(moreThan150)).forEach(System.out::println);
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

  // This method accepts a list and a consumer and perform operations implemented on the consumer
  static <T> void consumeList(List<T> list, Consumer<List<T>> consumer) {
    consumer.accept(list);
  }


}
