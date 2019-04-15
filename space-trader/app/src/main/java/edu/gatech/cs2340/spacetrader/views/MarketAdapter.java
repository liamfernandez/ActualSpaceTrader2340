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

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder>{


    private List<MockItem> itemList = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater l = LayoutInflater.from(parent.getContext());

        View itemView = l.inflate(R.layout.market_item, parent, false);


        return new MarketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {

        MockItem item = itemList.get(position);

        //Log.d("APP", "Binding: " + position + " " + itemList.get(position));

        holder.itemName.setText(item.getName());
        holder.itemPrice.setText("" + item.getBuyingPrice());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<MockItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    class MarketViewHolder extends RecyclerView.ViewHolder {

        private final TextView itemName;
        private final TextView itemPrice;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_Item_Name);
            itemPrice = itemView.findViewById(R.id.text_Item_price);

            itemView.setOnClickListener(e -> {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(itemList.get(position));
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
