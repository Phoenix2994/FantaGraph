package model;


public class User {
    private final long id;
    private final String username;
    private final String email;
    private final FantaTeam[] teams;

    public User(long id, String username, String email, FantaTeam[] teams){
        this.id = id;
        this.username = username;
        this.email = email;
        this.teams = teams;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public FantaTeam[] getTeams() {
        return teams;
    }
}
