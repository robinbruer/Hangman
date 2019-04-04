package iths.robin.hangman;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class lostGame_fragment extends Fragment {
    private saveViewModel model;

    /**
     * Empty constructor
     */
    public lostGame_fragment() {
        // Required empty public constructor
    }

    /**
     * Sets a view model for the fragment, sets optionsmenu true and sets the theme of the fragment
     * from the view model.
     * @param inflater for the layout
     * @param container for the view
     * @param savedInstanceState for the fragment
     * @return the layout for the lost game fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        model = ViewModelProviders.of(getActivity()).get(saveViewModel.class);
        setHasOptionsMenu(true);
        getActivity().setTheme(model.getThemeId());
        return inflater.inflate(R.layout.fragment_lost_game_fragment, container, false);
    }

    /**
     * Displays the number of tries left the user had when the Hangman game was lost
     * Displays the right word of the lost Hangman game
     * Set the title for the toolbar and sets the display home button to true
     * Back button that returns to the main menu fragment
     * @param savedInstanceState for the fragment
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTexttoView(Integer.toString(model.getHangman().getTriesLeft()), R.id.triesLeft3);
        setTexttoView(model.getHangman().getRealWord(), R.id.displayWord3);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.result);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().findViewById(R.id.back).setOnClickListener(this::back);
    }

    private void back(View view){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        home_fragment home_fragment = new home_fragment();
        fragmentTransaction.replace(R.id.framelayout, home_fragment);
        fragmentTransaction.commit();
    }

    private void setTexttoView(String text, int displayId){
        TextView display = getView().findViewById(displayId);
        display.setText(text);
    }

    /**
     * Clears the current toolbar and replaces it with a custom toolbar for the lost game fragment
     * @param menu the toolbar
     * @param inflater for the menu layout
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_all,menu);
    }

}
