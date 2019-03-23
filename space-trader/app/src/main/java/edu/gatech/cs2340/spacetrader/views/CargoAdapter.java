package edu.gatech.cs2340.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.MockItem;

public class CargoAdapter extends RecyclerView.Adapter<CargoAdapter.CargoViewHolder>{

    private List<MockItem> cargoList = new ArrayList<>();

    private CargoAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public CargoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_item, parent, false);

        return new CargoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CargoAdapter.CargoViewHolder holder, int position) {

        MockItem item = cargoList.get(position);

        Log.d("APP", "Binding: " + position + " " + cargoList.get(position));

        holder.itemName.setText(item.getName());
        String itemSellPrice = "" + item.getSellingPrice();
        holder.itemPrice.setText(itemSellPrice);
    }

    @Override
    public int getItemCount() {
        return cargoList.size();
    }


    public void setCargoList(List<MockItem> items) {
        cargoList = items;
        notifyDataSetChanged();
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    class CargoViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView itemPrice;

        public CargoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_Item_Name);
            itemPrice = itemView.findViewById(R.id.text_Item_price);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(cargoList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(MockItem item);
    }

    public void setOnItemClickListener(CargoAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
