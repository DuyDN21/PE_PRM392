package com.example.pe_prm392.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pe_prm392.Models.Country;
import com.example.pe_prm392.R;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListHolder> {

    List<Country> countryList;

    public CountryListAdapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout, parent, false);
        return new CountryListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListHolder holder, int position) {
        holder.tvName.setText(countryList.get(position).getName());
        holder.tvRank.setText(Integer.toString(countryList.get(position).getRank()));
        holder.tvGDP.setText(Long.toString(countryList.get(position).getGdppc()));
        holder.tvYear.setText(countryList.get(position).getYear());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    class CountryListHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvRank;
        TextView tvYear;
        TextView tvGDP;

        public CountryListHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Name);
            tvRank = itemView.findViewById(R.id.tv_Rank);
            tvYear = itemView.findViewById(R.id.tv_Year);
            tvGDP = itemView.findViewById(R.id.tv_GDP);
        }
    }
}
