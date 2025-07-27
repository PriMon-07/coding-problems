package factory;

public class Car implements Vehicle{
    @Override
    public void engineSpecs() {
        System.out.println("Car Engine");
    }
}
