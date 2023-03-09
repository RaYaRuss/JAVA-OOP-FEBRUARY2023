package Polymorphism_Lab.p02_shape;

public class Rectangle extends  Shape{
    private  Double width;
    private Double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Rectangle(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return this.width * this.height;
    }

    @Override
    public double calculatePerimeter() {
        return (2 * width + 2 * height);
    }
}
