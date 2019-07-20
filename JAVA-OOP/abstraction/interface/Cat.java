/*
Cat.java
example for course material
*/

public class Cat implements Animal {
    int peetime = 0;
    
    public void Pee(int num) {
        peetime = num + 1;
        System.out.println("pee time :" + peetime);
    }
    public void Sound() {
        System.out.println("meow meow");
    }
    public static void main(String []args) {
        Cat kattie = new Cat();
        kattie.Sound();
        kattie.Pee(3);
    }
}    
