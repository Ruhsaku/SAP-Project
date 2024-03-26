import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {
    private String password;
    private String username;

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public abstract userType getUserType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(password, user.password) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
