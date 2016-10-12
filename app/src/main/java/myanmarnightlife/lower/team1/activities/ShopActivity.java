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
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.PlacesData;
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

    private static String TYPE;

    private static final String IE_TYPE = "TYPE";

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

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);
        }


        ShopFragment shopFragment = new ShopFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_shop, shopFragment).commit();

        if (getIntent().getExtras() != null) {

            TYPE = getIntent().getStringExtra(IE_TYPE);

        }

        if (TYPE.equals("Beer")) {

            toolbar.setTitle("Beer Shop");

        } else if (TYPE.equals("Bar")) {

            toolbar.setTitle("Bar");

        } else if (TYPE.equals("Karaoke")) {

            toolbar.setTitle("Karaoke");

        }else if (TYPE.equals("Club")){

            toolbar.setTitle("Night Club");

        }else if (TYPE.equals("Restaurant")){

            toolbar.setTitle("Restaurant");

        }else if (TYPE.equals("Massage")){

            toolbar.setTitle("Massage");

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

}
