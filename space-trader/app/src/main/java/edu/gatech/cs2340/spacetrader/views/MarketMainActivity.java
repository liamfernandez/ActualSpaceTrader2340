package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.MockItem;
import edu.gatech.cs2340.spacetrader.viewmodels.MarketListViewModel;

public class MarketMainActivity extends AppCompatActivity {

    private MarketListViewModel  viewModel;
    private MarketAdapter adapter;
    private CargoAdapter adapterCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_main);

        /*
         Set up our recycler view by grabbing the layout for a single item
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

        Log.d("APP", viewModel.getMockItems().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setItemList(viewModel.getMockItems());
        adapterCargo.setCargoList(viewModel.getCargoItems());

        adapter.setOnItemClickListener(new MarketAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(MockItem item) {

            }
        });
    }
}
