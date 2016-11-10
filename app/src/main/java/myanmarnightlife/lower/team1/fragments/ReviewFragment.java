package myanmarnightlife.lower.team1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


    public static ReviewFragment INSTANCE;

    @BindView(R.id.btn_review_submit)
    Button mReviewSubmit;

    @BindView(R.id.et_user_review)
    EditText mUserReview;

//    private DatabaseReference mDatabase;

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
        ButterKnife.bind(this,view);

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("User_Review");

        mReviewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                submitReview();
            }
        });


        return view;
    }

//    private void submitReview() {
//
//        String review = mUserReview.getText().toString();
//
//        if (!TextUtils.isEmpty(review)){
//
//
//
//        }
//
//    }

}
