/*
java class example for course material
*/

class Kucing {

    String name;
    int age;

    /* class constructor */
    public Kucing(String name_param, int age_param) {
        this.name = name_param;
        this.age = age_param;
    }

    public void Meow() {
        System.out.println("meow...");
    }

    public void PrintMyProperties() {
        System.out.println("My name is " + this.name);
        System.out.println("My age is " + this.age);
    }

    public void Breed() {
        System.out.println("Give birth");
    }

    public void Defecate() {
        System.out.println("Discharge");
    }

    public void Urinate() {
        System.out.println("pee piss");
    }

    public static void main(String args[])
    {
        Kucing kampung = new Kucing("Pussy", 5);
        Kucing anggora = new Kucing("Katy", 7);

        kampung.PrintMyProperties();
        anggora.PrintMyProperties();

        kampung.Breed();
        anggora.Urinate();

        kampung.Defecate();
        anggora.Meow();
    }
}
