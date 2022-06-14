package com.example.foru;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.StringValue;
import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    public final String StartDate = "17/05/2022";
    public String CurrentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    public String DiffDate;
    public String LoveEmoji;
    public int upperbound = 15; //total number of photos + 1
    public int randInt;
    public int imageResource;
    public String uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        //get emoji
        LoveEmoji = getEmojiByUnicode(0x2764);

        //Get random image
        Random rand = new Random();
        randInt = rand.nextInt(upperbound);
        uri = "@drawable/tgt"+randInt;
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getDrawable(imageResource);

        //Set Texts
        TextView kk = findViewById(R.id.WkTxt);
        kk.setText("怪叔叔 / 慨慨" + LoveEmoji);

        //Set Texts
        TextView yl = findViewById(R.id.YlTxt);
        yl.setText("小小粒 / 铃铃" + LoveEmoji);

        //Set Texts
        TextView tgt = findViewById(R.id.TgtTxt);
        tgt.setText("我们在一起");

        //Set Image
        ImageView img = findViewById(R.id.ImageTgt);
        img.setImageDrawable(res);

        //On click change picture (seek)
        img.setOnClickListener(v -> {
            if (randInt >= 14){ //change here also eh bodoh
                randInt = 0;
            }else{
                randInt++;
            }

            uri = "@drawable/tgt"+randInt;
            imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable newRes = getDrawable(imageResource);
            ImageView img1 = findViewById(R.id.ImageTgt);
            img1.setImageDrawable(newRes);
            //Toast.makeText(this, uri, Toast.LENGTH_SHORT).show();
        });

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
            TxtDays.setText("Call ur bf for help");
        }
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}