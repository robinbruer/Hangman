package iths.robin.hangman;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class about_fragment extends Fragment {
    private saveViewModel model;

    /**
     * Empty constructor
     */
    public about_fragment() {
        // Required empty public constructor
    }

    /**
     * Sets a view model for the fragment, sets optionsmenu true and sets the theme of the fragment
     * from the view model.
     * @param inflater for the layout
     * @param container for the view
     * @param savedInstanceState for the fragment
     * @return the layout for the about fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        model = ViewModelProviders.of(getActivity()).get(saveViewModel.class);
        setHasOptionsMenu(true);
        getActivity().setTheme(model.getThemeId());
        return inflater.inflate(R.layout.fragment_about_fragment, container, false);
    }

    /**
     * Set the title for the toolbar and sets the display home button to true
     * @param savedInstanceState for the fragment
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_about);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Clears the current toolbar and replaces it with a custom toolbar for the about fragment
     * @param menu the toolbar
     * @param inflater for the menu layout
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_all,menu);
    }
}
