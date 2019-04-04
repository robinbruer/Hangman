package iths.robin.hangman;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    /**
     * Sets the layout for the activity
     * Sets a toolbar for the activity with a logo and a title
     * @param savedInstanceState if null, adds home fragment to the framelayout
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.ic_android);
        getSupportActionBar().setTitle(R.string.toolbar_mainmenu);
        toolbar.setTitleMarginStart(40);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        if( savedInstanceState == null) {
            home_fragment home_fragment = new home_fragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.framelayout, home_fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * Sets the toolbar layout for the activity
     * @param menu the toolbar
     * @return true to set the toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_all,menu);
        return true;
    }

    /**
     * Sets the items in the toolbar
     * If the playitem is pressed, switches fragment to the game fragment
     * If the infoitem is pressed, switches fragment to the about fragment
     * @param item of the toolbar
     * @return the fragment or the default item
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.play_ic:
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.framelayout, new game_fragment()).addToBackStack(null).commit();
                return true;
            case R.id.info_ic:
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.framelayout, new about_fragment()).addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Replaces fragment if the navigation button is pressed with the home fragment for the acitivty
     * @return the button
     */
    @Override
    public boolean onSupportNavigateUp() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.framelayout, new home_fragment()).commit();
        return true;
    }
}
