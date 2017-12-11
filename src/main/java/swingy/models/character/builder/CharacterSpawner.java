package swingy.models.character.builder;

import swingy.models.character.Artifact;
import swingy.models.character.Character;

import java.util.Random;

public class CharacterSpawner {
    private Random random = new Random();
    private CharacterBuilder characterBuilder = new CharacterBuilder();

    public Character spawnBeast(String type) {
        Character character = null;
        int helmChance = random.nextInt(100) + 1;
        int armorChance = random.nextInt(100) + 1;
        int weaponChance = random.nextInt(100) + 1;
        if (type.equals("weak")) {
            character = characterBuilder.createNewCharacter().setName("Snake")
                    .setClass("Weak Beast").setLevel(1).setExperience(0).setAttack(3)
                    .setDefense(7).setHitpoints(15).getCharacter();
            if (helmChance >= 50) {
                character.setHelm(new Artifact("Helm", "Wood", 8));
            } if (armorChance >= 50) {
                character.setArmor(new Artifact("Armor", "Wood", 6));
            } if (weaponChance >= 50) {
                character.setWeapon(new Artifact("Weapon", "Wood", 4));
            }
        } else if (type.equals("average")) {
            character = characterBuilder.createNewCharacter().setName("Wolf")
                    .setClass("Average Beast").setLevel(3).setExperience(0).setAttack(12)
                    .setDefense(21).setHitpoints(40).getCharacter();
            if (helmChance >= 50) {
                character.setHelm(new Artifact("Helm", "Iron", 17));
            } if (armorChance >= 50) {
                character.setArmor(new Artifact("Armor", "Iron", 14));
            } if (weaponChance >= 50) {
                character.setWeapon(new Artifact("Weapon", "Iron", 11));
            }
        } else if (type.equals("strong")) {
            character = characterBuilder.createNewCharacter().setName("Bear")
                    .setClass("Strong Beast").setLevel(6).setExperience(0).setAttack(23)
                    .setDefense(44).setHitpoints(90).getCharacter();
            if (helmChance >= 50) {
                character.setHelm(new Artifact("Helm", "Diamond", 39));
            } if (armorChance >= 50) {
                character.setArmor(new Artifact("Armor", "Diamond", 35));
            } if (weaponChance >= 50) {
                character.setWeapon(new Artifact("Weapon", "Diamond", 31));
            }
        } else if (type.equals("mythical")) {
            character = characterBuilder.createNewCharacter().setName("Hydra")
                    .setClass("Mythical Beast").setLevel(9).setExperience(0).setAttack(44)
                    .setDefense(90).setHitpoints(300).getCharacter();
            if (helmChance >= 50) {
                character.setHelm(new Artifact("Helm", "Adamantite", 77));
            } if (armorChance >= 50) {
                character.setArmor(new Artifact("Armor", "Adamantite", 73));
            } if (weaponChance >= 50) {
                character.setWeapon(new Artifact("Weapon", "Adamantite", 70));
            }
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
        character.setArmor(new Artifact("Armor", "none", 0));
        character.setHelm(new Artifact("Helm", "none", 0));
        character.setWeapon(new Artifact("Weapon", "none", 0));
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
