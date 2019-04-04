package iths.robin.hangman;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class game_fragment extends Fragment {
    private saveViewModel model;
    private ImageView image;
    private ArrayList<String> pictures = new ArrayList<>();

    /**
     * Empty constructor
     */
    public game_fragment() {
        // Required empty public constructor
    }

    /**
     * Sets a view model for the fragment, sets optionsmenu true and sets the theme of the fragment
     * from the view model.
     * @param inflater for the layout
     * @param container for the view
     * @param savedInstanceState for the fragment
     * @return the layout for the game fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        model = ViewModelProviders.of(getActivity()).get(saveViewModel.class);
        getActivity().setTheme(model.getThemeId());
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_game_fragment, container, false);
    }

    /**
     * Set the title for the toolbar and sets the display home button to true
     * Sets all the views of the fragment, sets data to the views from a Hangman game that is created
     * in the view model.
     *
     * Displays pictures in an image view depending of the number of guesses in the Hangman game the user have left
     * @param savedInstanceState for the fragment
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        image = getView().findViewById(R.id.hangmanImage);
        if (model.isActive()){
            model.newHangman(getResources().getStringArray(R.array.hangwords));
            model.setActive(false);
        }
        setTexttoView(model.getHangman().getHiddenWord(), R.id.displayWord);
        triesLeft(model.getHangman().getTriesLeft());
        Glide.with(this).load(setHangmanPicture(model.getHangman().getTriesLeft())).into(image);
        setTexttoView(model.getHangman().getWrongLetters(), R.id.wrongLetters);
        getActivity().findViewById(R.id.guessButton).setOnClickListener(this::guess);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void guess(View view) {
        Context context = getContext().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if (getinput().length() > 1){
            Toast toast = Toast.makeText(context,R.string.onlyOneLetter,duration);
            toast.show();
            hideKeyboard();
        }
        if(model.getHangman().getWrongLetters().contains(getinput().toLowerCase()) && getinput().length() == 1 ||
                model.getHangman().getRightLetters().contains(getinput().toLowerCase()) && getinput().length() == 1){
            Toast toast = Toast.makeText(context,R.string.alreadyusedLetter,duration);
            toast.show();
            hideKeyboard();
        }
        else if(getinput().length() == 1){
            model.getHangman().guess(getinput().charAt(0));
            setTexttoView(model.getHangman().getHiddenWord(), R.id.displayWord);
            setTexttoView(model.getHangman().getWrongLetters(), R.id.wrongLetters);
            triesLeft(model.getHangman().getTriesLeft());
            Glide.with(this).load(setHangmanPicture(model.getHangman().getTriesLeft())).into(image);
            gameOver(view);
            hideKeyboard();
            clear();
        }
    }


    private String setHangmanPicture(int index){
      if (model.isTheme()){
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang0.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang1.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang2.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang3.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang4.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang5.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang6.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang7.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang8.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang9.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang10.gif");
        }else if(!model.isTheme()){
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanOriginal/hang0.gif");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/10.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/9.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/8.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/7.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/6.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/5.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/4.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/3.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/2.png");
            pictures.add("https://raw.githubusercontent.com/robinbruer/HangmanImages/master/docs/hangmanHalloween/1.png");
        }
        return pictures.get(index);
    }

    private void setTexttoView(String text, int displayId){
        TextView display = getView().findViewById(displayId);
        display.setText(text);
    }

    private void triesLeft(int number){
        TextView tries = getView().findViewById(R.id.trysLeft);
        tries.setText(Integer.toString(number));
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void gameOver(View view){
        if(model.getHangman().hasWon()){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            wonGame_fragment wonGame_fragment = new wonGame_fragment();
            fragmentTransaction.replace(R.id.framelayout, wonGame_fragment);
            fragmentTransaction.commit();
            hideKeyboard();
        }
        if(model.getHangman().hasLost()){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            lostGame_fragment lostGame_fragment = new lostGame_fragment();
            fragmentTransaction.replace(R.id.framelayout, lostGame_fragment);
            fragmentTransaction.commit();
            hideKeyboard();
        }
    }

    private void clear(){
        EditText input = getView().findViewById(R.id.guessText);
        input.getText().clear();
    }

    private String getinput(){
        EditText input = getView().findViewById(R.id.guessText);
        return input.getText().toString();
    }

    /**
     * Clears the current toolbar and replaces it with a custom toolbar for the game fragment
     * @param menu the toolbar
     * @param inflater for the menu layout
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_game,menu);
    }
}
