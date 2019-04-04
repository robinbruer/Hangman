package iths.robin.hangman;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Hangman {
    private int triesLeft = 10;
    private Random random = new Random();
    private StringBuilder StringBuilder = new StringBuilder();
    private String realWord;
    private char tempGuess;
    private StringBuilder wrongLetters = new StringBuilder();
    private StringBuilder rightLetters = new StringBuilder();
    private ArrayList<String> words;

    /**
     * String of all the right letters of the word the user have guessed
     * @return String of all the right letters
     */
    public String getRightLetters() {
        return rightLetters.toString();
    }

    /**
     * String of all the wrong letters of the word the user have guessed
     * @return String of all the wrong letters
     */
    public String getWrongLetters() {
        return wrongLetters.toString();
    }

    /**
     * Chose a new word. Changes the currnet word, by randomly choosing a word from all available words.
     * @return String of a new word
     */
    public String newWord(String[] s) {
        words = new ArrayList<>(Arrays.asList(s));
        realWord = words.get(random.nextInt(words.size()));
        return realWord;
    }

    /**
     * Get the number of tries left from 10-0
     * @return number of tries left
     */
    public int getTriesLeft() {
        return triesLeft;
    }

    /**
     * Prints the current random word and hiding all the letters the user haven't guessed
     * @return The current word, with only right letters guess showing, hiding the wrong letters
     */
    public String getHiddenWord() {
        char[] hidden = realWord.toCharArray();
            for (int i = 0; i < hidden.length; i++) {
                hidden[i] = '-';
                if (hasUsedLetter(tempGuess)){
                    for (int j = 0; j < hidden.length; j++) {
                        for (int k = 0; k < getRightLetters().length() ; k++) {
                            if (realWord.charAt(j)==getRightLetters().charAt(k)){
                                hidden[j]=getRightLetters().charAt(k);
                            }
                        }
                    }
                }
            }
        String b = new String(hidden);
        return b;
    }

    /**
     * Returns the current word
     * @return the currnt word
     */
    public String getRealWord(){
        return realWord;
    }

    /**
     * Check if the char have been guessed by the user
     * @param c the letter to check
     * @return true if the char have been used, false if its free
     */
    public boolean hasUsedLetter(char c){
        if (StringBuilder.toString().contains(Character.toString(c))){
            return true;
        }else{
            StringBuilder.append(c);
            return false;
        }
    }

    /**
     * Makes a guess for a letter, if the letter is wrong it gets added to wrongletters, if its right
     * it gets added to rightletters. If the letter is wrong the tries left gets reduced.
     * @param guess the letter the user have guessed
     */
    public void guess(char guess){
        guess = Character.toLowerCase(guess);
        StringBuilder remove = new StringBuilder(realWord);
        String checkWord = realWord;
        tempGuess = guess;


        for (int i = 0; i < realWord.length(); i++)

        {
            if (!rightLetters.toString().contains(Character.toString(guess)))
                if (checkWord.indexOf(guess) == -1){
                    if (!wrongLetters.toString().contains(Character.toString(guess))){
                        triesLeft --;
                        wrongLetters.append(guess);
                        wrongLetters.append(", ");
                    }
                } else {
                    if (!rightLetters.toString().contains(Character.toString(guess))){
                        rightLetters.append(guess);
                        getHiddenWord();
                    }
                    remove.deleteCharAt(checkWord.indexOf(guess));
                    checkWord = remove.toString();
                }
        }
    }

    /**
     * Checks if the user have won or not, compares the right letters guessed with the right word.
     * @return true if the user guessed the right word, false if not.
     */
    public boolean hasWon(){
        char[] first = getRightLetters().toCharArray();
        char[] second = realWord.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        if (Arrays.equals(first, removeDuplicate(second))){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks if the user have lost the game
     * @return true if the user have used up all the 10 guesses, false if not
     */
    public boolean hasLost(){
        if (triesLeft == 0){
            return true;
        }else {
            return false;
        }
    }

    private char[] removeDuplicate(char[] array){
        boolean[] b = new  boolean[256];
        StringBuilder sb = new StringBuilder();
        for (char c: array) {
            if (!b[c]){
                b[c] = true;
                sb.append(c);
            }
        }
        return sb.toString().toCharArray();
    }
}