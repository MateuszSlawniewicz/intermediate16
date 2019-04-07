package pl.sda.intermediate16.users;

public class UserContextHolder {
    private static String email;

    public static void logUserIn(UserLoginDTO dto) {
        email = dto.getEmail();
    }

}
