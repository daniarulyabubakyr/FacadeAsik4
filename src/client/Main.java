
package client;

import facade.FlightControlFacade;
import model.Flight;

public class Main {

    public static void main(String[] args) {

        Flight flight = new Flight(
                "KC852",
                "Astana",
                "Almaty"
        );


        printScenario("1. SUCCESSFUL FLIGHT");

        FlightControlFacade successFacade =
                FlightControlFacade.create(
                        "Clear",
                        80, true, true,
                        180, 180, true,
                        "RWY-01"
                );

        boolean success = successFacade.prepareFlight(flight);
        printResult(success);

        if (success) {
            successFacade.releaseRunway();
        }


        printScenario("2. WEATHER FAILURE");

        FlightControlFacade weatherFacade =
                FlightControlFacade.create(
                        "Stormy",
                        80, true, true,
                        180, 180, true,
                        "RWY-02"
                );

        printResult(weatherFacade.prepareFlight(flight));

        printScenario("3. AIRCRAFT FAILURE");

        FlightControlFacade aircraftFacade =
                FlightControlFacade.create(
                        "Clear",
                        30, true, true,
                        180, 180, true,
                        "RWY-03"
                );

        printResult(aircraftFacade.prepareFlight(flight));

        printScenario("4. PASSENGER FAILURE");

        FlightControlFacade passengerFacade =
                FlightControlFacade.create(
                        "Clear",
                        80, true, true,
                        180, 170, true,
                        "RWY-04"
                );

        printResult(passengerFacade.prepareFlight(flight));


        printScenario("5. RUNWAY FAILURE");

        FlightControlFacade runwayFacade =
                FlightControlFacade.create(
                        "Clear",
                        80, true, true,
                        180, 180, true,
                        "RWY-05"
                );

        runwayFacade.reserveRunwayForTest();

        printResult(runwayFacade.prepareFlight(flight));


        printScenario("6. INPUT VALIDATION");

        try {
            FlightControlFacade.create(
                    "Clear",
                    -10, true, true,
                    180, 180, true,
                    "RWY-06"
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "VALIDATION ERROR: " + e.getMessage()
            );
        }
    }

    private static void printScenario(String name) {
        System.out.println("\n\n######## " + name + " ########");
    }

    private static void printResult(boolean ready) {
        System.out.println(
                "FINAL RESULT: " + (ready ? "SUCCESS" : "FAILED")
        );
    }
}
