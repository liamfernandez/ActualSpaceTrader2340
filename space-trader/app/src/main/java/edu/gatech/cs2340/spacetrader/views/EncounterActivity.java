package edu.gatech.cs2340.spacetrader.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.model.MarketInteractor;
import edu.gatech.cs2340.spacetrader.model.Model;

/**
 * encounter class
 */
@SuppressWarnings("SpellCheckingInspection")
public class EncounterActivity extends YouTubeBaseActivity {

    MediaPlayer player;
    String encounterType;
    Button playButton;
    Button next;
    Button yes;
    Button no;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    MockItem tradedItem;
    Item tradeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encounter_main);
        TextView textView =  findViewById(R.id.encounterType);
        TextView tradeTextView = findViewById(R.id.trade);
        int random = new Random().nextInt(20);
        youTubePlayerView = findViewById(R.id.youtube_encounter);
        playButton = findViewById(R.id.button);
        next = findViewById(R.id.nextButton);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        next.setOnClickListener( e -> {
                Intent intent;
                intent = new Intent(EncounterActivity.this, PlanetActivity.class);
                player.stop();
                startActivity(intent);
        });

        yes.setOnClickListener(e -> {
                if (textView.getText() == "Trader Encounter") {
                    tradeTextView.setVisibility(View.VISIBLE);
                    tradeTextView.setText("Thank you for doing business");
                    MockItem item = new MockItem(tradeItem, 0, 0);
                    Model.getInstance().getMarketInteractor().getRepository().loadCargo(item);
                    MarketInteractor m;
                    m = Model.getInstance().getMarketInteractor();
                    m.getRepository().removeCargo(tradedItem);
                } else {
                    tradeTextView.setVisibility(View.VISIBLE);
                    tradeTextView.setText("You have added a mercenary");
                }
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
        });

        no.setOnClickListener(e -> {
                tradeTextView.setVisibility(View.VISIBLE);
                tradeTextView.setText("Maybe next time");
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);
        });

        if (random < 5) {
            player = MediaPlayer.create(this, R.raw.cops);
            textView.setText("Police Encounter");
            encounterType = "cop";
            playButton.setVisibility(View.VISIBLE);
            int currCredit;
            MarketInteractor m;
            m = Model.getInstance().getMarketInteractor();
            currCredit = (int) m.getRepository().getPlayer().getCredit();
            double toAdd = -.5 * currCredit;
            Model.getInstance().getMarketInteractor().getRepository().getPlayer().editCredit(toAdd);

        } else if (random < 10) {
            player = MediaPlayer.create(this, R.raw.pirates);
            textView.setText("Pirate Encounter");
            encounterType = "pirate";
            playButton.setVisibility(View.VISIBLE);
            int currCredit;
            MarketInteractor m;
            m = Model.getInstance().getMarketInteractor();
            currCredit = (int) m.getRepository().getPlayer().getCredit();
            m.getRepository().getPlayer().editCredit(currCredit);

        } else if (random < 15) {
            player = MediaPlayer.create(this, R.raw.merc);
            textView.setText("Mercenary Encounter");
            tradeTextView.setText("Would you like to add a mercenary to your team?");
            yes.setVisibility(View.VISIBLE);
            no.setVisibility(View.VISIBLE);

        } else {
            player = MediaPlayer.create(this, R.raw.deal);
            textView.setText("Trader Encounter");
            tradeTextView.setVisibility(View.VISIBLE);
            tradedItem = Model.getInstance().getMarketInteractor().getRepository().getRandomItem();
            if (tradedItem == null) {
                tradeTextView.setText("Sorry you do not have cargo to trade");
            } else {
                int rand = new Random().nextInt(Item.values().length);
                tradeItem = Item.values()[rand];
                String tradeItemName = Item.values()[rand].getName();
                tradeTextView.setText("Would you like to trade a " + tradedItem.getName()
                        + " for a " + tradeItemName + "?");
                yes.setVisibility(View.VISIBLE);
                no.setVisibility(View.VISIBLE);
            }
        }
        player.start();

        onInitializedListener = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {

                String youTubeVideo;
                if (encounterType.equals("pirate")) {
                    youTubeVideo = "bjfMVqL344Y";
                } else {
                    youTubeVideo = "SlfkDnvgJAg";
                }

                youTubePlayer.loadVideo(youTubeVideo);

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult result) {

            }
        };

        playButton.setOnClickListener(e -> {
                player.stop();
                youTubePlayerView.setVisibility(View.VISIBLE);
                youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
        });
    }

}
