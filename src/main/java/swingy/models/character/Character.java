package swingy.models.character;

public class Character {
    private String _name;
    private String _class;
    private int _level;
    private int _experience;
    private int _attack;
    private int _defense;
    private int _hitpoints;
    private int[] pos = new int[2];

    public Character(String _name, String _class, int _level, int _experience, int _attack, int _defense, int _hitpoints) {
        this._name = _name;
        this._class = _class;
        this._level = _level;
        this._experience = _experience;
        this._attack = _attack;
        this._defense = _defense;
        this._hitpoints = _hitpoints;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public int get_experience() {
        return _experience;
    }

    public void set_experience(int _experience) {
        this._experience = _experience;
    }

    public int get_attack() {
        return _attack;
    }

    public void set_attack(int _attack) {
        this._attack = _attack;
    }

    public int get_defense() {
        return _defense;
    }

    public void set_defense(int _defense) {
        this._defense = _defense;
    }

    public int get_hitpoints() {
        return _hitpoints;
    }

    public void set_hitpoints(int _hitpoints) {
        this._hitpoints = _hitpoints;
    }

    public int[] get_pos() {
        return pos;
    }

    public void set_pos(int[] pos) {
        System.arraycopy(pos, 0, this.pos, 0, pos.length);
    }

    @Override
    public String toString() {
        return "Name='" + _name + '\'' +
                ", Class='" + _class + '\'' +
                ", Level=" + _level +
                ", Experience=" + _experience +
                ", Attack=" + _attack +
                ", Defense=" + _defense +
                ", Hitpoints=" + _hitpoints;
    }
}
