package myanmarnightlife.lower.team1.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.HotelRVAdapter;
import myanmarnightlife.lower.team1.adapters.PlacesRVAdapter;
import myanmarnightlife.lower.team1.data.Hotel;
import myanmarnightlife.lower.team1.data.HotelData;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.fragments.HotelFragment;
import myanmarnightlife.lower.team1.helper.HotelRealmHelper;
import myanmarnightlife.lower.team1.interfaces.ItemClickListener;

public class HotelActivity extends AppCompatActivity implements ItemClickListener{

    @BindView(R.id.sp_hotel_type)
    AppCompatSpinner spHotel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        ButterKnife.bind(this,this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            toolbar.setTitle("Hotels");
        }

        String[] type = getResources().getStringArray(R.array.hotel_type);

        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(MyanmarNightLifeApp.getContext(),android.R.layout.simple_spinner_dropdown_item,type);
        spHotel.setAdapter(spAdapter);

        spHotel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HotelFragment hotelFragment = HotelFragment.newInstance(spHotel.getSelectedItem().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_hotel,hotelFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void onTapShop(View v, Places places, ImageView imageView) {

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
}
