package Lesson_5;

class Animal {
    private static int animalCount = 0;
    public Animal() {
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public void run(int distance) {
        System.out.println("The animal ran " + distance + " meters.");
    }

    public void swim(int distance) {
        System.out.println("The animal swam " + distance + " meters.");
    }
}

class Dog extends Animal {
    private static int dogCount = 0;

    public Dog() {
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println("The dog ran " + distance + " meters.");
        } else {
            System.out.println("The dog can't run that far!");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println("The dog swam " + distance + " meters.");
        } else {
            System.out.println("The dog can't swim that far!");
        }
    }
}

class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull;

    public Cat() {
        catCount++;
        this.isFull = false;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println("The cat ran " + distance + " meters.");
        } else {
            System.out.println("The cat can't run that far!");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Cats can't swim!");
    }

    public boolean eat(FoodBowl bowl) {
        if (bowl.getFoodAmount() > 0) {
            bowl.eat(); // Кот пытается поесть из миски
            this.isFull = true;
            System.out.println("The cat has eaten and is full.");
            return true;
        } else {
            System.out.println("The bowl is empty. The cat did not eat.");
            return false;
        }
    }

    public boolean isFull() {
        return this.isFull;
    }
}

class FoodBowl {
    private int foodAmount;

    public FoodBowl(int initialAmount) {
        if (initialAmount < 0) {
            this.foodAmount = 0;
        } else {
            this.foodAmount = initialAmount;
        }
    }

    public void eat() {
        if (foodAmount > 0) {
            foodAmount--;
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("Added " + amount + " food to the bowl. Total food: " + foodAmount);
        } else {
            System.out.println("Cannot add negative food!");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog();
        dogBobik.run(150);
        dogBobik.swim(5);

        Cat catMurzik = new Cat();
        catMurzik.run(100);
        catMurzik.swim(10);

        FoodBowl bowl = new FoodBowl(5);

        // Создаем массив котов
        Cat[] cats = { new Cat(), new Cat(), new Cat() };

        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        for (int i = 0; i < cats.length; i++) {
            System.out.println("Cat " + (i + 1) + " is full? " + cats[i].isFull());
        }

        bowl.addFood(10);
        for (Cat cat : cats) {
            cat.eat(bowl);
        }
    }
}