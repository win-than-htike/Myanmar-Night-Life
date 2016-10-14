package myanmarnightlife.lower.team1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestFragment extends Fragment {

    String[] type = {"Beer Shop","Karaoke","Night Club","Bar","Massage","Restaurant"};

    public SuggestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggest, container, false);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MyanmarNightLifeApp.getContext(), android.R.layout.simple_spinner_item, type);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner) view.findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);

        return view;
    }

}
