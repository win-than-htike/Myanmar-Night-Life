package myanmarnightlife.lower.team1.activities;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
import myanmarnightlife.lower.team1.helper.PlacesRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

public class ShopActivity extends AppCompatActivity implements ItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_beer_shop)
    RecyclerView rvBeerShop;

    private List<Places> mPlaces;
    private PlacesRVAdapter mAdapter;

    private static String TYPE;

    private static final String IE_TYPE = "TYPE";

    public static Intent newInstance(String type){

        Intent intent = new Intent(MyanmarNightLifeApp.getContext(),ShopActivity.class);
        intent.putExtra(IE_TYPE,type);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_shop);
        ButterKnife.bind(this,this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        }

        if (getIntent().getExtras() != null){

            TYPE = getIntent().getStringExtra(IE_TYPE);

        }

        toolbar.setTitle("Beer Shop");

        rvBeerShop.setHasFixedSize(true);

        int gridColumn = getResources().getInteger(R.integer.list_grid);
        rvBeerShop.setLayoutManager(new GridLayoutManager(MyanmarNightLifeApp.getContext(),gridColumn));

        PlacesRealmHelper helper = PlacesRealmHelper.getInstance();


        mPlaces = helper.getBarList(TYPE);

        if (mPlaces.size() <= 0){
            PlacesData.getData();
            mPlaces = helper.getBarList(TYPE);
        }

        mAdapter = new PlacesRVAdapter(mPlaces, this);
        rvBeerShop.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View view, int position) {

        Places places = mAdapter.getSelectedPlace(position);
        Intent intent = DetailActivity.newInstance(places);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);

    }

}
