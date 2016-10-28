package myanmarnightlife.lower.team1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class TaxiServiceCardFragment extends Fragment{


    @BindView(R.id.card_ts)
    CardView mCardView;

    @BindView(R.id.tv_taxiapp_desc)
    TextView tvDesc;

    @BindView(R.id.iv_taxiapp_logo)
    CircleImageView ivTaxiLogo;

    @BindView(R.id.iv_taxiapp_bg)
    KenBurnsView ivTaxiBg;

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
        ButterKnife.bind(this,view);

        position = getArguments().getInt("position");

        if (position == 0){
            ivTaxiBg.setImageResource(R.drawable.owaybg);
            ivTaxiLogo.setImageResource(R.drawable.oway);
            tvDesc.setText(getString(R.string.owaydesc));
        }else if (position == 1){
            ivTaxiBg.setImageResource(R.drawable.hellocabsbg);
            ivTaxiLogo.setImageResource(R.drawable.hellocaps);
            tvDesc.setText(getString(R.string.hellocabsdesc));
        }else if (position == 2){
            ivTaxiBg.setImageResource(R.drawable.indriverbg);
            ivTaxiLogo.setImageResource(R.drawable.indriver);
            tvDesc.setText(getString(R.string.indriverdesc));
        }else {
            tvDesc.setText("");
        }

        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * TaxiCardInterface.MAX_ELEVATION_FACTOR);
        return view;
    }

    public CardView getCardView() {
        return mCardView;
    }

}
