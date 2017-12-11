package swingy.models.character;

import swingy.models.Model;

import java.util.ArrayList;

public class CharacterModel extends Model {
    private ArrayList<Character> characters = new ArrayList<Character>();

    public CharacterModel() {
        super();
        filename = "Characters.txt";
        load();
    }

    private void load () {
        String _name = "", _class = "";
        int _level = 0, _experience = 0, _attack = 0, _defense = 0, _hitpoints = 0;
        Artifact weapon  = new Artifact("Weapon", "none", 0);
        Artifact armor  = new Artifact("Armor", "none", 0);
        Artifact helm  = new Artifact("Helm", "none", 0);
        ArrayList<String> list = readFile();
        int line = 1;
        for (String s : list) {
            String[] fields = s.split(",");
            if (fields.length == 10) {
                for (String f : fields) {
                    String[] vals = f.split("=");
                    if (vals.length == 2) {
                        vals[0] = vals[0].trim();
                        vals[1] = vals[1].trim();
                        try {
                            if (vals[0].equals("Name")) {
                                _name = vals[1].substring(1, vals[1].length() - 1);
                            } else if (vals[0].equals("Class")) {
                                _class = vals[1].substring(1, vals[1].length() - 1);
                            } else if (vals[0].equals("Level")) {
                                _level = Integer.parseInt(vals[1]);
                            } else if (vals[0].equals("Experience")) {
                                _experience = Integer.parseInt(vals[1]);
                            } else if (vals[0].equals("Attack")) {
                                _attack = Integer.parseInt(vals[1]);
                            } else if (vals[0].equals("Defense")) {
                                _defense = Integer.parseInt(vals[1]);
                            } else if (vals[0].equals("Hitpoints")) {
                                _hitpoints = Integer.parseInt(vals[1]);
                            } else if (vals[0].equals("Weapon")) {
                                String[] tmp1 = vals[1].split("\\(");
                                String tmp2 = tmp1[1].substring(0, tmp1[1].length() - 1);
                                weapon.name = tmp1[0].trim();
                                weapon.stat = Integer.parseInt(tmp2);
                            } else if (vals[0].equals("Armor")) {
                                String[] tmp1 = vals[1].split("\\(");
                                String tmp2 = tmp1[1].substring(0, tmp1[1].length() - 1);
                                armor.name = tmp1[0].trim();
                                armor.stat = Integer.parseInt(tmp2);
                            } else if (vals[0].equals("Helm")) {
                                String[] tmp1 = vals[1].split("\\(");
                                String tmp2 = tmp1[1].substring(0, tmp1[1].length() - 1);
                                helm.name = tmp1[0].trim();
                                helm.stat = Integer.parseInt(tmp2);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println(e.getMessage());
                        }
                    } else {
                        System.err.println("Error: format is incorrect in file '" + filename + "' at line: " + line + ".");
                    }
                }
                Character character = new Character(_name, _class, _level, _experience, _attack, _defense, _hitpoints);
                character.setWeapon(weapon);
                character.setArmor(armor);
                character.setHelm(helm);
                characters.add(character);
            } else {
                System.err.println("Error: format is incorrect in file '" + filename + "' at line: " + line + ".");
            }
            line++;
        }
    }

    public void save (Character character) {
        characters.add(character);
        save();
    }

    public void save () {
        ArrayList<String> strings = new ArrayList<String>();
        for (Character character : characters) {
            strings.add(character.toString());
        }
        writeFile(strings);
    }

    public ArrayList<Character> getCharacters () {
        return characters;
    }
}
