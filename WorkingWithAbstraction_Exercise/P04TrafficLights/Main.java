package WorkingWithAbstraction_Exercise.P04TrafficLights;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        String[] signals = scanner.nextLine().split("\\s+");
        int iterations = Integer.parseInt(scanner.nextLine());

//        List<TrafficLight> trafficLights = new ArrayList<>();
//
//        for (String signal : signals) {
//            Color color = Color.valueOf(signal);
//            TrafficLight trafficLight = new TrafficLight(color);
//            trafficLights.add(trafficLight);
//        }

        List<TrafficLight> trafficLights = Arrays.stream(signals)
                .map(Color::valueOf)
                .map(TrafficLight::new)
                .collect(Collectors.toList());

        for (int i = 0; i < iterations; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.changeColor();
                System.out.print(trafficLight.getColor() + " ");
            }
            System.out.println();
        }

    }
}
