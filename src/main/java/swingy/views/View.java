package swingy.views;

import swingy.models.character.Character;

import java.util.ArrayList;

public interface View {
    public void showCharacterSelection (ArrayList<Character> characters);
    public int getOption();
    public void displayMessage (String msg);
    public boolean goToPreviousMenu ();
    public void showStartMenu ();
    public void exit ();
    public void showCharacterCreation ();
    public String[] getNameClass ();
    public void showPlayScreen (Character character);
    public void showYesNoDialog (String question);
    public boolean getAnswer ();
    public void showNavigationOptions (Character character);
    public void showFightSummary (ArrayList<String> summary);
}
