
package subsystem;

public class WeatherService{

    private final String weatherCondition;

    public WeatherService(String weatherCondition){
        this.weatherCondition=weatherCondition;
    }

    public boolean isWeatherSafe(){

        if (weatherCondition==null){
            return false;
        }

        return weatherCondition.equalsIgnoreCase("Clear")
                || weatherCondition.equalsIgnoreCase("Cloudy")
                || weatherCondition.equalsIgnoreCase("Rainy");
    }
}
