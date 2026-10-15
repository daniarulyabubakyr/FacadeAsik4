
package model;

public class Flight {

    private final String flightNumber;
    private final String departure;
    private final String destination;

    public Flight(String flightNumber,
                  String departure,
                  String destination) {

        this.flightNumber=flightNumber;
        this.departure=departure;
        this.destination=destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}
