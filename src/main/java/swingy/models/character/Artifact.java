package swingy.models.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Artifact {
    @NotNull
    public String type = "";
    @NotNull
    public String name = "";
    @Min(value = 1)
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
