/*
Cat.java
example for course material
*/

public class Cat extends Animal {

    public void Sound() {
        System.out.println("meow meow meow");
    }

    public static void main(String []args) {
        Cat kattie = new Cat();
        kattie.Sound();
    }
}

