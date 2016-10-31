package myanmarnightlife.lower.team1.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.adapters.TaxiCardInterface;

/**
 * Created by winthanhtike on 10/27/16.
 */
public class TaxiServiceCardFragment extends Fragment {


    @BindView(R.id.card_ts)
    CardView mCardView;

    @BindView(R.id.tv_taxiapp_desc)
    TextView tvDesc;

    @BindView(R.id.iv_taxiapp_logo)
    CircleImageView ivTaxiLogo;

    @BindView(R.id.iv_taxiapp_bg)
    KenBurnsView ivTaxiBg;

    @BindView(R.id.btn_go)
    Button btnGo;

    @BindView(R.id.btn_website)
    Button btnWebiste;

    @BindView(R.id.btn_app)
    Button btnApp;

    int position;

    public static Fragment getInstance(int position) {

        TaxiServiceCardFragment f = new TaxiServiceCardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.taxi_card, container, false);
        ButterKnife.bind(this, view);

        position = getArguments().getInt("position");

        if (position == 0) {

            ivTaxiBg.setImageResource(R.drawable.owaybg);
            ivTaxiLogo.setImageResource(R.drawable.oway);
            tvDesc.setText(getString(R.string.owaydesc));

            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionPhoneCall("09253808581");
                }
            });

            btnWebiste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.owayride.com.mm/")));
                }
            });

            btnApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String packageName = "com.owayride";
                    Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);

                    if (intent == null){
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName));
                    }

                    startActivity(intent);

                }
            });

        } else if (position == 1) {

            ivTaxiBg.setImageResource(R.drawable.hellocabsbg);
            ivTaxiLogo.setImageResource(R.drawable.hellocaps);
            tvDesc.setText(getString(R.string.hellocabsdesc));

            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionPhoneCall("019339111");
                }
            });

            btnWebiste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.hellocabsmyanmar.com/")));
                }
            });

            btnApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String packageName = "com.cws.hellocabs";
                    Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);

                    if (intent == null){
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName));
                    }

                    startActivity(intent);

                }
            });

        } else if (position == 2) {

            ivTaxiBg.setImageResource(R.drawable.indriverbg);
            ivTaxiLogo.setImageResource(R.drawable.indriver);
            tvDesc.setText(getString(R.string.indriverdesc));

            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionPhoneCall("");
                }
            });

            btnWebiste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://indriver.com/index_my.html")));
                }
            });

            btnApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String packageName = "sinet.startup.inDriver";
                    Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageName);

                    if (intent == null){
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName));
                    }

                    startActivity(intent);

                }
            });

        } else {

            tvDesc.setText("");

        }

        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * TaxiCardInterface.MAX_ELEVATION_FACTOR);
        return view;
    }

    private void actionPhoneCall(String phno) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phno));
        startActivity(intent);

    }

    public CardView getCardView() {
        return mCardView;
    }

}
