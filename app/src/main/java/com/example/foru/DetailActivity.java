package com.example.foru;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    public final String StartDate = "17/05/2022"; //17/05/2022
    public final int upperbound = 107; //total number of photos including 0
    public String CurrentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    public String DiffDate;
    public String LoveEmoji;
    public String FeelingLoveEmoji;
    public String HeheFaceEmoji;
    public String LoveEyesEmoji;
    public int randInt;
    public int imageResource;
    public String uri;
    public ArrayList<Long> hundreds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //get emoji
        LoveEmoji = getEmojiByUnicode(0x2764);
        FeelingLoveEmoji = getEmojiByUnicode(0x1F970);
        HeheFaceEmoji = getEmojiByUnicode(0x1F606);
        LoveEyesEmoji = getEmojiByUnicode(0x1F60D);

        //Get random image
        Random rand = new Random();
        randInt = rand.nextInt(upperbound);
        uri = "@drawable/tgt" + randInt;
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getDrawable(imageResource);

        //Set Texts
        TextView kk = findViewById(R.id.WkTxt);
        kk.setText("怪叔叔 / 慨慨" + LoveEmoji);
        TextView yl = findViewById(R.id.YlTxt);
        yl.setText("小小粒 / 铃铃" + LoveEmoji);
        TextView tgt = findViewById(R.id.TgtTxt);
        tgt.setText("我们在一起");

        //Set Image
        ImageView ImageTgt = findViewById(R.id.ImageTgt);
        ImageTgt.setImageDrawable(res);

        //On click change picture (seek)
        ImageTgt.setOnClickListener(v -> {
            //reset photo index if larger than total index of photo
            if (randInt >= 106){ //total index of photo
                randInt = 0;
            }else{
                randInt++;
            }

            uri = "@drawable/tgt" + randInt;
            imageResource = getResources().getIdentifier(uri, null, getPackageName());
            Drawable newRes = getDrawable(imageResource);
            ImageView imageView = findViewById(R.id.ImageTgt);
            imageView.setImageDrawable(newRes);
        });

        //On Click love logo
        ImageView ImageLove = findViewById(R.id.ImageLove);
        ImageLove.setOnClickListener(v -> Toast.makeText(this, "5月17号2022年 是我们在一起的第一天" + FeelingLoveEmoji, Toast.LENGTH_SHORT).show());
        //On click wk picture
        ImageView ImageWk = findViewById(R.id.ImageWk);
        ImageWk.setOnClickListener(v -> Toast.makeText(this, "10月6号2019年 是我们认识的第一天" + HeheFaceEmoji, Toast.LENGTH_SHORT).show());
        //On click yl picture
        ImageView ImageYl = findViewById(R.id.ImageYl);
        ImageYl.setOnClickListener(v -> Toast.makeText(this, "10月6号2019年 是我们认识的第一天" + HeheFaceEmoji, Toast.LENGTH_SHORT).show());

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
            TxtDays.setText(DiffDate + " days");

            TxtDays.setOnClickListener(v -> Toast.makeText(this, "我们在一起" + DiffDate + "天啦！！" + LoveEyesEmoji, Toast.LENGTH_SHORT).show());

            //push hundreds into arraylist for future 100 years
            for(long i=100; i<=36500; i=i+100){
                hundreds.add(i);
            }

            //conditional set Gif and toast each day 100
            if(hundreds.contains(differenceDates)){
                TxtDays.setText(LoveEmoji + DiffDate + " days" + LoveEmoji);

                Toast.makeText(this, "我们在一起" + DiffDate + "天啦！！" + LoveEyesEmoji, Toast.LENGTH_LONG).show();

                ImageView imageGif = findViewById(R.id.ImageGif);
                Glide.with(this).load(R.drawable.falling_love).into(imageGif);
            }
        }catch(Exception exception){
            TextView TxtDays = findViewById(R.id.TxtDays);
            TxtDays.setText("Call ur bf for help");
        }
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}