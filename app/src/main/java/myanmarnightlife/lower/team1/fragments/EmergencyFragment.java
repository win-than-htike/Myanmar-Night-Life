package myanmarnightlife.lower.team1.fragments;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyFragment extends Fragment {

    @BindView(R.id.btn_police)
    Button btnPolice;

    @BindView(R.id.btn_fire)
    Button btnFire;

    @BindView(R.id.btn_blood)
    Button btnBlood;

    @BindView(R.id.btn_ambulance)
    Button btnAmbulance;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);
        ButterKnife.bind(this,rootView);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        btnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPhoneCall("199");
            }
        });

        btnFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPhoneCall("119");
            }
        });

        btnBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPhoneCall("122");
            }
        });


        btnAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPhoneCall("111");
            }
        });

        return rootView;
    }

    private void actionPhoneCall(String phno){

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phno));
        startActivity(intent);

    }

}
