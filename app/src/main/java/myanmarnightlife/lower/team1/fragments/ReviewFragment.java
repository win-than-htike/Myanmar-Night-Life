package myanmarnightlife.lower.team1.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.Places;
import myanmarnightlife.lower.team1.data.Review;
import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {

  public static ReviewFragment INSTANCE;

  @BindView(R.id.btn_review_submit) Button mReviewSubmit;

  @BindView(R.id.et_user_review) EditText mUserReview;

  private DatabaseReference mDatabase;

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

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_review, container, false);
    ButterKnife.bind(this, view);
    Bundle bundle = getArguments();
    final Places mPlaces = Parcels.unwrap(bundle.getParcelable("places"));

    mDatabase = FirebaseDatabase.getInstance().getReference("reviews");

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
