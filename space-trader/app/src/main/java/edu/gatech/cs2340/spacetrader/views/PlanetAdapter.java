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
import edu.gatech.cs2340.spacetrader.entity.Planet;
import edu.gatech.cs2340.spacetrader.entity.SolarSystem;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.SolarSystemViewHolder> {

    private List<SolarSystem> solarSystemsList = new ArrayList<>();

    private OnItemClickListener listener;

    @NonNull
    @Override
    public SolarSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solar_system_item, parent, false);

        return new SolarSystemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SolarSystemViewHolder holder, int position) {

        SolarSystem solarSystem = solarSystemsList.get(position);

        Log.d("APP", "Binding: " + position + " " + solarSystemsList.get(position));

        holder.solarSystemName.setText(solarSystem.getName());
        holder.distance.setText("" + (int) solarSystem.getDistance());

    }

    @Override
    public int getItemCount() {
        return solarSystemsList.size();
    }

    public void setSolarSystemsList(List<SolarSystem> solarSystems) {
        solarSystemsList = solarSystems;
        notifyDataSetChanged();
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    class SolarSystemViewHolder extends RecyclerView.ViewHolder {

        private TextView solarSystemName;
        private TextView distance;

        public SolarSystemViewHolder(@NonNull View itemView) {
            super(itemView);
            solarSystemName = itemView.findViewById(R.id.solar_system_name);
            distance = itemView.findViewById(R.id.distance);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(solarSystemsList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(SolarSystem solarSystem);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
