package lambdas;

public class StrategyDesignPattern {

  public static void main(String[] args) {
   
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

  public void printApple(AppleFormatter formatter) {
    System.out.println(formatter.format(this));
  }
}
interface AppleFormatter {
  public String format(Apple apple);
}
