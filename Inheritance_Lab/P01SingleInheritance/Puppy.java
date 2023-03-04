package Inheritance_Lab.P01SingleInheritance;

public class Puppy extends Dog {
    public int age;

    public void weep(){
        walk();
        System.out.println("weeping...");
    }


    @Override
    public void bark() {
        System.out.println("wof-bark-wof");
    }
}
