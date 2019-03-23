package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.viewmodels.SolarSystemViewModel;

public class SolarSystemActivity extends AppCompatActivity {

    private SolarSystemViewModel viewModel;
    private SolarSystemAdapter adapter;

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
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setPlanetList(viewModel.getPlanetsInSolarSystem());

        adapter.setOnItemClickListener(new SolarSystemAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Planet planet) {
                viewModel.travelToPlanet(planet);
                Intent intent = new Intent(SolarSystemActivity.this, PlanetActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }
}
