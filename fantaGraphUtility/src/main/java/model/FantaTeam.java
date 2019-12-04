package model;

public class FantaTeam{
    private final long id;
    private final String name;
    private final long playersNum;
    private final double avgAge;
    private final String logo;
    private final long ownerId;

    public FantaTeam(long id, String name, long players, double avgAge, String logo, String username, long ownerId){
        this.id = id;
        this.name = name;
        this.playersNum = players;
        this.avgAge = avgAge;
        this.logo = logo;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPlayersNum() {
        return playersNum;
    }

    public double getAvgAge() {
        return avgAge;
    }

    public String getLogo() {
        return logo;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
