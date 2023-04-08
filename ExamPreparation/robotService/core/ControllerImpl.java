package robotService.core;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private SupplementRepository supplementRepository;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplementRepository = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        if (type.equals("MainService")) {
            service = new MainService(name);
        } else if (type.equals("SecondaryService")) {
            service = new SecondaryService(name);
        } else {
            throw new NullPointerException(INVALID_SERVICE_TYPE);
        }
        this.services.add(service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        if (type.equals("PlasticArmor")) {
            supplement = new PlasticArmor();
        } else if (type.equals("MetalArmor")) {
            supplement = new MetalArmor();
        } else {
            throw new IllegalStateException(INVALID_SUPPLEMENT_TYPE);
        }
        this.supplementRepository.addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplementToAdd = this.supplementRepository.findFirst(supplementType);

        if (supplementToAdd == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }
        Service service = getServiceByName(serviceName);
        service.addSupplement(supplementToAdd);
        supplementRepository.removeSupplement(supplementToAdd);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Robot robot;


        if (robotType.equals("MaleRobot")) {
            robot = new MaleRobot(robotName, robotKind, price);
        } else if (robotType.equals("FemaleRobot")) {
            robot = new FemaleRobot(robotName, robotKind, price);
        } else {
            throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        Service service = getServiceByName(serviceName);

        boolean checkMain = robotType.startsWith("Male") && service.getClass().getSimpleName().startsWith("Main");
        boolean checkSec = robotType.startsWith("Female") && service.getClass().getSimpleName().startsWith("Secondary");

        if (checkMain || checkSec) {
            service.addRobot(robot);
        } else {
            return UNSUITABLE_SERVICE;
        }
        return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = getServiceByName(serviceName);
                service.getRobots().forEach(Robot::eating);

                return String.format(FEEDING_ROBOT, service.getRobots().size());

    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = getServiceByName(serviceName);
         double sumAllRobots = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
         double priceServices = service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();

        double totalSum = sumAllRobots + priceServices;
        return String.format(VALUE_SERVICE, serviceName, totalSum) ;
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Service service : services) {
            sb.append(service.getStatistics()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }


    private Service getServiceByName(String serviceName) {
        return this.services.stream().filter(s -> s.getName().equals(serviceName))
                .findFirst().get();
    }
}
