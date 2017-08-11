package lambdas;

import java.util.Arrays;
import java.util.List;

public class AppleWithLambdas {

  public static void main(String[] args) {
 // Create a list of apples
    List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"),
        new Apple(120, "red"), new Apple(130, "light green"), new Apple(150, "dark red"));
    System.out.println("***** Print the apple types *****");
    for(Apple item : inventory){
      item.printApple((Apple a)-> {
        String appleType = null;
        if(a.getWeight()>150){
          appleType = "good apple";
        }else{
          appleType = "bad apple";
        }
        return "Apple : "+a.getWeight()+"grams. This is "+appleType;
      });
    }
    System.out.println("***** Print the apples with colors *****");
    for(Apple item : inventory){
      item.printApple( (Apple a)-> {return a.getColor()+" apple";});// Both the lines are same
      item.printApple( a-> a.getColor()+" apple"); 
    }
  }
  
}
