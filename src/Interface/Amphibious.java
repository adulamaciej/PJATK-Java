package Interface;

public class Amphibious implements IAuto, IBoat{
    private boolean onWater = false;

    public void setOnWater(boolean onWater) {
        this.onWater = onWater;
    }

    @Override
    public void move() {

        if (onWater) {
            System.out.println("Sailing on the water as an amphibious vehicle...");
        } else {
            System.out.println("Driving on the road as an amphibious vehicle...");
        }
    }
}
