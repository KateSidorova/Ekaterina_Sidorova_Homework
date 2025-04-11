package Lesson_4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Product {
    private String name;
    private LocalDate manufactureDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isBooked;

    public Product(String name, LocalDate manufactureDate, String manufacturer, String countryOfOrigin, double price, boolean isBooked) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isBooked = isBooked;
    }

    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Product: " + name);
        System.out.println("Manufacture Date: " + manufactureDate.format(formatter));
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Country of origin: " + countryOfOrigin);
        System.out.println("Price: " + price + " руб.");
        System.out.println("Booking Status: " + (isBooked ? "Забронирован" : "Свободен"));
    }

    public static void main(String[] args) {
        Product product1 = new Product(
                "Laptop",
                LocalDate.of(2020, 6, 3),
                "ARDOR GAMING",
                "China",
                64990,
                false
        );

        product1.displayInfo();
    }
}
