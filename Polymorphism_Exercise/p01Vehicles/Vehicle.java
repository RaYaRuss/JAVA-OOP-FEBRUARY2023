package Polymorphism_Exercise.p01Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle{
    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }
    public String drive(double distance) {
        double fuelNeeded = distance * getFuelConsumption();
        if (fuelNeeded > getFuelQuantity()) {
            return this.getClass().getSimpleName() + " needs refueling";
        }
        setFuelQuantity(getFuelQuantity() - fuelNeeded);
        DecimalFormat df = new DecimalFormat("##.##");

        return String.format("%s travelled %s km", this.getClass().getSimpleName(), df.format(distance));
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), fuelQuantity);
    }
}
