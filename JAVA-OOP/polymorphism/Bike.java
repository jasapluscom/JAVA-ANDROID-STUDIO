class Bike extends Vehicle {

    public void PropertiesDisplay() {
        System.out.println("I do not have machine, running by gear");
    }
    public static void main(String args[])
    {
        Bike obj = new Bike();
        
        obj.PropertiesDisplay();
    }

}    
