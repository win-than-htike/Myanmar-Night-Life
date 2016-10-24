package myanmarnightlife.lower.team1.fragments;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyFragment extends Fragment {


    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_emergency, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        return rootView;
    }

}
