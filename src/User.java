public class User {
    private String login;
    private String name;
    private String company;
    private String location;
    private int public_repos;

    // Getters
    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public int getPublicRepos() {
        return public_repos;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", public_repos=" + public_repos +
                '}';
    }
}
