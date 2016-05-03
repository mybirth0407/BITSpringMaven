package springcontainer.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Guest {
    private String name;
    private List<User> users;

    @Autowired
    private Friend friend;

    public Guest() {
        this.name = "asdf";
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Guest{" +
            "name='" + name + '\'' +
            ", users=" + users +
            ", friend=" + friend +
            '}';
    }
}
