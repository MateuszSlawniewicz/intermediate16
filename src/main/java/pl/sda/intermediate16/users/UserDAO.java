package pl.sda.intermediate16.users;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDAO {
    private String path = "C:/Users/mateu/IdeaProjects/user_data.txt";
    private List<User> users = readUsers();

    private List<User> readUsers() {
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object object = ois.readObject();
            return (List<User>) object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


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
