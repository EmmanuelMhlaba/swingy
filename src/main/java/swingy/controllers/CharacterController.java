package swingy.controllers;

import swingy.models.character.Character;
import swingy.models.character.CharacterModel;
import swingy.models.character.builder.CharacterSpawner;
import swingy.views.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CharacterController {
    private View view;
    private CharacterModel characterModel;
    private Character character = null;
    private ArrayList<Character> enemies = new ArrayList<Character>();
    private CharacterSpawner characterSpawner = new CharacterSpawner();
    private Random random= new Random ();
    private int mapSize = -1;

    public CharacterController(View view, CharacterModel characterModel) {
        this.view = view;
        this.characterModel = characterModel;
    }

    private void selectCharacter () {
        view.showCharacterSelection(characterModel.getCharacters());
        if (!view.goToPreviousMenu()) {
            character = characterModel.getCharacters().get(view.getOption());
            play();
        } else {
            startGame();
        }
    }

    private void createCharacter () {
        view.showCharacterCreation();
        String[] nameClass = view.getNameClass();
        character = characterSpawner.spawnCharacter(nameClass[0], nameClass[1]);
        characterModel.save(character);
        play();
    }

    public void startGame () {
        view.showStartMenu();
        if (view.getOption() == 1) {
            selectCharacter();
        } else if (view.getOption() == 2){
            createCharacter();
        } else if (view.getOption() == 3) {
            view.showYesNoDialog("Are you sure you want to exit?");
            if (view.getAnswer()) {
                view.exit();
                characterModel.save();
                System.exit(0);
            } else {
                startGame();
            }
        }
    }

    private void play () {
        int[] pos = new int[2];
        mapSize = (character.get_level() - 1) * 5 + 10 - (character.get_level() % 2);
        pos[0] = pos[1] = mapSize / 2;
        character.set_pos(pos);
        populateEnemies();
        view.showPlayScreen(character);
        gameLoop();
        if (character.get_hitpoints() > 0) {
            character.set_experience(character.get_experience() + 10);
            levelUpCharacter();
            characterModel.save();
            view.showYesNoDialog("You have completed this stage.\nDo you want to play another game?");
            if (view.getAnswer()) {
                play();
            } else {
                startGame();
            }
        } else {
            characterSpawner.resetPlayerStats(character);
            characterModel.save();
            view.showYesNoDialog("You have failed to complete this stage.\n Do you want to try again?");
            if (view.getAnswer()) {
                play();
            } else {
                startGame();
            }
        }
    }

    private void populateEnemies () {
        String[] types = {"weak", "average", "strong", "mythical"};
        int[] pos = new int[2];
        for (int loop = 0; loop < mapSize / 3; loop++) {
            int type = random.nextInt(4);
            pos[0] = random.nextInt(mapSize - 1) + 1;
            pos[1] = random.nextInt(mapSize - 1) + 1;
            Character tmpC = characterSpawner.spawnBeast(types[type]);
            tmpC.set_pos(pos);
            enemies.add(tmpC);
        }
        for (Character c : enemies) {
            System.out.println (c.get_name() + " - " + Arrays.toString(c.get_pos()));
        }
    }

    private boolean inBounds () {
        return character.get_pos()[0] > 0 && character.get_pos()[0] < mapSize &&
                character.get_pos()[1] > 0 && character.get_pos()[1] < mapSize;
    }

    private void gameLoop () {
        int[] oldPos = new int[2];
        System.arraycopy(character.get_pos(), 0, oldPos, 0, oldPos.length);
        while (inBounds() && character.get_hitpoints() > 0) {
            int decision = random.nextInt(100) + 1;
            for (Character c : enemies) {
                if (Arrays.equals(c.get_pos(), character.get_pos())) {
                    view.showYesNoDialog("Do you want to fight: '" + c.get_name() + "'");
                    if (view.getAnswer()) {
                        fight(c);
                    } else {
                        if (decision >= 50) {
                            System.arraycopy(oldPos, 0, character.get_pos(), 0, oldPos.length);
                            view.displayMessage(character.get_name() + " ran away to position: " + Arrays.toString(character.get_pos()));
                        } else {
                            fight(c);
                        }
                    }
                }
            }
            System.arraycopy(character.get_pos(), 0, oldPos, 0, oldPos.length);
            navigate ();
        }
    }

    private void fight (Character c) {
        ArrayList<String> actions = new ArrayList<String>();
        actions.add(character.get_name() + " has engaged combat with: " + c.get_name());
        while (c.get_hitpoints() > 0 && character.get_hitpoints() > 0) {
            int decision = random.nextInt(100) + 1;
            if (decision <= 50) {
                c.set_hitpoints(c.get_hitpoints() - character.get_attack());
                actions.add(character.get_name() + " attacks " + c.get_name() + " causing " +
                        character.get_attack() + " damage. (" + character.get_name() + ": " +
                        character.get_hitpoints() + "; " + c.get_name() + ": " + c.get_hitpoints() + ")");
            } else {
                character.set_hitpoints(character.get_hitpoints() - c.get_attack());
                actions.add(c.get_name() + " attacks " + character.get_name() + " causing " +
                        c.get_attack() + " damage. (" + character.get_name() + ": " +
                        character.get_hitpoints() + "; " + c.get_name() + ": " + c.get_hitpoints() + ")");
            }
        }
        if (character.get_hitpoints() > 0) {
            character.set_experience(character.get_experience() + (100 * c.get_level()));
            levelUpCharacter();
            characterModel.save();
            actions.add(character.get_name() + " has defeated " + c.get_name());
            view.showFightSummary(actions);
        } else {
            actions.add(character.get_name() + " has been defeated by " + c.get_name());
            view.showFightSummary(actions);
            startGame();
        }
    }

    private void navigate () {
        int[] tmp = character.get_pos();
        view.showNavigationOptions(character);
        switch (view.getOption()) {
            case 1:
                tmp[1] += 1;
                break;
            case 2:
                tmp[0] += 1;
                break;
            case 3:
                tmp[1] -= 1;
                break;
            case 4:
                tmp[0] -= 1;
                break;
            case 5:
                view.showYesNoDialog("Are you sure you want to go to the main menu?");
                if (view.getAnswer()) {
                    startGame();
                }
                break;
        }
        character.set_pos(tmp);
    }

    private void levelUpCharacter () {
        int xpNeeded = (int) (character.get_level() * 1000 + Math.pow(character.get_level() - 1, 2) * 450);
        if (character.get_experience() >= xpNeeded) {
            character.set_experience(character.get_experience() - xpNeeded);
            character.set_attack((int) (character.get_attack() * 1.5));
            character.set_hitpoints((int) (20 * (1.6 * character.get_level())));
            character.set_defense((int) (character.get_defense() * 1.7));
            character.set_level(character.get_level() + 1);
            view.displayMessage(character.get_name() + " is now at level " + character.get_level());
        }
    }
}
