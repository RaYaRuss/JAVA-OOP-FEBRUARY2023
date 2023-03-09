package Polymorphism_Lab.p03Animals;

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Cat("Tommy", "Jerry");
        System.out.println(animal1.explainSelf());
        Animal animal2 = new Dog("Rex", "Bone");
        System.out.println(animal2.explainSelf());
    }
}
