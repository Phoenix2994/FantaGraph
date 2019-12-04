package model;

public class Prosecutor {
    private final long id;
    private final String name;


    public Prosecutor(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

}
