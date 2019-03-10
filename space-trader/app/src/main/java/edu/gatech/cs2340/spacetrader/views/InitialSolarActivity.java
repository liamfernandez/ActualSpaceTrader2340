package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodels.MarketListViewModel;

public class InitialSolarActivity extends AppCompatActivity {

    private MarketListViewModel marketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_solar_main);
        Button start = findViewById(R.id.toMarket);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitialSolarActivity.this, MarketMainActivity.class);
                startActivity(intent);
            }
        });

        marketViewModel = ViewModelProviders.of(this).get(MarketListViewModel.class);
        marketViewModel.refreshMockItems();

    }
}
