package myanmarnightlife.lower.team1.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.R;

public class AddEventActivity extends AppCompatActivity {

    @BindView(R.id.et_event_name)
    EditText etEventName;

    @BindView(R.id.et_event_image_url)
    EditText etImageURL;

    @BindView(R.id.et_event_time)
    EditText etEventTime;

    @BindView(R.id.et_event_location)
    EditText etEventLocation;

    @BindView(R.id.et_event_type)
    EditText etEventType;

    @BindView(R.id.et_event_gps)
    EditText etEventGPS;

    @BindView(R.id.et_event_info)
    EditText etEventInfo;

    @BindView(R.id.btn_submit_event)
    Button btnSubmitEvent;

    private DatabaseReference mDatabase;

    private ProgressDialog pDialog;

    int i = -1;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this, this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

        pDialog = new ProgressDialog(this);



        btnSubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvent();
                etEventName.setText("");
                etImageURL.setText("");
                etEventTime.setText("");
                etEventLocation.setText("");
                etEventType.setText("");
                etEventInfo.setText("");
                etEventGPS.setText("");
            }
        });

    }

    private void addEvent() {

        pDialog.setMessage("Adding Event...");
        pDialog.show();

        final String eventName = etEventName.getText().toString();
        final String eventImageUrl = etImageURL.getText().toString();
        final String eventTime = etEventTime.getText().toString();
        final String eventLocation = etEventLocation.getText().toString();
        final String eventType = etEventType.getText().toString();
        final String eventInfo = etEventInfo.getText().toString();
        final String eventGPS = etEventGPS.getText().toString();

        if (!TextUtils.isEmpty(eventName) && !TextUtils.isEmpty(eventImageUrl) && !TextUtils.isEmpty(eventTime) && !TextUtils.isEmpty(eventLocation) && !TextUtils.isEmpty(eventType) && !TextUtils.isEmpty(eventInfo) && !TextUtils.isEmpty(eventGPS)) {

            j = i+1;

            DatabaseReference newEvent = mDatabase.child(j +"");
            newEvent.child("name").setValue(eventName);
            newEvent.child("image_url").setValue(eventImageUrl);
            newEvent.child("time").setValue(eventTime);
            newEvent.child("location").setValue(eventLocation);
            newEvent.child("gps").setValue(eventGPS);
            newEvent.child("type").setValue(eventType);
            newEvent.child("information").setValue(eventInfo);

            pDialog.dismiss();

            startActivity(new Intent(AddEventActivity.this,EventActivity.class));

        } else {

            pDialog.dismiss();

            Toast.makeText(getApplicationContext(),"Data ma pyae sone par :( Noob ):", Toast.LENGTH_SHORT).show();

        }

    }
}
