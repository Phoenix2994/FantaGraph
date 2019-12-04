package model;


import java.time.LocalDate;

public class Player {
    private final long id;
    private final String name;
    private final String birthplace;
    private final LocalDate birthdate;
    private final String nationality;
    private final long age; //long per janusgraph
    private final String height;
    private final String position;
    private final String mainFoot;
    private final String teamName;
    private final long teamId;
    private final String prosecutorName;
    private final long prosecutorId;
    private final PlayerStats[] stats;
    private final long quot;
    private final String img;


    public Player(long id,
                  String name,
                  String birthplace,
                  LocalDate birthdate,
                  String nationality,
                  long age,
                  String height,
                  String position,
                  String mainFoot,
                  PlayerStats[] stats,
                  long quot,
                  String teamName,
                  long teamId,
                  String prosecutorName,
                  long prosecutorId,
                  String img) {
        this.id = id;
        this.name = name;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.age = age;
        this.height = height;
        this.position = position;
        this.mainFoot = mainFoot;
        this.stats = stats;
        this.quot = quot;
        this.teamName = teamName;
        this.teamId = teamId;
        this.prosecutorName = prosecutorName;
        this.prosecutorId = prosecutorId;
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public long getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getPosition() {
        return position;
    }

    public String getMainFoot() {
        return mainFoot;
    }

    public PlayerStats[] getStats() {
        return stats;
    }

    public long getQuot() {
        return quot;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getProsecutorName() {
        return prosecutorName;
    }

    public long getProsecutorId() {
        return prosecutorId;
    }

    public String getImg() {
        return img;
    }
}
