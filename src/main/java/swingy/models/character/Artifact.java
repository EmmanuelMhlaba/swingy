package swingy.models.character;

public class Artifact {
    public String type = "";
    public String name = "";
    public int stat = 0;

    public Artifact(String type, String name, int stat) {
        this.type = type;
        this.name = name;
        this.stat = stat;
    }

    public String toString () {
        return name + "(" + type + "): " + stat;
    }
}
