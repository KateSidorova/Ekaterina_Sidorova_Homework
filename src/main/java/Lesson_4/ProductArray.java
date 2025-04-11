package Lesson_4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ProductArray {
    private String name;
    private LocalDate manufactureDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isBooked;

    public ProductArray(String name, String manufactureDate, String manufacturer, String countryOfOrigin, double price, boolean isBooked) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isBooked = isBooked;
        this.manufactureDate = LocalDate.parse(manufactureDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void displayInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Name: " + name);
        System.out.println("Manufacture Date: " + manufactureDate.format(formatter));
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Country of Origin: " + countryOfOrigin);
        System.out.println("Price: " + price + " руб.");
        System.out.println("Booking Status: " + (isBooked ? "Booked" : "Available"));
        System.out.println();
    }

    public static void main(String[] args) {
        ProductArray[] productsArray = new ProductArray[5];

        productsArray[0] = new ProductArray("Samsung S25 Ultra", "07.02.2023", "Samsung Corp.", "Korea", 160000, true);
        productsArray[1] = new ProductArray("Xiomi Redmi Note", "10.11.2024", "Xiomi Inc.", "China", 17999, false);
        productsArray[2] = new ProductArray("Samsung Galaxy A55", "15.10.2023", "Samsung Corp.", "Korea", 38499, true);
        productsArray[3] = new ProductArray("OPPO Reno13", "25.03.2023", "Guangdong Oujia Holding Company Ltd.", "China", 59999, false);
        productsArray[4] = new ProductArray("iPhone 14 Pro", "20.12.2024", "Apple Inc.", "China", 103799, true);

        for (ProductArray product : productsArray) {
            product.displayInfo();
        }
    }
}