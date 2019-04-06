package pl.sda.intermediate16.users;

public class UserExistException extends RuntimeException {


    public UserExistException(String s) {
        super(s);
    }
}
