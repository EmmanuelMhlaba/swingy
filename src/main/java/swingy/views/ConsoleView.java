package swingy.views;

import swingy.models.character.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleView implements View {
    private Scanner scanner = new Scanner(System.in);
    private int option;
    private boolean previousMenu = false;
    private boolean answer = false;
    private String[] nameClass = new String[2];

    public ConsoleView() {
    }

    public void showCharacterSelection (ArrayList<Character> characters) {
        option = -1;
        previousMenu = false;
        int index = 0;
        System.out.println ("===== Character selection =====");
        for (Character character : characters) {
            System.out.println (index++ + ") " + character);
        }
        System.out.println (index + ") Back to previous menu.");
        System.out.print("Select a character: ");
        if (scanner.hasNextInt()) {
            int tmp = scanner.nextInt();
            if (tmp == index) {
                previousMenu = true;
                System.out.println ("==========\n");
            } else if (tmp >= 0 && tmp < index) {
                option = tmp;
                System.out.println ("==========\n");
            } else {
                displayMessage ("Please select one of the provided options.");
                showCharacterSelection(characters);
            }
        } else {
            scanner.next();
            displayMessage ("Please select one of the provided options.");
            showCharacterSelection(characters);
        }
    }

    public int getOption() {
        return option;
    }


    public void displayMessage(String msg) {
        System.out.println ("==========\n" + msg + "\n==========\n");
    }

    public boolean goToPreviousMenu() {
        return previousMenu;
    }

    public void showStartMenu() {
        System.out.println ("===== Swingy =====");
        System.out.println ("1) Select a character");
        System.out.println ("2) Create a character");
        System.out.println ("3) Exit");
        System.out.print ("Select an option: ");
        if (scanner.hasNextInt()) {
            int tmp = scanner.nextInt();
            if (tmp >= 1 && tmp <= 3) {
                option = tmp;
                System.out.println ("==========\n");
            } else {
                displayMessage ("Please select one of the provided options.");
                showStartMenu();
            }
        } else {
            scanner.next();
            displayMessage ("Please select one of the provided options.");
            showStartMenu();
        }
    }

    public void exit() {
        displayMessage("Goodbye");
    }

    public void showCharacterCreation() {
        nameClass[0] = nameClass[1] = "";
        System.out.println ("===== Character creation =====");
        System.out.print ("Enter character's name: ");
        String tmp = scanner.next().trim();
        if (tmp.length() == 0) {
            displayMessage("Please enter the character's name");
            showCharacterCreation();
        } else {
            nameClass[0] = tmp;
            System.out.print ("Enter the character's class (e.g. 'Warrior' or 'Thief'): ");
            tmp = scanner.next().trim();
            if (tmp.length() == 0) {
                displayMessage("Please enter the character's class");
                showCharacterCreation();
            } else {
                nameClass[1] = tmp;
                System.out.println ("==========\n");
            }
        }
    }

    public String[] getNameClass() {
        return nameClass;
    }

    public void showPlayScreen(Character character) {
        displayMessage("Playing with: '" + character.get_name() + "'");
    }

    public void showYesNoDialog(String question) {
        answer = false;
        System.out.println("==========");
        System.out.print(question + " (yes/no): ");
        String ans = scanner.next().trim();
        if (ans.equals("yes")) {
            answer = true;
            System.out.println("==========\n");
        } else if (ans.equals("no")) {
            answer = false;
            System.out.println("==========\n");
        } else {
            displayMessage("Please enter yes or no.");
            showYesNoDialog(question);
        }
    }

    public boolean getAnswer() {
        return answer;
    }

    public void showNavigationOptions(Character character) {
        System.out.println ("===== Navigation =====");
        System.out.println ("Current direction: " + Arrays.toString(character.get_pos()));
        System.out.println ("Select a direction to move towards");
        System.out.println ("1) North");
        System.out.println ("2) East");
        System.out.println ("3) South");
        System.out.println ("4) West");
        System.out.println ("5) Main menu");
        System.out.print ("Select an option: ");
        if (scanner.hasNextInt()) {
            int tmp = scanner.nextInt();
            if (tmp >= 1 && tmp <= 5) {
                option = tmp;
                System.out.println ("==========\n");
            } else {
                displayMessage("Please select one of the provided options.");
                showNavigationOptions(character);
            }
        } else {
            scanner.next();
            displayMessage("Please select one of the provided options.");
            showNavigationOptions(character);
        }
    }

    public void showFightSummary(ArrayList<String> summary) {
        System.out.println ("===== Fight =====");
        for (String s : summary) {
            System.out.println (s);
        }
        System.out.println ("==========\n");
    }
}
