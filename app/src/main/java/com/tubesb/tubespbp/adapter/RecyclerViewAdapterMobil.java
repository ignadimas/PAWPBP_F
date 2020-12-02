package com.tubesb.tubespbp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tubesb.tubespbp.databinding.AdapterRecyclerViewMobilBinding;
import com.tubesb.tubespbp.model.Mobil;

import java.util.List;

public class RecyclerViewAdapterMobil extends RecyclerView.Adapter<RecyclerViewAdapterMobil.MyViewHolder> {
    private Context context;
    private List<Mobil> result;
    private AdapterRecyclerViewMobilBinding binding;

    public RecyclerViewAdapterMobil(Context context, List<Mobil> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = AdapterRecyclerViewMobilBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Mobil mobil = result.get(position);
        holder.adapterRecyclerViewBinding.setMobil(mobil);
        holder.adapterRecyclerViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AdapterRecyclerViewMobilBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull AdapterRecyclerViewMobilBinding adapterRecyclerViewBinding){
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;
        }

        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }

}