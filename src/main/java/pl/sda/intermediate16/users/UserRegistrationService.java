package pl.sda.intermediate16.users;

import org.apache.commons.codec.digest.DigestUtils;

public class UserRegistrationService {


    public void register(UserRegistrationDTO dto) {
        UserDAO userDAO = new UserDAO();
        if (userExist(dto, userDAO)) {
            throw new UserExistException("Użytkownik " + dto.getEMail() + " istnieje");
        } else {
            User user = new User();
            rewriteDataToUser(dto, user);
            userDAO.saveUser(user);

        }


    }

    private void rewriteDataToUser(UserRegistrationDTO dto, User user) {
        user.setName(dto.getFirstName().trim());
        user.setSurname(dto.getLastName().trim());
        user.setBirthdate(dto.getBirthDate().trim());
        user.setLogin(dto.getEMail().trim());
        user.setPesel(dto.getPesel().trim());
        user.setPhone(dto.getPhone().trim());
        UserAddress userAddress = new UserAddress();
        rewriteAddress(dto, userAddress);
        user.setUserAddress(userAddress);
        user.setPasswordHash(DigestUtils.sha512Hex(dto.getPassword().trim()));
    }

    private void rewriteAddress(UserRegistrationDTO dto, UserAddress userAddress) {
        userAddress.setCity(dto.getCity().trim());
        userAddress.setCountry(dto.getCountry().trim());
        userAddress.setCountry(dto.getCountry().trim());
        userAddress.setZipCode(dto.getZipCode().trim());
    }

    private boolean userExist(UserRegistrationDTO userRegistrationDTO, UserDAO userDAO) {
        return userDAO.getUsers().stream()
                .map(User::getLogin)
                .anyMatch(e -> e.equals(userRegistrationDTO.getEMail()));
    }
}
