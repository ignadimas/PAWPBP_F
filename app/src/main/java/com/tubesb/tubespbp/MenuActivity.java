package com.tubesb.tubespbp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tubesb.tubespbp.adapter.RecyclerViewAdapterMobil;
import com.tubesb.tubespbp.databinding.ActivityMenuBinding;
import com.tubesb.tubespbp.model.DaftarMobil;
import com.tubesb.tubespbp.model.Mobil;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Mobil> listMobil;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMobil adapter;
    private ActivityMenuBinding binding;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        binding.recyclerViewMobil.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMobil.setHasFixedSize(true);

        listMobil = new DaftarMobil().MOBIL;

        recyclerView = findViewById(R.id.recycler_view_mobil);
        adapter = new RecyclerViewAdapterMobil(MenuActivity.this, listMobil);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_dashboard){
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Apa Kamu yakin untuk Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "Ya", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "Tidak", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}