package pl.sda.intermediate16.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private String name;
    private String surname;
    private String login;
    private String birthdate; //TODO na localDate

    private static final long serialVersionUID = -381001129481477463L;

    private String pesel;
    private String phone;
    private String passwordHash;
    private UserAddress userAddress;
}

