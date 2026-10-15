
package subsystem;

public class RunwayService {

    private final String runwayId;
    private boolean available;

    public RunwayService(String runwayId){

        if (runwayId==null || runwayId.isBlank()){
            throw new IllegalArgumentException(
                    "Runway ID cannot be empty"
            );
        }

        this.runwayId=runwayId;
        this.available=true;
    }

    public boolean isRunwayAvailable(){
        return available;
    }

    public boolean reserveRunway(){

        if (!available){
            return false;
        }

        available=false;
        return true;
    }

    public void releaseRunway(){
        available=true;
    }

    public String getRunwayId(){
        return runwayId;
    }
}
