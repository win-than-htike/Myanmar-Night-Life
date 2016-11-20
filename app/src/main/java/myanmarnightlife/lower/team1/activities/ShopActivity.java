package myanmarnightlife.lower.team1.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.fragments.SearchFragment;
import myanmarnightlife.lower.team1.fragments.ShopFragment;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

public class ShopActivity extends AppCompatActivity implements ItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
//
//    @BindView(R.id.rv_beer_shop)
//    RecyclerView rvBeerShop;

    private List<Places> mPlaces;
    private PlacesRVAdapter mAdapter;
    String state;

    @BindView(R.id.sp_township)
    AppCompatSpinner spTownship;

    private static String TYPE;

    private static final String IE_TYPE = "TYPE";

    private boolean isSearchResultFragmentLoaded = false;
    public static FragmentManager fragmentManager;
    private SearchView searchView;
    private MenuItem searchViewMenuItem;

    public static Intent newInstance(String type) {

        Intent intent = new Intent(MyanmarNightLifeApp.getContext(), ShopActivity.class);
        intent.putExtra(IE_TYPE, type);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_shop);
        ButterKnife.bind(this, this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();

        String[] township = getResources().getStringArray(R.array.township);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(MyanmarNightLifeApp.getContext(),android.R.layout.simple_spinner_dropdown_item,township);
        spTownship.setAdapter(spAdapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
        }

        if(savedInstanceState != null){

        }

        spTownship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShopFragment shopFragment = ShopFragment.newInstance(spTownship.getSelectedItem().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_shop, shopFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        fragmentManager = getSupportFragmentManager();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }



        if (getIntent().getExtras() != null) {

            TYPE = getIntent().getStringExtra(IE_TYPE);

        }

        if (TYPE.equals("beer")) {

            toolbar.setTitle("Beer Shop");

        } else if (TYPE.equals("bar")) {

            toolbar.setTitle("Bar");

        } else if (TYPE.equals("ktv")) {

            toolbar.setTitle("Karaoke");

        }else if (TYPE.equals("club")){

            toolbar.setTitle("Night Club");

        }else if (TYPE.equals("Restaurant")){

            toolbar.setTitle("Restaurant");

        }else if (TYPE.equals("massage")){

            toolbar.setTitle("Massage");

        }

    }


    @Override
    public void onTapShop(View v, Places places, ImageView imageView) {
        Intent intent = DetailActivity.newInstance(places,TYPE);
//        startActivity(intent);
        ActivityTransitionLauncher
                .with(ShopActivity.this)
                .from(v)
                .launch(intent);
    }

    @Override
    public void onTapHotel(Hotel hotel, ImageView imageView) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        searchViewMenuItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) searchViewMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        MenuItemCompat.setOnActionExpandListener(searchViewMenuItem, new SearchViewExpandListener());
        searchView.setOnQueryTextListener(new SearchQueryListener());
        searchView.setQueryHint("Search by Name or City...");

        return true;
    }

    public void setAllMenuItemVisiblity(Menu menu, MenuItem exception, boolean visibility) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item != exception) {
                item.setVisible(visibility);
            }
        }
    }

    private class SearchViewExpandListener implements MenuItemCompat.OnActionExpandListener {

        @Override public boolean onMenuItemActionExpand(MenuItem menuItem) {
            setAllMenuItemVisiblity(toolbar.getMenu(), menuItem, false);
            return true;
        }

        @Override public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            setAllMenuItemVisiblity(toolbar.getMenu(), menuItem, true);
            if (isSearchResultFragmentLoaded) {
                fragmentManager.popBackStackImmediate();
                isSearchResultFragmentLoaded = false;
            }
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
     outState.putString("S",state);
    }

    private class SearchQueryListener implements SearchView.OnQueryTextListener {

        @Override public boolean onQueryTextSubmit(String s) {

            if (isSearchResultFragmentLoaded) {
                fragmentManager.popBackStackImmediate();
            }

            fragmentManager.beginTransaction()
                    .add(R.id.fl_shop, SearchFragment.newInstance(s,spTownship.getSelectedItem().toString()))
                    .addToBackStack("result")
                    .commit();
            isSearchResultFragmentLoaded = true;
            searchView.clearFocus();
            return true;
        }

        @Override public boolean onQueryTextChange(String s) {
            return false;
        }
    }

}
