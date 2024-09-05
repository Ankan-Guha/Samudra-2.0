package com.example.samudra20.Home.Main.BeachInfo.Tide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samudra20.R;

import java.util.ArrayList;

public class TideAdaptor extends RecyclerView.Adapter<TideAdaptor.TideViewHolder> {

    private ArrayList<Tide> tideList;

    public TideAdaptor(ArrayList<Tide> tideList) {
        this.tideList = tideList;
    }

    @NonNull
    @Override
    public TideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tide, parent, false);
        return new TideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TideViewHolder holder, int position) {
        Tide tide = tideList.get(position);
        holder.textViewTideTime.setText(tide.getTime());
        holder.textViewTideType.setText(tide.getType());
        holder.textViewTideLevel.setText(String.format("Level: %.1f", tide.getLevel()));
    }

    @Override
    public int getItemCount() {
        return tideList.size();
    }

    public static class TideViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTideTime;
        TextView textViewTideType;
        TextView textViewTideLevel;

        public TideViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTideTime = itemView.findViewById(R.id.tideTime);
            textViewTideType = itemView.findViewById(R.id.tideType);
            textViewTideLevel = itemView.findViewById(R.id.tideLevel);
        }
    }
}
