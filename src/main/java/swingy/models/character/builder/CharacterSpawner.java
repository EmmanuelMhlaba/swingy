package swingy.models.character.builder;

import swingy.models.character.Character;

public class CharacterSpawner {
    private CharacterBuilder characterBuilder = new CharacterBuilder();

    public Character spawnBeast(String type) {
        Character character = null;
        if (type.equals("weak")) {
            character = characterBuilder.createNewCharacter().setName("Snake")
                    .setClass("Weak Beast").setLevel(1).setExperience(0).setAttack(3)
                    .setDefense(7).setHitpoints(15).getCharacter();
        } else if (type.equals("average")) {
            character = characterBuilder.createNewCharacter().setName("Wolf")
                    .setClass("Average Beast").setLevel(3).setExperience(0).setAttack(12)
                    .setDefense(21).setHitpoints(40).getCharacter();
        } else if (type.equals("strong")) {
            character = characterBuilder.createNewCharacter().setName("Bear")
                    .setClass("Strong Beast").setLevel(6).setExperience(0).setAttack(23)
                    .setDefense(44).setHitpoints(90).getCharacter();
        } else if (type.equals("mythical")) {
            character = characterBuilder.createNewCharacter().setName("Hydra")
                    .setClass("Mythical Beast").setLevel(9).setExperience(0).setAttack(44)
                    .setDefense(90).setHitpoints(300).getCharacter();
        }
        return character;
    }

    public Character spawnCharacter (String name, String characterClass) {
        return characterBuilder.createNewCharacter().setName(name)
                .setClass(characterClass).setLevel(1).setExperience(0).setAttack(4)
                .setDefense(6).setHitpoints(20).getCharacter();
    }

    public void resetPlayerStats (Character character) {
        character.set_level(1);
        character.set_experience(0);
        character.set_attack(4);
        character.set_defense(6);
        character.set_hitpoints(20);
    }
}
