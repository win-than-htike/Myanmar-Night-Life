package myanmarnightlife.lower.team1.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
import myanmarnightlife.lower.team1.fragments.SearchFragment;
import myanmarnightlife.lower.team1.fragments.ShopFragment;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
        }

        if(savedInstanceState != null){

        }

        ShopFragment shopFragment = new ShopFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_shop, shopFragment).commit();

        fragmentManager = getSupportFragmentManager();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        }



        if (getIntent().getExtras() != null) {

            TYPE = getIntent().getStringExtra(IE_TYPE);

        }

        if (TYPE.equals("Beer")) {

            toolbar.setTitle("Beer Shop");
            state = "beer";

        } else if (TYPE.equals("Bar")) {

            toolbar.setTitle("Bar");
            state = "bar";

        } else if (TYPE.equals("Karaoke")) {

            toolbar.setTitle("Karaoke");
            state = "karaoke";

        }else if (TYPE.equals("Club")){

            toolbar.setTitle("Night Club");
            state = "club";

        }else if (TYPE.equals("Restaurant")){

            toolbar.setTitle("Restaurant");
            state = "restaurant";

        }else if (TYPE.equals("Massage")){

            toolbar.setTitle("Massage");
            state = "massage";

        }

    }


    @Override
    public void onTapShop(Places places, ImageView imageView) {
        Intent intent = DetailPagerActivity.newInstance(places,TYPE);
        startActivity(intent);
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
        searchView.setQueryHint("Search...");

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
                    .add(R.id.fl_shop, SearchFragment.newInstance(s))
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
