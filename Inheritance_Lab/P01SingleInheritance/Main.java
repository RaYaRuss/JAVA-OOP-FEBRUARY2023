package Inheritance_Lab.P01SingleInheritance;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Animal animal = new Animal();
        animal.eat();

        Dog dog =  new Dog();
        dog.bark();
        dog.eat();

         Puppy puppy = new Puppy();
         puppy.weep();
         puppy.bark();
         puppy.eat();

         Cat cat = new Cat();
         cat.meow();
         cat.eat();


    }
}
