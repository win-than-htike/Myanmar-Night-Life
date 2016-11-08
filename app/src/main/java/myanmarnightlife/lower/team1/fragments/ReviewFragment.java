package myanmarnightlife.lower.team1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public static ReviewFragment INSTANCE;


    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(){

        INSTANCE = new ReviewFragment();
        return INSTANCE;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);



        return view;
    }

}
