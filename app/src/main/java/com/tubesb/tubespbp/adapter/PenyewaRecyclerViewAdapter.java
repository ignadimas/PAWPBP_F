package com.tubesb.tubespbp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tubesb.tubespbp.R;
import com.tubesb.tubespbp.UpdatePenyewaFragment;
import com.tubesb.tubespbp.model.Penyewa;

import java.util.ArrayList;
import java.util.List;

public class PenyewaRecyclerViewAdapter extends RecyclerView.Adapter<PenyewaRecyclerViewAdapter.PenyewaViewHolder> {

    private Context context;
    private List<Penyewa> penyewaList;
    private List<Penyewa> penyewaListFull = new ArrayList<>();

    public PenyewaRecyclerViewAdapter(Context context, List<Penyewa> penyewaList) {
        this.context = context;
        this.penyewaList = penyewaList;
        penyewaListFull.addAll(penyewaList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PenyewaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_penyewa, parent, false);
        return new PenyewaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenyewaViewHolder holder, int position) {
        Penyewa penyewa = penyewaList.get(position);
        holder.txtNama.setText(penyewa.getNama());
        holder.txtAlamat.setText(penyewa.getAlamat());
        holder.txtMobil.setText(penyewa.getMobil());
        holder.txtHari.setText(penyewa.getHari() + " Hari");
        holder.txtTanggal.setText(penyewa.getTanggal());
    }

    @Override
    public int getItemCount() {
        return penyewaList.size();
    }

    public Filter getFilter() {
        return filterPenyewa;
    }

    public class PenyewaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNama, txtAlamat, txtMobil, txtHari,txtTanggal;

        public PenyewaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.namaPenyewa);
            txtAlamat= itemView.findViewById(R.id.alamat);
            txtMobil = itemView.findViewById(R.id.mobil);
            txtHari = itemView.findViewById(R.id.hari);
            txtTanggal = itemView.findViewById(R.id.tanggal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Penyewa penyewa = penyewaList.get(getAdapterPosition());
            Bundle data = new Bundle();
            data.putSerializable("penyewa", penyewa);
            UpdatePenyewaFragment updatePenyewaFragment = new UpdatePenyewaFragment();
            updatePenyewaFragment.setArguments(data);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, updatePenyewaFragment)
                    .commit();
        }
    }

    private Filter filterPenyewa = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Penyewa> filterList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0) {
                filterList.addAll(penyewaListFull) ;
            }
            else {
                String pattern = charSequence.toString().toLowerCase().trim();

                for(Penyewa P : penyewaListFull) {
                    if(P.getNama().toLowerCase().contains(pattern))
                        filterList.add(P);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            penyewaList.clear();

            penyewaList.addAll((List<Penyewa>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}

