package myanmarnightlife.lower.team1.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
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

    private TextView mEmail;

    private static final int RC_SIGN_IN = 1;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){

            FragmentMain fragmentMain = new FragmentMain();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,fragmentMain,"home").commit();

        }else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), RC_SIGN_IN);
        }

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
        View header = navigationView.getHeaderView(0);
        mEmail = (TextView)header.findViewById(R.id.tv_email);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){

            if (resultCode == RESULT_OK){

                mEmail.setText(mAuth.getCurrentUser().getDisplayName());

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
            startActivity(new Intent(this,TaxiServiceActivity.class));
        }

        if (id == R.id.action_logout){
            AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                }
            });
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
                    toolbar.setTitle("Myanmar Night Life");

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
        Intent intent = DetailActivity.newInstance(places,places.getShopType());
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("state",toolbar.getTitle().toString());
    }

}
