package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrategyDesignPattern {

  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
        new Apple(120, "red"), new Apple(200, "dark red"), new Apple(220, "lite green"));
    for (Apple apple : inventory) {
      apple.printApple(new AppleColorPrint());
      apple.printApple(new AppleWeightPrint());
      apple.printApple(new ApplePrettyPrint());
    }
    for (Apple apple : inventory) {
      apple.printApple(new AppleFormatter() {

        @Override
        public String format(Apple apple) {
          String appleType = null;
          if (apple.getWeight() > 150) {
            appleType = "good apple";
          } else {
            appleType = "bad apple";
          }
          return "Apple : " + apple.getWeight() + "grams. This is " + appleType;
        }

      });
    }
    // Get me red apples
    List<Apple> redApples = new ArrayList<>();
    for (Apple a : inventory) {
      if ("red".equalsIgnoreCase(a.getColor())) {
        redApples.add(a);
      }
    }
    for (Apple a : redApples) {
      a.printApple(new ApplePrettyPrint());
    }
  }

}


class Apple {
  private Integer weight = 0;
  private String color = "";

  public Apple(Integer weight, String color) {
    super();
    this.weight = weight;
    this.color = color;
  }

  public Apple(Integer weight) {
    this.weight = weight;
  }

  public Apple(String color) {
    this.color = color;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Apple [weight=" + weight + ", color=" + color + "]";
  }

  public void printApple(AppleFormatter formatter) {
    System.out.println(formatter.format(this));
  }
}


interface AppleFormatter {
  public String format(Apple apple);
}


class AppleColorPrint implements AppleFormatter {
  @Override
  public String format(Apple apple) {
    String s = apple.getColor() + " color apple";
    return s;
  }
}


class AppleWeightPrint implements AppleFormatter {
  @Override
  public String format(Apple apple) {
    String s = "Apple thats weights : " + apple.getWeight() + " grams";
    return s;
  }
}


class ApplePrettyPrint implements AppleFormatter {
  @Override
  public String format(Apple apple) {
    String s =
        "The apple is of " + apple.getColor() + " color, weighing " + apple.getWeight() + " grams";
    return s;
  }
}
