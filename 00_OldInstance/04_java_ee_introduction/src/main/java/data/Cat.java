package data;

public class Cat {
    private String name;
    private String breed;
    private String color;
    private int numberOfLegs;

    public Cat(String name, String breed, String color, int numberOfLegs) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.numberOfLegs = numberOfLegs;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
}
