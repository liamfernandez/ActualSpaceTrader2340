package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.model.Model;

public class MiniGameActivity extends AppCompatActivity {

    private ProgressBar pb;
    private TextView congrats;
    private int status = 0;
    private int clicks = 0;
    private Handler hand = new Handler();
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mini_game);

        Button money = findViewById(R.id.button);
        Button back = findViewById(R.id.back);

        congrats = (TextView) findViewById(R.id.congrats);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        int random = new Random().nextInt(10);

        if (random < 5) {
            player = MediaPlayer.create(this, R.raw.mi);
        } else {
            player = MediaPlayer.create(this, R.raw.golden);
        }
        player.start();


        new Thread (new Runnable() {
            public void run() {
                while (status < 100) {
                    status++;
                    android.os.SystemClock.sleep(90);
                    hand.post(new Runnable() {
                        public void run() {
                            pb.setProgress(status);
                        }
                    });
                }
                hand.post(new Runnable() {
                    public void run() {
                        congrats.setText("Congrats! you earned $" + clicks +".00!");
                        congrats.setVisibility(View.VISIBLE);
                        money.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        Model.getInstance().getMarketInteractor().getRepository().getPlayer().editCredit(clicks);
                    }
                });
            }
        }).start();

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
            }
        });

         back.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                Intent intent = new Intent(MiniGameActivity.this, MarketMainActivity.class);
                player.stop();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
