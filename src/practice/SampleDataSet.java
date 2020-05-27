package practice;

public class SampleDataSet {
    private String username;
    private String password;

    public SampleDataSet(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SampleDataSet{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
