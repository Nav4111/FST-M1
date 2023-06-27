package activities;

public class Activity7 {
    public static void main(String[] args) {
        MountainBike mb = new MountainBike(4, 30, 6);
        System.out.println(mb.bicycleDesc());
        mb.speedUp(20);
        mb.applyBrake(5);
        mb.setHeight(8);
        System.out.println(mb.bicycleDesc());
    }
}

class Bicycle implements BicycleParts,BicycleOperations {
    public int gears = 0;
    public int speed = 0;
    Bicycle(int gears, int speed){
        this.gears = gears;
        this.speed = speed;
    }
    public void applyBrake(int decrement) {
        speed-=decrement;
        System.out.println("After applying brake current Speed: " + speed);
    }
    public void speedUp(int increment) {
        speed+=increment;
        System.out.println("After speed up current speed: "+ speed);
    }
    public String bicycleDesc() {
        return("No of gears are "+ gears + "\nSpeed of bicycle is " + speed);
    }
}

class MountainBike extends Bicycle {
    public int seatHeight;
    MountainBike(int gears, int speed, int startHeight) {
        super(gears, speed);
        seatHeight = startHeight;
    }
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }
    public String bicycleDesc(){
        return(super.bicycleDesc() + "\nSeat height is " + seatHeight);
    }
}
