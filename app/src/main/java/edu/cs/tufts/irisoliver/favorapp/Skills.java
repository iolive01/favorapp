package edu.cs.tufts.irisoliver.favorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Skills extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private Button submit_skills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDatabase = FirebaseDatabase.getInstance();
        submit_skills = (Button) findViewById(R.id.submit_btn);

        FirebaseUser curr_user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference curr_user_ref = mDatabase.getReference("USERS").child(curr_user.getUid());
        curr_user_ref.child("artist").setValue(false);
        curr_user_ref.child("driver").setValue(false);


        submit_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseUser curr_user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference curr_user_ref = mDatabase.getReference("USERS").child(curr_user.getUid());

//                //TODO IS HERE
//                DataSnapshot data = ;
//                data.getRef(curr_user_ref);



                startActivity(new Intent(Skills.this, search_favor.class));
                finish();

            }
        });



    }


    public void onCheckboxClicked(View v) {
        FirebaseUser curr_user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference curr_user_ref = mDatabase.getReference("USERS").child(curr_user.getUid());


        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.checkbox_art:
                if (checked) {
                    curr_user_ref.child("artist").setValue(true);
//                    artist.child(curr_user.getUid()).setValue(true);
                } else {
                    curr_user_ref.child("artist").setValue(false);

//                    artist.setValue(false);
                }
               break;
            case R.id.checkbox_car:
                if (checked) {
                    curr_user_ref.child("driver").setValue(true);

                } else {
                    curr_user_ref.child("driver").setValue(false);
                }
                break;
        }
    }

}


