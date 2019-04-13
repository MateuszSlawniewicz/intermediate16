package pl.sda.intermediate16.users;

import org.apache.commons.codec.digest.DigestUtils;

public class UserLoginService {
    UserDAO userDAO;

    public UserLoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(UserLoginDTO userDTO){
        return userDAO.getUsers().stream()
                .filter(u -> u.getLogin().equals(userDTO.getEmail()))
                .findAny()
                .map(u -> u.getPasswordHash()
                        .equals(DigestUtils.sha512Hex(userDTO.getPassword())))
                .orElse(false);
    }
}
