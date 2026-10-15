
package subsystem;

public class AircraftService{

    private static final int MIN_FUEL_LEVEL=50;

    private final int fuelLevel;
    private final boolean engineReady;
    private final boolean maintenanceValid;

    public AircraftService(int fuelLevel,
                           boolean engineReady,
                           boolean maintenanceValid){

        if (fuelLevel < 0 || fuelLevel>100) {
            throw new IllegalArgumentException(
                    "Fuel level must be between 0 and 100"
            );
        }

        this.fuelLevel=fuelLevel;
        this.engineReady=engineReady;
        this.maintenanceValid=maintenanceValid;
    }

    public boolean isAircraftReady(){
        return fuelLevel>=MIN_FUEL_LEVEL
                && engineReady
                && maintenanceValid;
    }
}
