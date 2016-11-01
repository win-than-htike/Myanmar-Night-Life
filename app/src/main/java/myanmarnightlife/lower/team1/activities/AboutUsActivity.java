package myanmarnightlife.lower.team1.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;

public class AboutUsActivity extends AppCompatActivity {

    @BindView(R.id.btn_winthanhtike)
    Button btnWinthan;

    @BindView(R.id.btn_aungkhantwai)
    Button btnAungKhantWai;

    @BindView(R.id.btn_aungmyothu)
    Button btnAungMyoThu;

    @BindView(R.id.btn_facebook)
    Button btnFacebook;

    @BindView(R.id.btn_googleplus)
    Button btnGooglePlus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this,this);

        btnWinthan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail("winthanhtike@ucsy.edu.mm","Dear Win Than Htike,");
            }
        });

        btnAungKhantWai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail("johnaungkhant@gmail.com","Dear Aung Khant Wai,");
            }
        });

        btnAungMyoThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail("tunlin009707@gmail.com","Dear Aung Myo Thu,");
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntent(MyanmarNightLifeApp.getContext()));
            }
        });

        btnGooglePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenGooglePlusIntent(MyanmarNightLifeApp.getContext()));
            }
        });

    }

    public static Intent getOpenGooglePlusIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.google.android.apps.plus", 0);
            Intent googlePlus = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/2/118109565446882060041"));
            googlePlus.putExtra("customAppUri","118109565446882060041");
            return googlePlus;
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/2/118109565446882060041"));
        }
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1839413723005995"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/myanmarnightlifewaa/"));
        }
    }

    public void sendEmail(String email,String header){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
        shareIntent.putExtra(Intent.EXTRA_TEXT, header);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Send To..."));
    }

}
