/*
Dog.java
example for course material
*/
public class Dog implements Animal {
    int peetime = 0;
    
    public void Pee(int num) {
        peetime = num;
        System.out.println("pee time :" + peetime);
    }
    public void Sound() {
        System.out.println("guk guk");
    }
    
    public static void main(String []args) {
        Dog doggie = new Dog();
        doggie.Sound();
        doggie.Pee(3);
    }
    

}    
