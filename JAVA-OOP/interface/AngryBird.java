/*
java interface example
*/

interface bird {
    void fly();
}

class AngryBird implements bird {
    public void fly() {
        System.out.println("\nfly implementation\n");
    }

    public void echo() {
        System.out.println("\nchirp chirp\n");
    }

    public static void main (String[] args) {
        AngryBird ab = new AngryBird();
        ab.fly();
        ab.echo();
    }
}
