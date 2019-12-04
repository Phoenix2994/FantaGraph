package model;

public class Team {
    private final long id;
    private final String name;
    private final long playersNum;
    private final double avgAge;
    private final String logo;
    private final String coachName;
    private final long coachId;
    private final String presidentName;
    private final long presidentId;
    private final String stadiumName;
    private final long stadiumId;

    public Team(){
        this.id = 0;
        this.name = "";
        this.playersNum = 0;
        this.avgAge = 0;
        this.logo = "";
        this.coachName = "";
        this.coachId = 0;
        this.presidentName = "";
        this.presidentId = 0;
        this.stadiumName = "";
        this.stadiumId = 0;
    }
    public Team(long id, String name, long players, double avgAge, String logo, String coach, long coachId, String presidentName, long presidentId, String stadiumName, long stadiumId){
        this.id = id;
        this.name = name;
        this.playersNum = players;
        this.avgAge = avgAge;
        this.logo = logo;
        this.coachName = coach;
        this.coachId = coachId;
        this.presidentName = presidentName;
        this.presidentId = presidentId;
        this.stadiumName = stadiumName;
        this.stadiumId = stadiumId;
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

    public String getCoach() {
        return coachName;
    }

    public long getCoachId() {
        return coachId;
    }

    public String getPresidentName() {
        return presidentName;
    }

    public long getPresidentId() {
        return presidentId;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public long getStadiumId() {
        return stadiumId;
    }
}
