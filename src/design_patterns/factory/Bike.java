package design_patterns.factory;

public class Bike implements Vehicle{
    @Override
    public void engineSpecs() {
        System.out.println("Bike Engine");
    }
}
