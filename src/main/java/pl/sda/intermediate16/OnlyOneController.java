package pl.sda.intermediate16;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.intermediate16.categories.CategorySearchService;
import pl.sda.intermediate16.users.Countries;
import pl.sda.intermediate16.users.UserExistException;
import pl.sda.intermediate16.users.UserRegistrationDTO;
import pl.sda.intermediate16.users.UserRegistrationService;
import pl.sda.intermediate16.users.UserValidationService;


import java.util.Map;

@Controller
public class OnlyOneController { //ta klasa pozwala kontaktować się przeglądarce z naszą aplikacją

    CategorySearchService categorySearchService = new CategorySearchService();


    @ResponseBody
    @RequestMapping("/")
    public String ok() {
        return "OK";
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
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorMap = userValidationService.validate(userRegistrationDTO);
        model.addAttribute("form", userRegistrationDTO);
        model.addAttribute("countries", Countries.values());
        if (errorMap.isEmpty()) {
            UserRegistrationService userRegistrationService = new UserRegistrationService();
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


}