/*
Hacker.java
example for course material
*/
public class Hacker {
    private String Name;
    private int SkillCounts;

    public void Properties() {
        System.out.println("Name : " + Name);
        System.out.println("Skill Count : " + SkillCounts);
    }

    public String getName() {
        return Name;
    }

    public void setName(String nama) {
        Name = nama;
    }

    public void SetSkillCounts(int jum) {
        SkillCounts = jum;
    }

    public int getSkillCounts() {
        return SkillCounts;
    }

}
