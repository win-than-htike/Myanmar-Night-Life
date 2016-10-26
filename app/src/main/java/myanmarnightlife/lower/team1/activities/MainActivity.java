package myanmarnightlife.lower.team1.activities;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.fragments.EmergencyFragment;
import myanmarnightlife.lower.team1.fragments.FavouriteFragment;
import myanmarnightlife.lower.team1.fragments.FragmentMain;
import myanmarnightlife.lower.team1.fragments.SuggestFragment;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    static String sate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        toolbar.setTitle("Myanmar Night Life");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null){

            sate = savedInstanceState.getString("state");

            if(sate.equals("Myanmar Night Life")){

                FragmentMain fragmentMain = new FragmentMain();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragmentMain,"home").commit();

            }else if (sate.equals("Favourite")){

                FavouriteFragment favouriteFragment = new FavouriteFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,favouriteFragment).commit();

            }
        }

        if (savedInstanceState == null) {
            FragmentMain homeFragment = new FragmentMain();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, homeFragment,"home").commit();
            toolbar.setTitle("Myanmar Night Life");
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {

            if (toolbar.getTitle().toString().equals("Myanmar Night Life")){
                finish();
            }else {
                FragmentMain fragmentMain = new FragmentMain();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragmentMain).commit();
                toolbar.setTitle("Myanmar Night Life");
            }

        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_taxi) {
            Toast.makeText(MyanmarNightLifeApp.getContext(),"Taxi",Toast.LENGTH_SHORT);
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (id == R.id.nav_home) {

                    FragmentMain homeFragment = new FragmentMain();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fl_container, homeFragment,"home")
                            .commit();
                    toolbar.setTitle("Myanmar Night Out");

                } else if (id == R.id.nav_favourite) {

                    FavouriteFragment favouriteFragment = new FavouriteFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, favouriteFragment).addToBackStack("Myanmar Night Life").commit();
                    toolbar.setTitle("Favourite");


                } else if (id == R.id.nav_about_us) {

                    startActivity(new Intent(MyanmarNightLifeApp.getContext(),AboutUsActivity.class));

                } else if (id == R.id.nav_suggest) {

                    SuggestFragment suggestFragment = new SuggestFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,suggestFragment).commit();
                    toolbar.setTitle("Suggestion");

                }else if (id == R.id.nav_emergency){

                    EmergencyFragment emergencyFragment = new EmergencyFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,emergencyFragment).commit();
                    toolbar.setTitle("Emergency");

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        },300);
        return true;
    }


    @Override
    public void onTapShop(Places places, ImageView imageView) {
        Intent intent = DetailPagerActivity.newInstance(places,places.getShopType());
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("state",toolbar.getTitle().toString());
    }



}
