package com.travel.smartcitytraveller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Feedback extends AppCompatActivity {

    //Firebase ref;
    private EditText feed;
    AwesomeSpinner my_spinner;
    //AtomicInteger seq =new AtomicInteger();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

       // Toolbar toolbar = findViewById(R.id.tool);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //hooks
        feed=findViewById(R.id.feedback);
        //spinner
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

    /*    if (signInAccount != null) {
            // name.setText("hello");
            .setText(signInAccount.getDisplayName());*/

        my_spinner=findViewById(R.id.user_spinner);

        List<String> point = new ArrayList<String>();
        point.add("From:   "+"Username");
        point.add("From:   "+signInAccount.getDisplayName());

        ArrayAdapter<String> pointAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, point);

        my_spinner.setAdapter(pointAdapter);
        my_spinner.setSelection(1);
        my_spinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {

            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.send) {
            if(feed != null) {

              //  int nextVal = seq.incrementAndGet();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mRef = database.getReference("Feedback").push();

                feedhelper newFeed = new feedhelper(my_spinner.getSelectedItem().replace("From:   ",""), feed.getText().toString());


                //mRef.child("Name").setValue(my_spinner.getSelectedItem());
                //mRef.child("feed").setValue(feed.getText().toString());
                mRef.setValue(newFeed);

            }
        }

        return true;
    }
}