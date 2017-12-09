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
            System.out.println(tmpC + " - " + Arrays.toString(tmpC.get_pos()));
            enemies.add(tmpC);
        }
    }

    private boolean inBounds () {
        return character.get_pos()[0] > 0 && character.get_pos()[0] < mapSize &&
                character.get_pos()[1] > 0 && character.get_pos()[1] < mapSize;
    }

    private void gameLoop () {
        int decision = random.nextInt(100) + 1;
        int[] oldPos = new int[2];
        System.arraycopy(character.get_pos(), 0, oldPos, 0, oldPos.length);
        while (inBounds() && character.get_hitpoints() > 0) {
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
        view.displayMessage(character.get_name() + " has engaged combat with: " + c.get_name());
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
}
