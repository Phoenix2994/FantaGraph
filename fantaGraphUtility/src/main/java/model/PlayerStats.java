package model;

public class PlayerStats {
    private String season;
    private String team;
    private long playedMatches;
    private long goals;
    private long assists;
    private long concededGoals;
    private long ownGoals;
    private long redCards;
    private long yellowCards;
    private double average;
    private double fantaAverage;
    private double avgGaussianRating;
    private double avgGaussianFantaRating;

    public PlayerStats(String season,
                       String team,
                       long playedMatches,
                       long goals,
                       long assists,
                       long concededGoals,
                       long ownGoals,
                       long redCards,
                       long yellowCards,
                       double average,
                       double fantamedia,
                       double avgGaussianRating,
                       double avgGaussianFantaRating){
        this.season = season;
        this.team = team;
        this.playedMatches = playedMatches;
        this.goals = goals;
        this.assists = assists;
        this.concededGoals = concededGoals;
        this.ownGoals = ownGoals;
        this.redCards = redCards;
        this.yellowCards = yellowCards;
        this.average = average;
        this.fantaAverage = fantamedia;
        this.avgGaussianRating = avgGaussianRating;
        this.avgGaussianFantaRating = avgGaussianFantaRating;
    }

    public String getSeason() {
        return season;
    }

    public String getTeam() {
        return team;
    }

    public long getPlayedMatches() {
        return playedMatches;
    }

    public long getGoals() {
        return goals;
    }

    public long getAssists() {
        return assists;
    }

    public long getConcededGoals() {
        return concededGoals;
    }

    public long getOwnGoals() {
        return ownGoals;
    }

    public long getRedCards() {
        return redCards;
    }

    public long getYellowCards() {
        return yellowCards;
    }

    public double getAverage() {
        return average;
    }

    public double getFantaAverage() {
        return fantaAverage;
    }

    public double getAvgGaussianRating() {
        return avgGaussianRating;
    }

    public double getAvgGaussianFantaRating() {
        return avgGaussianFantaRating;
    }
}
