package model;



import java.time.LocalDate;

public class President {
    private final long id;
    private final String name;
    private final LocalDate birthdate;
    private final String birthplace;
    private final String nationality;
    private final String ownedTeamName;
    private final long ownedTeamId;

    public President(long id, String name, LocalDate birthdate, String birthplace, String nationality, String ownedTeamName, long ownedTeamId){
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.nationality = nationality;
        this.ownedTeamName = ownedTeamName;
        this.ownedTeamId = ownedTeamId;
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

    public String getOwnedTeamName() {
        return ownedTeamName;
    }

    public long getOwnedTeamId() {
        return ownedTeamId;
    }
}
