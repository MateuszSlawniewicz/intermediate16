package pl.sda.intermediate16.users;

import lombok.Getter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDAO {
    private String path = "C:/Users/mateu/IdeaProjects/user_data.txt";
    private List<User> users = new ArrayList<>();

    public void saveUser(User user) {
        users.add(user);
        try (FileOutputStream fos = new FileOutputStream(path);                   // try with resources, żeby nie zamykać
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {    //dekorator
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
