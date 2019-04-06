package pl.sda.intermediate16.users;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String eMail;
    private String birthDate; //TODO na localDate
    private String pesel;
    private String phone;
    private String password;
    private String city;
    private String country;
    private String street;
    private String zipCode;


}
