package com.example.foru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.protobuf.StringValue;
import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    public final String StartDate = "17/05/2022";
    public String CurrentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    public String DiffDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        //Date calculations
        try{
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");
            date1 = dates.parse(StartDate);
            date2 = dates.parse(CurrentDate);
            long difference = Math.abs(date1.getTime() - date2.getTime() - 86400000); //86400000 = add current date
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            DiffDate = Long.toString(differenceDates);
            TextView TxtDays = findViewById(R.id.TxtDays);
            TxtDays.setText(DiffDate + "days");
        }catch(Exception exception){
            TextView TxtDays = findViewById(R.id.TxtDays);
            TxtDays.setText("Please contact ur bf for help");
        }
    }
}