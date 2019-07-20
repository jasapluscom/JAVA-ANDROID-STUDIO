/*
java class example for course material
*/
class Bird {
    String wakeup_time = "06:00 am";
    String sleep_time = "06:00 pm";

    public void WakeUp() {
        System.out.println("\nChirp chirp I wake up at " + wakeup_time);
    }

    public void Sleep() {
        System.out.println("\nChirp chirp ... sleep at " +  sleep_time);
    }

    public static void main(String args[])
    {
        Bird bird = new Bird();
        bird.WakeUp();
        bird.Sleep();
    }
}
