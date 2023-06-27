package activities;

public class Activity1 {

    public static void main(String[] args) {

        Car hyundai = new Car();
        hyundai.make = 2014;
        hyundai.color = "Black";
        hyundai.transmission = "Manual";

        hyundai.displayCharacteristics();
        hyundai.accelarate();
        hyundai.brake();
    }


}
