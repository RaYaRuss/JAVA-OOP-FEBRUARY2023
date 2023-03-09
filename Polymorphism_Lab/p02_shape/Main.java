package Polymorphism_Lab.p02_shape;

public class Main {
    public static void main(String[] args) {

        Shape rectangle = new Rectangle(4.5, 5.5);

        System.out.println(rectangle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());

        Shape shape = new Circle(10.0);
        System.out.println(shape.calculatePerimeter());

    }

}
