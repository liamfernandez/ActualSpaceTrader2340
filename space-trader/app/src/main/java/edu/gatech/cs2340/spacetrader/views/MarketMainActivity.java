package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.viewmodels.MarketListViewModel;

public class MarketMainActivity extends AppCompatActivity {

    private MarketListViewModel  viewModel;
    private MarketAdapter adapter;
    private CargoAdapter adapterCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView credit;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_main);

        /*
         Set up our recycler view by grabbing the layout for items
         */
        RecyclerView recyclerView = findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RecyclerView recyclerViewCargo = findViewById(R.id.cargo_list);
        recyclerViewCargo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCargo.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new MarketAdapter();
        recyclerView.setAdapter(adapter);
        adapterCargo = new CargoAdapter();
        recyclerViewCargo.setAdapter(adapterCargo);

        //grab our view model instance
        viewModel = ViewModelProviders.of(this).get(MarketListViewModel.class);

        Button miniGame = findViewById(R.id.mini);
        miniGame.setOnClickListener(e -> {
                Intent intent = new Intent(MarketMainActivity.this, MiniGameActivity.class);
                startActivity(intent);
        });


        Button start = findViewById(R.id.backButton);
        start.setOnClickListener(e -> {
                Intent intent = new Intent(MarketMainActivity.this, PlanetActivity.class);
                startActivity(intent);
        });

        credit = findViewById(R.id.text_money);
        credit.setText(viewModel.getCredit());

        //Log.d("APP", viewModel.getMockItems().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setItemList(viewModel.getMockItems());
        adapterCargo.setCargoList(viewModel.getCargoItems());

        adapter.setOnItemClickListener(e -> {
                boolean flag = viewModel.buyMockItem(e);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
        });

        adapterCargo.setOnItemClickListener(e -> {
                boolean flag = viewModel.sellMockItem(e);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
        });

    }
}
