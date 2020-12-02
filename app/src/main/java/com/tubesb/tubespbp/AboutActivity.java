package com.tubesb.tubespbp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tubesb.tubespbp.adapter.RecyclerViewAdapter;
import com.tubesb.tubespbp.databinding.ActivityAboutBinding;
import com.tubesb.tubespbp.model.DaftarMahasiswa;
import com.tubesb.tubespbp.model.Mahasiswa;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    private ArrayList<Mahasiswa> ListMahasiswa;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.recyclerViewMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMahasiswa.setHasFixedSize(true);

        ListMahasiswa = new DaftarMahasiswa().MAHASISWA;

        recyclerView = findViewById(R.id.recycler_view_mahasiswa);
        adapter = new RecyclerViewAdapter(AboutActivity.this, ListMahasiswa);
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
