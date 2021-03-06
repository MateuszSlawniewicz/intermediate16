package pl.sda.intermediate16.users;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
@Service
public class UserValidationService {
    public Map<String, String> validate(UserRegistrationDTO dto) {
        Map<String, String> errorMap = new HashMap<>();
        String firstName = dto.getFirstName(); //todo remove this messy code
        String lastName = dto.getLastName();
        String email = dto.getEMail();
        String zipCode = dto.getZipCode();
        String birthDate = dto.getBirthDate();
        String phone = dto.getPhone();
        String password = dto.getPassword();
        String city = dto.getCity();
        String country = dto.getCountry();
        String street = dto.getStreet();
        String pesel = dto.getPesel();
        if (firstName == null || !firstName.matches("^[A-Z][a-z]{2,}$")) {
            errorMap.put("firstNameValRes", "Powinny być przynajmniej 3 znaki");
        }
        if (lastName == null || !lastName.matches("^[A-Z][a-z]{2,}(-[A-Z][a-z]{2,})?")) {
            errorMap.put("lastNameValRes", "Powinny być przynajmniej 3 znaki, oraz pierwsza duża");
        }
        if (email == null || !email.matches("^[\\w\\.]{2,}@([a-z]{2,}\\.){1,2}[a-z]{2,3}$")) {
            errorMap.put("emailValRes", "zły adres email");
        }
        if (zipCode == null || !zipCode.matches("[0-9]{2}-[0-9]{3}")) {
            errorMap.put("zipCodeValRes", "nie taki geniuszu");
        }
        if (birthDate == null || !birthDate.matches("^(19|20)[0-9]{2}-((0[1-9])|1[0-2])-((0[1-9])|([12][0-9])|(3[01]))$")) {
            errorMap.put("birthDateValRes", "błędna data");
        }
        if (phone == null || !phone.trim().matches("((\\+48)|)( |)[0-9]{9}$")) {
            errorMap.put("phoneValRes", "zły numer telefonu");
        }
        if (password == null || !password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d]).{10,20}")) {
            errorMap.put("passwordValRes", "złe hasło");
        }
        if (!isNotBlank(city)) {
            errorMap.put("cityValRes", "błędne miasto");
        }
        if (!isNotBlank(country)) {
            errorMap.put("countryValRes", "błędny kraj");
        }
        if (!isNotBlank(street)) {
            errorMap.put("streetValRes", "błędna ulica");
        }
        if (pesel == null || !pesel.matches("^[0-9]{11}$")) {
            errorMap.put("peselValRes", "błędny pesel");
        }

        return errorMap;
    }
}
