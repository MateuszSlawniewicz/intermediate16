package pl.sda.intermediate16.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAddress implements Serializable {
    private String city;
    private String country;
    private String street;
    private String zipCode;
}
