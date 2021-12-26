package com.gamzeuysal.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText=findViewById(R.id.timeText);
        scoreText=findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();
        score=0;
        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {//Her 10 saniyeden 1  sn indiginde
                timeText.setText("Time :"+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                //Oyun süresi bittiğinde oyuncuya tekrar restart edip etmeyecegini soralım.
                timeText.setText("Time Off!");
                //random image basmasını durduralım.
                handler.removeCallbacks(runnable);
                //kenny sakla
                for(ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restar?");
                alert.setMessage("Are you sure to restart game? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     //restart
                        Intent intent=getIntent();//yollanan intent olmadıgı için getIntent() yaptık.
                        finish();//guncel aktiviye bitir
                        startActivity(intent);//Yeni aktiviyi yeniden baslat
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     Toast.makeText(MainActivity.this,"Game Over!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void increaseScore(View View)
    {

     score++;
     scoreText.setText("Score:"+score);
    }
    public void hideImages()
    {
        //belli zaman aralıgında belli işleri yapmak runnable ile olur.
        handler=new Handler(Looper.getMainLooper());
        runnable=new Runnable() {
            @Override
            public void run() {

                for(ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);//hespsini en basta görünmez yap
                }
                //Rasgele bir deger bulup sonra bu rasgele degeri acacagım.
                Random random=new Random();
                int i=random.nextInt(9);//benim dizimde 0-8 indili dokuz tane elemanın var 0-8 dahil random sayılar uretir.
                //beli periyotla kenyy görünür yapıp acıp yakalatmaya calısacagım
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);//her yarım saniyede bir yapalım.
            }
        };//Runnable
handler.post(runnable);
    }

}