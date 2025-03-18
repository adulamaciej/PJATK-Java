package Interface;

public class VehicleTest {
    public static void main(String[] args) {
        System.out.println("Testing Auto:");
        Auto auto = new Auto();
        auto.move();

        System.out.println("\nTesting Boat:");
        Boat boat = new Boat();
        boat.move();

        System.out.println("\nTesting Amphibious:");
        Amphibious amphibious = new Amphibious();

        System.out.println("On land:");
        amphibious.setOnWater(false);
        amphibious.move();

        System.out.println("On water:");
        amphibious.setOnWater(true);
        amphibious.move();
    }
}
