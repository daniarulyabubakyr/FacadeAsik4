
package subsystem;

public class PassengerService{

    private final int expectedPassengers;
    private final int checkedInPassengers;
    private final boolean boardingCompleted;

    public PassengerService(int expectedPassengers,
                            int checkedInPassengers,
                            boolean boardingCompleted){

        if (expectedPassengers<=0){
            throw new IllegalArgumentException(
                    "Expected passengers must be positive"
            );
        }

        if (checkedInPassengers < 0
                || checkedInPassengers>expectedPassengers) {
            throw new IllegalArgumentException(
                    "Invalid checked-in passenger count"
            );
        }

        this.expectedPassengers=expectedPassengers;
        this.checkedInPassengers=checkedInPassengers;
        this.boardingCompleted=boardingCompleted;
    }

    public boolean arePassengersReady(){

        return expectedPassengers==checkedInPassengers
                && boardingCompleted;
    }
}
