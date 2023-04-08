package robotService.entities.services;

import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public abstract class BaseService implements Service{
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return robots;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.getRobots().size() < this.capacity) {
            this.getRobots().add(robot);
        } else {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
    }

    @Override
    public void removeRobot(Robot robot) {

        this.getRobots().remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.getSupplements().add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : getRobots()) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {

        return this.supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:", this.getName(), this.getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        sb.append("Robots: ");

        if (this.getRobots().isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            sb.append(this.getRobots().stream().map(Robot::getName)
                    .collect(Collectors.joining(" ")).trim());
            sb.append(System.lineSeparator());

        }
        sb.append(String.format("Supplements: %d Hardness: %d", this.supplements.size(), this.sumHardness()));
        return sb.toString().trim();
    }

}
