package Lesson_4;

import java.util.ArrayList;

public class Park {
    private ArrayList<Attraction> attractions;

    public Park() {
        attractions = new ArrayList<>();
    }

    public void addAttraction(String name, String hours, double price) {
        attractions.add(new Attraction(name, hours, price));
    }

    public void displayAttractions() {
        System.out.println("Аттракционы в парке:");
        for (Attraction attraction : attractions) {
            attraction.displayInfo();
        }
    }

    private class Attraction {
        private String name;
        private String hours;
        private double price;

        public Attraction(String name, String hours, double price) {
            this.name = name;
            this.hours = hours;
            this.price = price;
        }

        public void displayInfo() {
            System.out.println("Название: " + name + ", Время работы: " + hours + ", Стоимость: " + price);
        }

        public double getPrice() {
            return price;
        }
    }

    public static void main(String[] args) {
        Park amusementPark = new Park();

        amusementPark.addAttraction("Бустер", "12:00 - 22:00", 1400);
        amusementPark.addAttraction("Ракета", "12:00 - 22:00", 1300);
        amusementPark.addAttraction("Шейкер", "12:00 - 22:00", 400);
        amusementPark.addAttraction("Крылатые качели", "13:00 - 22:00", 400);

        amusementPark.displayAttractions();
    }
}