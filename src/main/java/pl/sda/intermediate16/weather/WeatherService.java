package pl.sda.intermediate16.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.intermediate16.users.User;
import pl.sda.intermediate16.users.UserContextHolder;
import pl.sda.intermediate16.users.UserDAO;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;
@Service
public class WeatherService {

    private String apiKey = "ea900b66f547fd7b23625544873a4200";
    @Autowired
    private UserDAO userDAO;

    public WeatherService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public WeatherResult getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();
        OpenWeatherMapJ8 openWeatherMapJ8 = retrofit.create(OpenWeatherMapJ8.class);
        String city = findCity();
        CompletableFuture<WeatherResult> completableFuture = openWeatherMapJ8.getCurrentWeatherByCity(city, apiKey, "metric", "pl");
        WeatherResult result = completableFuture.join();
        return result;
    }

    private String findCity() {
        String loggedUser = UserContextHolder.getUserLoggedIn();
        return userDAO.getUsers().stream()
                .filter(e -> e.getLogin().equals(loggedUser))
                .findAny()
                .map(user -> user.getUserAddress().getCity())
                .orElse("Berlin");
    }


}
