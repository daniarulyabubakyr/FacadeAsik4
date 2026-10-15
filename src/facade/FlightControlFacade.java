
package facade;

import model.Flight;
import subsystem.WeatherService;
import subsystem.AircraftService;
import subsystem.PassengerService;
import subsystem.RunwayService;

import java.util.Objects;

public class FlightControlFacade {

    private final WeatherService weatherService;
    private final AircraftService aircraftService;
    private final PassengerService passengerService;
    private final RunwayService runwayService;

    private FlightControlFacade(
            WeatherService weatherService,
            AircraftService aircraftService,
            PassengerService passengerService,
            RunwayService runwayService) {

        this.weatherService = Objects.requireNonNull(weatherService);
        this.aircraftService = Objects.requireNonNull(aircraftService);
        this.passengerService = Objects.requireNonNull(passengerService);
        this.runwayService = Objects.requireNonNull(runwayService);
    }


    public static FlightControlFacade create(
            String weatherCondition,
            int fuelLevel,
            boolean engineReady,
            boolean maintenanceValid,
            int expectedPassengers,
            int checkedInPassengers,
            boolean boardingCompleted,
            String runwayId) {

        return new FlightControlFacade(
                new WeatherService(weatherCondition),
                new AircraftService(
                        fuelLevel,
                        engineReady,
                        maintenanceValid
                ),
                new PassengerService(
                        expectedPassengers,
                        checkedInPassengers,
                        boardingCompleted
                ),
                new RunwayService(runwayId)
        );
    }

    public boolean prepareFlight(Flight flight) {

        Objects.requireNonNull(flight, "Flight cannot be null");

        System.out.println("\n================================");
        System.out.println("AIRPORT FLIGHT CONTROL SYSTEM");
        System.out.println("================================");

        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("From: " + flight.getDeparture());
        System.out.println("To: " + flight.getDestination());


        System.out.println("\n[1] Checking weather...");

        if (!weatherService.isWeatherSafe()) {
            System.out.println("FAILED: Unsafe weather conditions.");
            return false;
        }

        System.out.println("PASSED: Weather is safe.");


        System.out.println("\n[2] Checking aircraft...");

        if (!aircraftService.isAircraftReady()) {
            System.out.println("FAILED: Aircraft is not ready.");
            return false;
        }

        System.out.println("PASSED: Aircraft is ready.");


        System.out.println("\n[3] Checking passengers...");

        if (!passengerService.arePassengersReady()) {
            System.out.println("FAILED: Passengers are not ready.");
            return false;
        }

        System.out.println("PASSED: Passengers are ready.");


        System.out.println("\n[4] Reserving runway...");

        if (!runwayService.reserveRunway()) {
            System.out.println("FAILED: Runway is unavailable.");
            return false;
        }

        System.out.println("PASSED: Runway reserved.");
        System.out.println("Runway: " + runwayService.getRunwayId());

        System.out.println("\n================================");
        System.out.println("FLIGHT READY FOR DEPARTURE");
        System.out.println("================================");

        return true;
    }


    public void releaseRunway() {
        runwayService.releaseRunway();
        System.out.println("Runway released successfully.");
    }


    public boolean reserveRunwayForTest() {
        return runwayService.reserveRunway();
    }
}
