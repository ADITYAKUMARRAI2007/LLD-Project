public class VehicleFactory {

    private VehicleFactory() {
    }

    public static Vehicle createVehicle(String vehicleNumber, VehicleType vehicleType) {
        return new Vehicle(vehicleNumber, vehicleType);
    }
}