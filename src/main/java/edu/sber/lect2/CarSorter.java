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
        List<Car> cars = new ArrayList<>();
        //рандомные авто
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car(models.get(new Random().nextInt(models.size())),
                    types.get(new Random().nextInt(types.size()))));
        }

        //мапа с листами мишин
        Map<String, List<Car>> sortedCars = new HashMap<>();
        //фильтр по типу, добавление в коллекции
        for (String type : types) {
            sortedCars.put(type, cars.stream()
                    .filter(Car -> Car.getType().equals(type))
                    .collect(Collectors.toList()));
        }
        //вывод на экран
        sortedCars.forEach((key, value) -> {
            System.out.println("type: " + key + " count: " + value.size());
            value.forEach(System.out::println);
        });
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
