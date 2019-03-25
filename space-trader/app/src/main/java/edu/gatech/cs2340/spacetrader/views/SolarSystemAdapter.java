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

public class SolarSystemAdapter extends RecyclerView.Adapter<SolarSystemAdapter.PlanetViewHolder> {

    private List<Planet> planetList = new ArrayList<>();

    private SolarSystemAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_item, parent, false);

        return new PlanetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {

        Planet planet = planetList.get(position);

        Log.d("APP", "Binding: " + position + " " + planetList.get(position));

        holder.planetName.setText(planet.getName());
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }

    public void setPlanetList(List<Planet> planets) {
        planetList = planets;
        notifyDataSetChanged();
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of items
     */
    class PlanetViewHolder extends RecyclerView.ViewHolder {

        private TextView planetName;

        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.planetName);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(planetList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Planet planet);
    }

    public void setOnItemClickListener(SolarSystemAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}
