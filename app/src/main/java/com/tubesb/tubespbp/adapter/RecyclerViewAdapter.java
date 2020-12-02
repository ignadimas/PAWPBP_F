package com.tubesb.tubespbp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tubesb.tubespbp.databinding.AdapterRecyclerViewBinding;
import com.tubesb.tubespbp.model.Mahasiswa;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Mahasiswa> result;
    private AdapterRecyclerViewBinding binding;

    public RecyclerViewAdapter(Context context, List<Mahasiswa> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = AdapterRecyclerViewBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Mahasiswa mhs = result.get(position);
        holder.adapterRecyclerViewBinding.setMhs(mhs);
        holder.adapterRecyclerViewBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull AdapterRecyclerViewBinding adapterRecyclerViewBinding){
            super(adapterRecyclerViewBinding.getRoot());
            this.adapterRecyclerViewBinding = adapterRecyclerViewBinding;
        }

        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }

}