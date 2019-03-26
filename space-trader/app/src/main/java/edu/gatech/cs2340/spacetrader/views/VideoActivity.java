package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Model;

public class VideoActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button button;
    Button goBack;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_main);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);

        button = (Button) findViewById(R.id.button);
        goBack = (Button) findViewById(R.id.next);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, SolarSystemActivity.class);
                startActivity(intent);
            }
        });


        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                String youTubeVideo;
                String currSolarSystem = Model.getInstance().getMarketInteractor().getRepository().getPlayer().getCurrSolarSystem().getName();
                if (currSolarSystem == "Meganville") {
                    youTubeVideo = "3XQO7MwmgvM";
                } else if (currSolarSystem == "The Street") {
                    youTubeVideo = "cUusX1Js6R0";
                } else if (currSolarSystem == "iCarly") {
                    youTubeVideo = "854jxWy5L5Y";
                } else if (currSolarSystem == "Buddy land") {
                    youTubeVideo = "nhNC1TvHiNc";
                } else if (currSolarSystem == "Break town") {
                    youTubeVideo = "mYj0m5RnWiY";
                } else if (currSolarSystem == "Niceopolis") {
                    youTubeVideo = "EuPghU-T-kE";
                } else if (currSolarSystem == "Spiketopia") {
                    youTubeVideo = "S7m6gOWkqko";
                } else if (currSolarSystem == "Oddparentia") {
                    youTubeVideo = "no8vgXg_rlw";
                } else if (currSolarSystem == "Waynes World") {
                    youTubeVideo = "thyJOnasHVE";
                } else if (currSolarSystem == "Pineappleappalis") {
                    youTubeVideo = "BWZt4v6b1hI";
                } else {
                    youTubeVideo = "xPZigWFyK2o";
                }

                youTubePlayer.loadVideo(youTubeVideo);

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
            }
        });
    }
}
