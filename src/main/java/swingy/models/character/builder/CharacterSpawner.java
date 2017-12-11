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
        int[] stats = getBaseStats(characterClass);
        return characterBuilder.createNewCharacter().setName(name)
                .setClass(characterClass).setLevel(1).setExperience(0).setAttack(stats[0])
                .setDefense(stats[1]).setHitpoints(stats[2]).getCharacter();
    }

    public void resetPlayerStats (Character character) {
        int[] stats = getBaseStats(character.get_class());
        character.set_level(1);
        character.set_experience(0);
        character.set_attack(stats[0]);
        character.set_defense(stats[1]);
        character.set_hitpoints(stats[2]);
    }

    public int[] getBaseStats (String characterClass) {
        int[] stats = new int[3];
        if (characterClass.equals("Tank")) {
            stats[0] = 2;
            stats[1] = 10;
            stats[2] = 35;
        } else if (characterClass.equals("Warrior")) {
            stats[0] = 6;
            stats[1] = 7;
            stats[2] = 20;
        }
        return stats;
    }
}
