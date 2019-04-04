package iths.robin.hangman;

import android.arch.lifecycle.ViewModel;

public class saveViewModel extends ViewModel {

    private boolean theme = true;
    private boolean active = true;
    private Hangman hangman;

    /**
     * Get the id for the theme
     * @return the id of the theme
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Sets the id for the theme
     * @param themeId the id of the theme
     */
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    private int themeId;

    /**
     * @return the current Hangman game
     */
    public Hangman getHangman() {
        return hangman;
    }

    /**
     * Sets a new Hangman game
     * @param s String array of words to choose from
     * @return a new Hangman game
     */
    public Hangman newHangman(String[] s){
        this.hangman = new Hangman();
        hangman.newWord(s);
        return hangman;
    }

    /**
     * Cecks if the game is active or not
     * @return true or false depending on active game or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set it the game is active or not
     * @param active boolean to check if game is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Check if the theme is default or not
     * @return true or false
     */
    public boolean isTheme() {
        return theme;
    }

    /**
     * Set a new theme is choosen
     * @param theme of the application
     */
    public void setTheme(boolean theme) {
        this.theme = theme;
    }
}
