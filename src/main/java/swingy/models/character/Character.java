package swingy.models.character;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Character {
    @NotNull
    private String _name;
    @NotNull
    private String _class;
    @Min(value = 1)
    private int _level;
    @Min(value = 0)
    private int _experience;
    @Min(value = 1)
    private int _attack;
    @Min(value = 1)
    private int _defense;
    private int _hitpoints;
    private int[] pos = new int[2];
    private Artifact weapon = new Artifact("Weapon", "none", 0);
    private Artifact armor = new Artifact("Armor", "none", 0);
    private Artifact helm = new Artifact("Helm", "none", 0);

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
                ", Hitpoints=" + _hitpoints +
                ", Weapon=" + weapon.name + "(" + weapon.stat + ")" +
                ", Armor=" + armor.name + "(" + armor.stat + ")" +
                ", Helm=" + helm.name + "(" + helm.stat + ")";
    }

    public Artifact getWeapon() {
        return weapon;
    }

    public void setWeapon(Artifact weapon) {
        _attack -= this.weapon.stat;
        this.weapon.name = weapon.name;
        this.weapon.stat = weapon.stat;
        _attack += weapon.stat;
    }

    public Artifact getArmor() {
        return armor;
    }

    public void setArmor(Artifact armor) {
        _defense -= this.armor.stat;
        this.armor.name = armor.name;
        this.armor.stat = armor.stat;
        _defense += armor.stat;
    }

    public Artifact getHelm() {
        return helm;
    }

    public void setHelm(Artifact helm) {
        _hitpoints -= this.helm.stat;
        this.helm.name = helm.name;
        this.helm.stat = helm.stat;
        _hitpoints += helm.stat;
    }

    public String getStats () {
        return _name + "[hp: " + _hitpoints + ", def: " + _defense + ", atk: " + _attack + "]";
    }

    public String getEquipment () {
        return weapon + ", " + armor + ", " + helm;
    }

    public void takeEquipment (Artifact weapon, Artifact armor, Artifact helm) {
        if (weapon.stat > this.weapon.stat) {
            setWeapon(weapon);
        }
        if (armor.stat > this.armor.stat) {
            setArmor(armor);
        }
        if (helm.stat > this.helm.stat) {
            setHelm(helm);
        }
    }
}
