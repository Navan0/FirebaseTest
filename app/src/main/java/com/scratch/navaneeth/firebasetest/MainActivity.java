package com.scratch.navaneeth.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cooltechworks.views.ScratchTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mPrizeRef;
    private DatabaseReference mFirstPrizeRef;
    private DatabaseReference mSecondPrizeRef;

    //UI references

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = findViewById(R.id.textView);
        mDatabase = FirebaseDatabase.getInstance();


        mPrizeRef = mDatabase.getReference("prize");

        //Write single value
        mFirstPrizeRef = mDatabase.getReference("prize/first_prize");
        //Read the single value
        mFirstPrizeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mText.setText(""+dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mPrizeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prizes prizes= dataSnapshot.getValue(Prizes.class);
                Log.d("MainActivity",prizes.first_prize.toString());
                Log.d("MainActivity",prizes.second_prize.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ScratchTextView scratchTextView = new ScratchTextView(this);

        scratchTextView.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView tv) {
                //on reveal
            }


            @Override
            public void onRevealPercentChangedListener(ScratchTextView stv, float percent) {
                // on text percent reveal
            }
        });



    }

}