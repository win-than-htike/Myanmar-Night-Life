package myanmarnightlife.lower.team1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.Review;
import myanmarnightlife.lower.team1.views.ReviewViewHolder;

import org.parceler.Parcels;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {


  public static ReviewFragment INSTANCE;

  @BindView(R.id.btn_review_submit) Button mReviewSubmit;

  @BindView(R.id.et_user_review) EditText mUserReview;

  @BindView(R.id.rv_review)
  RecyclerView rvReview;

  private DatabaseReference mDatabase;

  List<Review> reviews;

  public ReviewFragment() {
    // Required empty public constructor
  }

  public static ReviewFragment newInstance(Places places) {

    INSTANCE = new ReviewFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("places", Parcels.wrap(places));
    INSTANCE.setArguments(bundle);
    return INSTANCE;
  }

  /**
   * Use this to Listen database
   *
   * @return mDatabase.child(mPlaces.get_id() + "")
   * ValueEventListener mReviewListener = new ValueEventListener() {
   * @Override public void onDataChange(DataSnapshot dataSnapshot) {
   * // Get Post object and use the values to update the UI
   * List<Review> reviews = dataSnapshot.getValue(Review.class);
   * // ...
   * }
   * @Override public void onCancelled(DatabaseError databaseError) {
   * // Getting Post failed, log a message
   * Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
   * // ...
   * }
   * };
   * mReviewListener.addValueEventListener(postListener);
   */

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    final View view = inflater.inflate(R.layout.fragment_review, container, false);
    ButterKnife.bind(this, view);
    Bundle bundle = getArguments();
    final Places mPlaces = Parcels.unwrap(bundle.getParcelable("places"));

    mDatabase = FirebaseDatabase.getInstance().getReference("reviews");

    rvReview.setHasFixedSize(true);
    rvReview.setLayoutManager(new LinearLayoutManager(MyanmarNightLifeApp.getContext()));

    mReviewSubmit.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
          Review review = new Review(mUserReview.getText().toString(),
              FirebaseAuth.getInstance().getCurrentUser(), Integer.toString(mPlaces.get_id()));
          String key = mDatabase.push().getKey();
          mDatabase.child(mPlaces.get_id() + "").child(key)

              .setValue(review, new DatabaseReference.CompletionListener() {
                @Override public void onComplete(DatabaseError databaseError,
                    DatabaseReference databaseReference) {
                  /**
                   * Here do logic in complete
                   */


                }
              });
        } else {
          /**
           *
           *
           * User haven't sign in yet so review shouldn't be available
           *
           *
           */
        }
      }
    });

    FirebaseRecyclerAdapter<Review, ReviewViewHolder> mReviewAdapter = new FirebaseRecyclerAdapter<Review, ReviewViewHolder>(
            Review.class,
            R.layout.review_card,
            ReviewViewHolder.class,
            mDatabase
    ) {
      @Override
      protected void populateViewHolder(ReviewViewHolder viewHolder, Review model, int position) {

        viewHolder.setReview(model.getReview());

      }
    };

    rvReview.setAdapter(mReviewAdapter);

    return view;
  }

  @Override
  public void onStart() {
    super.onStart();



  }
}
