package design_patterns.factory;

public class VehicleFactoryClient {
    private final VehicleFactory vehicleFactory;

    public VehicleFactoryClient(String vehicleType) throws Exception {
        if ("twoWheeler".equals(vehicleType)) {
            this.vehicleFactory = new BikeFactory();
        } else if ("fourWheeler".equals(vehicleType)) {
            this.vehicleFactory = new CarFactory();
        } else {
            throw new Exception("Invalid vehicle type");
        }
    }

    public Vehicle getVehicle() {
        return vehicleFactory.createVehicle();
    }
}
