package springcontainer.main;

import org.springframework.stereotype.Component;

@Component
public class User {
    public String name;
    public String email;

    public Friend friend;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public User(String name, String email, Friend friend) {
        this.name = name;
        this.email = email;
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", friend=" + friend +
            '}';
    }
}
