package InterfacesAndAbstraction_Lab.P03SayHello;

public interface Person {
    String getName();


    default String sayHello() {
        return "Hello!";
    }

}
