package iths.robin.hangman;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {
    private saveViewModel model;
    //public static int position;

    /**
     * Empty constructor
     */
    public home_fragment() {
        // Required empty public constructor
    }

    /**
     * Sets a view model for the fragment, sets optionsmenu true and sets the theme of the fragment
     * from the view model.
     * @param inflater for the layout
     * @param container for the view
     * @param savedInstanceState for the fragment
     * @return the layout for the home fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       model = ViewModelProviders.of(getActivity()).get(saveViewModel.class);
        setHasOptionsMenu(true);
        getActivity().setTheme(model.getThemeId());
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
    }

    /**
     * Set the title for the toolbar and sets the display home button to true
     */
    @Override
    public void onResume() {
        super.onResume();
         ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_mainmenu);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    /**
     * Displays buttons in the view
     * Play game button to change fragment to start a new game
     * About button to display the about fragment
     * Theme button to change the current theme
     * @param savedInstanceState for the fragment
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
               getActivity().findViewById(R.id.playbutton).setOnClickListener(this::playGame);
        getActivity().findViewById(R.id.aboutButton).setOnClickListener(this::aboutInfo);
        getActivity().findViewById(R.id.themeButton).setOnClickListener(this::theme);
    }

    private void theme(View view){
        Context context = getContext().getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,R.string.themeChoosen,duration);
        toast.show();
        if (model.isTheme()){
            model.setTheme(false);
            model.setThemeId(R.style.Halooween);
            getActivity().setTheme(model.getThemeId());
        }else if (!model.isTheme()){
            model.setThemeId(R.style.AppTheme);
            getActivity().setTheme(model.getThemeId());
            model.setTheme(true);
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        home_fragment home_fragment = new home_fragment();
        fragmentTransaction.replace(R.id.framelayout, home_fragment);
        fragmentTransaction.commit();
    }

    private void playGame(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        game_fragment game_fragment = new game_fragment();
        fragmentTransaction.replace(R.id.framelayout, game_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        model.setActive(true);
    }

    private void aboutInfo(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        about_fragment about_fragment = new about_fragment();
        fragmentTransaction.replace(R.id.framelayout, about_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Clears the current toolbar and replaces it with a custom toolbar for the home fragment
     * @param menu the toolbar
     * @param inflater for the menu layout
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu,menu);
    }
}
