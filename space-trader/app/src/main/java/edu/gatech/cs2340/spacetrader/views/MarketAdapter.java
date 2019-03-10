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
import edu.gatech.cs2340.spacetrader.entity.Item;
import edu.gatech.cs2340.spacetrader.entity.MockItem;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder>{


    /** a copy of the list of students in the model */
    private List<MockItem> itemList = new ArrayList<>();

    private List<MockItem> cargoList = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single student in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_item, parent, false);

        return new MarketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {

        //bind the student data for one student
        MockItem item = itemList.get(position);

        Log.d("APP", "Binding: " + position + " " + itemList.get(position));

        holder.itemName.setText(item.getName());
        holder.itemPrice.setText("" + item.getBasePrice());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<MockItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    public void setCargoList(List<MockItem> items) {
        cargoList = items;
        notifyDataSetChanged();
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    class MarketViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView itemPrice;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_Item_Name);
            itemPrice = itemView.findViewById(R.id.text_Item_price);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(itemList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(MockItem item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
