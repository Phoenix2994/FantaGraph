package model;

public class Stadium {
    private final long id;
    private final String name;
    private final String city;
    private final long capacity;
    private final String img;
    private final String teamName;
    private final long teamId;

    public Stadium(long id, String name, String city, long capacity, String img, String teamName, long teamId){
        this.id = id;
        this.name = name;
        this.city = city;
        this. capacity = capacity;
        this.img = img;
        this.teamName = teamName;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public long getCapacity() {
        return capacity;
    }

    public String getImg() {
        return img;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getTeamId() {
        return teamId;
    }
}
