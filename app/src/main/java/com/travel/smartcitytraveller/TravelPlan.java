package com.travel.smartcitytraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.collection.LLRBNode;
import com.isapanah.awesomespinner.AwesomeSpinner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TravelPlan extends AppCompatActivity {
    //Int var
    TextView timer1;
    int hour,min;
    FloatingActionButton nxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_plan);

        //hooks
        timer1 = findViewById(R.id.timer);


        timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // init time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(

                        TravelPlan.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //init hh:mm
                                hour = hourOfDay;
                                min = minute;
                                //store hour n min in str
                                String time = hour + ":" + min;
                                // init 24h time format
                                SimpleDateFormat f24h = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24h.parse(time);
                                    //init 12h time format
                                    SimpleDateFormat f12h = new SimpleDateFormat(
                                            "HH:mm"
                                    );
                                    //set selected time on text view
                                    timer1.setText(f12h.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //set Transparent bg
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //displayed time
                timePickerDialog.updateTime(hour, min);

                //show dialog
                timePickerDialog.show();


            }
        });


        AwesomeSpinner my_spinner = (AwesomeSpinner) findViewById(R.id.my_spinner);
        List<String> point = new ArrayList<String>();
        point.add("Default");
        point.add("Current Location");

        ArrayAdapter<String> pointAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, point);

        my_spinner.setAdapter(pointAdapter);
        my_spinner.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                Toast.makeText(TravelPlan.this,itemAtPosition,Toast.LENGTH_SHORT).show();
            }
        });
        //hooks
        nxt=findViewById(R.id.nxt);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TravelPlan.this,CitySlt.class);
                startActivity(intent);
            }
        });
    }
}