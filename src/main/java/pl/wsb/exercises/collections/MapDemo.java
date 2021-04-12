package pl.wsb.exercises.collections;

import java.util.*;

class User{
    String login;
    String password;
    String email;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
public class MapDemo {
    public static void main(String[] args) {
        NavigableMap<String, User> users = new TreeMap<>();
        User user = new User("adam", "12345", "adam@wsb.pl");
        users.put(user.getEmail(), user);
        user = new User("karolina", "abv34", "karola@op.pl");
        users.put(user.getEmail(), user);
        user = new User("ewa", "abv3434", "ewka@op.pl");
        users.put(user.getEmail(), user);
        //czy istnieje uzytkownik o danym email
        if (users.containsKey("qqq@op.pl")){
            System.out.println("JEST");
        } else {
            System.out.println("BRAK");
        }
        System.out.println(users);
        users.replace("adam@wsb.pl", new User("adamek","452gh","adam@wsb.pl"));
        System.out.println(users);
        users.put("test@op.pl", null);
        if (users.get("test@op.pl")!= null) {
            System.out.println(users.get("test@op.pl").getPassword());
        } else{
            System.out.println("Brak takiego użytkownika");
        }
        //users.put(null,new User("null","null","null"));
        //users.putIfAbsent(null,new User("next null","next null","next null"));
        //System.out.println(users.get(null));
        for(User u:users.values()){
            if (u != null)
            System.out.println(u.getPassword());
        }
        for(Map.Entry<String, User> entry: users.entrySet()){
            System.out.println("Pod kluczem: " + entry.getKey() +" jest wartość " + entry.getValue());
        }
        System.out.println(users.descendingMap());
    }
}
