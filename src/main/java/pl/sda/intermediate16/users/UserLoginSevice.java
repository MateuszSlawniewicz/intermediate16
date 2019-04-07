package pl.sda.intermediate16.users;

import org.apache.commons.codec.digest.DigestUtils;

public class UserLoginSevice {

    UserDAO userDAO ;

    public UserLoginSevice(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(UserLoginDTO uld) {
        return userDAO.getUsers().stream()
                .filter(e -> e.getLogin().equals(uld.getEmail()))
                .findAny()
                .map(u -> u.getPasswordHash().equals(DigestUtils.sha512Hex(uld.getPassword())))
                .orElse(false);

    }


}
