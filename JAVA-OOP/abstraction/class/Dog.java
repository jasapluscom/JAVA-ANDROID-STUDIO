/*
Dog.java
example for course material
*/
public class Dog extends Animal {
    public void Sound() {
        System.out.println("guk guk guk");
    }
    public static void main(String []args) {
        Dog doggy = new Dog();
        doggy.Sound();
    }

}    
