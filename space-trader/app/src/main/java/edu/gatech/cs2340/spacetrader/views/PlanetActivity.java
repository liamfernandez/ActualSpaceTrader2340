package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;
import edu.gatech.cs2340.spacetrader.viewmodels.MarketListViewModel;
import edu.gatech.cs2340.spacetrader.viewmodels.PlanetViewModel;

public class PlanetActivity extends AppCompatActivity {

    private MarketListViewModel marketViewModel;
    private PlanetViewModel viewModel;
    private PlanetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet_main);

        RecyclerView recyclerView = findViewById(R.id.solarSystemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        // Setup the adapter for this recycler view
        adapter = new PlanetAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        Button start = findViewById(R.id.toMarket);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanetActivity.this, MarketMainActivity.class);
                startActivity(intent);
            }
        });

        TextView textView =  findViewById(R.id.currentPlanet);
        textView.setText(viewModel.getCurrentPlanet().getName());


        marketViewModel = ViewModelProviders.of(this).get(MarketListViewModel.class);
        marketViewModel.refreshMockItems();

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setSolarSystemsList(viewModel.getInRangeSolarSystems());
        adapter.setOnItemClickListener(new PlanetAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(SolarSystem solarSystem) {
                viewModel.travelToSolarSystem(solarSystem);
                Intent intent = new Intent(PlanetActivity.this, SolarSystemActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
