package swingy.models.character.builder;

import swingy.models.character.Character;

public class CharacterBuilder {
    protected Character character;

    public Character getCharacter () {
        return character;
    }

    public CharacterBuilder createNewCharacter () {
        character = new Character(
                "",
                "",
                0,
                0,
                0,
                0,
                0
        );
        return this;
    }

    public CharacterBuilder setName (String name) {
        character.set_name(name);
        return this;
    }

    public CharacterBuilder setClass (String characterClass) {
        character.set_class(characterClass);
        return this;
    }

    public CharacterBuilder setLevel (int level) {
        character.set_level(level);
        return this;
    }

    public CharacterBuilder setExperience (int experience) {
        character.set_experience(experience);
        return this;
    }

    public CharacterBuilder setAttack (int attack) {
        character.set_attack(attack);
        return this;
    }

    public CharacterBuilder setDefense (int defense) {
        character.set_defense(defense);
        return this;
    }

    public CharacterBuilder setHitpoints (int hitpoints) {
        character.set_hitpoints(hitpoints);
        return this;
    }
}
