package pl.sda.intermediate16;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate16.categories.CategorySearchService;
import pl.sda.intermediate16.users.*;
import pl.sda.intermediate16.weather.WeatherResult;
import pl.sda.intermediate16.weather.WeatherService;


import java.util.Map;

@Controller
public class OnlyOneController { //ta klasa pozwala kontaktować się przeglądarce z naszą aplikacją
    UserDAO userDAO = new UserDAO();
    CategorySearchService categorySearchService = new CategorySearchService();
    UserValidationService userValidationService = new UserValidationService();
    UserRegistrationService userRegistrationService = new UserRegistrationService(userDAO);
    UserLoginSevice userLoginSevice = new UserLoginSevice(userDAO);
    WeatherService weatherService = new WeatherService(userDAO);


    @RequestMapping("/")
    public String ok() {
        return "index";
    }

    //adnotacja
    @RequestMapping("/categories") //pod takim urlem dostępna jest strona z kategoriami
    public String categories(String searchText, Model model) {
        model.addAttribute("catsdata", categorySearchService.filterCategories(searchText));
        return "catspage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("form", new UserRegistrationDTO());
        model.addAttribute("countries", Countries.values());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //ta metoda (POST) obsluguje wyslanie danych z frontu
    public String retrieveRegisterForm(UserRegistrationDTO userRegistrationDTO, Model model) {
        Map<String, String> errorMap = userValidationService.validate(userRegistrationDTO);
        model.addAttribute("form", userRegistrationDTO);
        model.addAttribute("countries", Countries.values());
        if (errorMap.isEmpty()) {
            try {
                userRegistrationService.register(userRegistrationDTO);
            } catch (UserExistException e) {
                model.addAttribute("userExistsExceptionMessage", e.getMessage());
                return "registerForm";
            }
        } else {
            model.addAllAttributes(errorMap);
            return "registerForm";
        }
        return "registerEffect";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("form", new UserLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserLoginDTO uld, Model model) {
        if (userLoginSevice.login(uld)) {
            UserContextHolder.logUserIn(uld);
        }
        return "index";
    }

    @ResponseBody                 // to mówi, że będzie Jsonem, a nie nazwą hmla
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public String showWeather() {
        WeatherResult weather = weatherService.getWeather();
        return new Gson().toJson(weather);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        UserContextHolder.logUserOut();
        return "index";
    }

}