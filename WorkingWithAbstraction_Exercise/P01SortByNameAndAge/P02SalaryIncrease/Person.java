package WorkingWithAbstraction_Exercise.P01SortByNameAndAge.P02SalaryIncrease;

import java.text.DecimalFormat;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

     Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public Person(String firstName, String lastName, int age, double salary) {
        this(firstName, lastName, age);
        this.salary = salary;
    }

     String getFirstName() {
        return firstName;
    }

     String getLastName() {
        return lastName;
    }

     int getAge() {
        return age;
    }
    public void increaseSalary(double bonus) {
         double actualBonus = bonus;
         if (age < 30) {
             actualBonus /= 2;
         }

         salary = salary * (1 + actualBonus / 100);
    }

    public double getSalary() {
        return salary;
    }
    //    @Override
//    public String toString() {
//        return String.format("%s %s is %d years old.", firstName,lastName, age);
//    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.0##");
        return String.format("%s %s gets %s leva", firstName, lastName, df.format(salary));
    }
}
