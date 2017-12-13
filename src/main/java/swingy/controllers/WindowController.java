package swingy.controllers;

import swingy.models.character.Character;
import swingy.models.character.CharacterModel;
import swingy.models.character.builder.CharacterSpawner;
import swingy.views.window.CJPanel1;
import swingy.views.window.WindowView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class WindowController {
    private WindowView view;
    private CharacterModel characterModel;
    private Character character;
    private CharacterSpawner characterSpawner = new CharacterSpawner();
    private int mapSize;
    private Random random = new Random();
    private ArrayList<Character> enemies = new ArrayList<Character>();
    CJPanel1 playPanel = null;

    public WindowController(WindowView view, CharacterModel characterModel) {
        this.view = view;
        this.characterModel = characterModel;
        this.view.setCharacterLoadPanelListener(new CharacterLoadPanelListener());
        this.view.setCharacterCreatePanelListener(new CharacterCreatePanelListener(), new CharacterCreatePanelListener2());
        this.view.setMainMenuPanelListener(new MainMenuPanelListener());
        this.view.setPlayPanelListener(new PlayPanelListener());
    }

    private class MainMenuPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int tmp;
                tmp = Integer.parseInt(view.getMainMenuPanelText());
                switch (tmp) {
                    case 0:
                        view.showCharacterCreatePanel();
                        break;
                    case 1:
                        view.showCharacterLoadPanel(characterModel.getCharacters());
                        break;
                    case 2:
                        view.displayMessage("Exit coming soon");
                        break;
                    default:
                        view.displayMessage("Please enter one of the given options");
                        break;
                }
            } catch (NumberFormatException e1) {
                view.displayMessage("Please enter one of the given options");
            }
            view.clearAllTextBoxes();
        }
    }

    private class CharacterLoadPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int tmp;
                tmp = Integer.parseInt(view.getCharacterLoadPanelText());
                if (tmp >= 0 && tmp <= characterModel.getCharacters().size()) {
                    if (tmp == characterModel.getCharacters().size()) {
                        view.clearAllTextBoxes();
                        view.showMainMenuPanel();
                    } else {
                        character = characterModel.getCharacters().get(tmp);
                        play();
                    }
                } else {
                    view.displayMessage("Please enter one of the given options");
                }
            } catch (NumberFormatException e1) {
                view.displayMessage("Please enter one of the given options");
            }
            view.clearAllTextBoxes();
        }
    }

    private class CharacterCreatePanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] tmp = view.getCharacterCreatePanelText();
            if (tmp[0].length() == 0) {
                view.displayMessage("Please ensure that the name is not empty");
            } else {
                if (tmp[1].equals("Warrior") || tmp[1].equals("Tank")) {
                    character = characterSpawner.spawnCharacter(tmp[0], tmp[1]);
                    characterModel.save(character);
                    play();
                } else {
                    view.displayMessage("Please type 'Warrior' or 'Tank'");
                }
            }
        }
    }

    private class CharacterCreatePanelListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.showMainMenuPanel();
        }
    }

    private class PlayPanelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int[] oldPos = new int[2];
            System.arraycopy(character.get_pos(), 0, oldPos, 0, oldPos.length);
            if (view.getPlayPanelText().trim().equals("")) {
                if (inBounds() && character.get_hitpoints() > 0) {
                    int decision = random.nextInt(100) + 1;
                    for (Character c : enemies) {
                        if (Arrays.equals(c.get_pos(), character.get_pos())) {
                            if (view.showConfirmDialog("Do you want to fight: '" + c.getStats() + "'")) {
                                fight(c);
                                if (character.get_hitpoints() <= 0) {
                                    return;
                                }
                            } else {
                                if (decision >= 50) {
                                    System.arraycopy(oldPos, 0, character.get_pos(), 0, oldPos.length);
                                    playPanel.appendTextAreaText(character.get_name() + " ran away to position: " + Arrays.toString(character.get_pos()) + "\n");
                                } else {
                                    fight(c);
                                    if (character.get_hitpoints() <= 0) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                int tmp;
                try {
                    tmp = Integer.parseInt(view.getPlayPanelText());
                    if (tmp >= 1 && tmp <= 5) {
                        navigate(tmp);
                    } else {
                        view.displayMessage("Please enter one of the given options");
                        view.showNavigationOptions(character);
                    }
                } catch (NumberFormatException e1) {
                    view.displayMessage("Please enter one of the given options");
                    view.showNavigationOptions(character);
                }
            }
            view.clearAllTextBoxes();
            if (character.get_hitpoints() > 0 && !inBounds()) {
                character.set_experience(character.get_experience() + 10);
                levelUpCharacter();
                characterModel.save();
                if (view.showConfirmDialog("You have completed this stage.\nDo you want to play another game?")) {
                    play();
                } else {
                    view.showMainMenuPanel();
                }
            } else if (character.get_hitpoints() <= 0) {
                characterSpawner.resetPlayerStats(character);
                characterModel.save();
                if (view.showConfirmDialog("You have failed to complete this stage.\nDo you want to play another game?")) {
                    play();
                } else {
                    view.showMainMenuPanel();
                }
            } else {
                view.showNavigationOptions(character);
            }
        }
    }

    private void play() {
        playPanel = view.getPlayPanel();
        playPanel.setTextAreaText("");
        view.showPlayPanel();
        int[] pos = new int[2];
        mapSize = (character.get_level() - 1) * 5 + 10 - (character.get_level() % 2);
        pos[0] = pos[1] = mapSize / 2;
        character.set_pos(pos);
        populateEnemies();
        playPanel.appendTextAreaText("Playing with: " + character.get_name() + "\n");
        view.showNavigationOptions(character);
    }

    private void populateEnemies() {
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
        // For testing
        for (Character c : enemies) {
            playPanel.appendTextAreaText(c.get_name() + " - " + Arrays.toString(c.get_pos()) + "\n");
        }
    }

    private void levelUpCharacter() {
        int xpNeeded = (int) (character.get_level() * 1000 + Math.pow(character.get_level() - 1, 2) * 450);
        int[] stats = characterSpawner.getBaseStats(character.get_class());
        if (character.get_experience() >= xpNeeded) {
            character.set_experience(character.get_experience() - xpNeeded);
            character.set_attack((int) (stats[0] * character.get_level() * 1.5));
            character.set_defense((int) (stats[1] * character.get_level() * 1.6));
            character.set_hitpoints((int) (stats[2] * character.get_level() * 1.7));
            character.set_level(character.get_level() + 1);
            view.displayMessage(character.get_name() + " is now at level " + character.get_level());
        }
    }

    private void gameLoop() {

    }

    private boolean inBounds() {
        return character.get_pos()[0] > 0 && character.get_pos()[0] < mapSize &&
                character.get_pos()[1] > 0 && character.get_pos()[1] < mapSize;
    }

    private void fight(Character c) {
        ArrayList<String> actions = new ArrayList<String>();
        playPanel.appendTextAreaText(character.get_name() + " has engaged combat with: " + c.get_name() + "\n");
        playPanel.appendTextAreaText(c.get_name() + " is equipped with: " + c.getEquipment() + "\n");
        while (c.get_hitpoints() > 0 && character.get_hitpoints() > 0) {
            int decision = random.nextInt(100) + 1;
            if (decision <= 50) {
                int damage;
                damage = calculateDamage(character, c);
                c.set_hitpoints(c.get_hitpoints() - damage);
                playPanel.appendTextAreaText(character.get_name() + " attacks " + c.get_name() + " causing " +
                        damage + " damage. (" + character.get_name() + ": " +
                        character.get_hitpoints() + "; " + c.get_name() + ": " + c.get_hitpoints() + ")\n");
            } else {
                int damage;
                damage = calculateDamage(c, character);
                character.set_hitpoints(character.get_hitpoints() - damage);
                playPanel.appendTextAreaText(c.get_name() + " attacks " + character.get_name() + " causing " +
                        damage + " damage. (" + character.get_name() + ": " +
                        character.get_hitpoints() + "; " + c.get_name() + ": " + c.get_hitpoints() + ")\n");
            }
        }
        if (character.get_hitpoints() > 0) {
            character.set_experience(character.get_experience() + (100 * c.get_level()));
            levelUpCharacter();
            characterModel.save();
            playPanel.appendTextAreaText(character.get_name() + " has defeated " + c.get_name() + "\n");
            character.takeEquipment(c.getWeapon(), c.getArmor(), c.getHelm());
            playPanel.appendTextAreaText(character.get_name() + " has found " + c.getEquipment() + "\n");
        } else {
            playPanel.appendTextAreaText(character.get_name() + " has been defeated by " + c.get_name() + "\n");
        }
    }

    private int calculateDamage(Character attacker, Character deffender) {
        int damage;
        if (deffender.get_defense() >= 80) {
            damage = (int) (attacker.get_attack() * 0.2);
            if (damage == 0) {
                damage = 2;
            }
        } else {
            damage = (int) (attacker.get_attack() * ((100 - deffender.get_defense()) / 100.0));
            if (damage == 0) {
                damage = 2;
            }
        }
        return damage;
    }

    private void navigate(int i) {
        int[] tmp = character.get_pos();
        switch (i) {
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
                if (view.showConfirmDialog("Are you sure you want to go to the main menu?")) {
                    characterModel.save();
                    view.showMainMenuPanel();
                }
                break;
        }
        character.set_pos(tmp);
    }
}
