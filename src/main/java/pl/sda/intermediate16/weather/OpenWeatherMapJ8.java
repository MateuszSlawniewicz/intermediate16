package pl.sda.intermediate16.weather;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

public interface OpenWeatherMapJ8 {

    @GET("data/2.5/weather")
    CompletableFuture<WeatherResult> getCurrentWeatherByCity(
            @Query("q") String cityName,
            @Query("appid") String appId,
            @Query("units") String units,
            @Query("lang") String lang
    );

}
