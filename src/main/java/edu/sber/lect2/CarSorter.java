package edu.sber.lect2;

import java.util.*;
import java.util.stream.Collectors;

public class CarSorter {

    public static void main(String[] args) {
        int carCount = 100;
        //список моделей
        List<String> models = Arrays.asList("Lada", "Peugeot", "Geely", "Mercedes", "Toyota", "Ford");
        //список кузовов
        List<String> types = Arrays.asList("hatchback", "sedan", "coupe", "SUV", "wagon");
        List<Car> cars = new LinkedList<>();
        //рандомные авто
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car(models.get(new Random().nextInt(models.size())),
                    types.get(new Random().nextInt(types.size()))));
        }
        //лист листов с авто разных типов
        List<List<Car>> sortedCars = new ArrayList<>();
        //фильтр по типу, добавление в коллекции
        for (String type : types) {
            sortedCars.add(cars.stream()
                    .filter(Car -> Car.getType().equals(type))
                    .collect(Collectors.toList()));
        }
        //вывод на экран
        for (List<Car> sortedCar : sortedCars) {
            if (!sortedCar.isEmpty()) {
                System.out.println("<== type ==> " + sortedCar.get(0).getType() + " count: " + sortedCar.size());
                for (Car car : sortedCar) {
                    System.out.println(car);
                }
            }
        }
    }
}

class Car {
    private String model;
    private String type;

    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
