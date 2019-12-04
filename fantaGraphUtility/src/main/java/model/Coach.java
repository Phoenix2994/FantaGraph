package model;

import java.time.LocalDate;

public class Coach {
    private final long id;
    private final String name;
    private final LocalDate birthdate;
    private final String birthplace;
    private final String nationality;
    private final String module;
    private final String teamName;
    private final long teamId;

    public Coach(long id, String name, LocalDate birthdate, String birthplace, String nationality, String module, String teamName, long teamId){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.nationality = nationality;
        this.module = module;
        this.teamName = teamName;
        this.teamId = teamId;

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getNationality() {
        return nationality;
    }

    public String getModule() {
        return module;
    }

    public String getTeamName() {
        return teamName;
    }

    public long getTeamId() {
        return teamId;
    }
}
