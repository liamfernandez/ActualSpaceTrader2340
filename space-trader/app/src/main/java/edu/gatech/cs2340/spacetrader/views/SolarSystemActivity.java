package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.Random;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodels.SolarSystemViewModel;

/**
 * determines what will be done when the solar system screen is presented to the user
 */
public class SolarSystemActivity extends AppCompatActivity {

    private SolarSystemViewModel viewModel;
    private SolarSystemAdapter adapter;
    Button toVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solar_system_main);

        RecyclerView recyclerView = findViewById(R.id.solarSystemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new SolarSystemAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

        toVideo = findViewById(R.id.toVideo);
        toVideo.setOnClickListener(e -> {
            Intent intent = new Intent(SolarSystemActivity.this, VideoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setPlanetList(viewModel.getPlanetsInSolarSystem());

        adapter.setOnItemClickListener(e -> {
                viewModel.travelToPlanet(e);
                int random = new Random().nextInt(10);
                Intent intent;
                if (random < 4) {
                    intent = new Intent(SolarSystemActivity.this, EncounterActivity.class);
                } else {
                    intent = new Intent(SolarSystemActivity.this, PlanetActivity.class);
                }
                finish();
                startActivity(intent);
        });

    }
}
