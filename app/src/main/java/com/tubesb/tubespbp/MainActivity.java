package com.tubesb.tubespbp;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;

    EditText namaLengkap, alamat, no_telp;
    String namastr, alamatr, nostr,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPreferences();
        setProfile();

        namaLengkap = findViewById(R.id.inputNama);
        alamat = findViewById(R.id.inputAlamat);
        no_telp = findViewById(R.id.inputNoHp);
        preferences =getSharedPreferences("MyUser", Context.MODE_PRIVATE);

        //NOTIF FIREBASE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            String mag = "Failed";
                            Toast.makeText(MainActivity.this, mag, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        ImageButton btnAddress = findViewById(R.id.btnAddress);
        btnAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final TextInputEditText alamatText = findViewById(R.id.inputAlamat);
                Intent Map = new Intent(MainActivity.this, MapActivity.class);
                startActivity(Map);

                //Mendapatkan string dari MapActivity untuk alamat
                Intent intent = getIntent();
                address = intent.getStringExtra("alamat");
                alamat.setText(address);

                //Langsung masukkan alamat ke dalam preference
                alamatr = alamat.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("alamat", alamatr);
                editor.commit();

                editor.putString("alamat", alamatText.getText().toString());
                editor.apply();

            }
        });

        Button addBtn = findViewById(R.id.btnMulai);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namastr = namaLengkap.getText().toString();
                alamatr = alamat.getText().toString();
                nostr = no_telp.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("nama", namastr);
                editor.putString("alamat", alamatr);
                editor.putString("no handphone", nostr);
                editor.commit();
                Toast.makeText(MainActivity.this, "Profile saved", Toast.LENGTH_SHORT).show();
                //Intent Main = new Intent(MainActivity.this, ProfileActivity.class);
                //startActivity(Main);
                savePreferences();
            }
        });

    }

    private void setProfile(){
        TextInputEditText namaLengkapText = findViewById(R.id.inputNama);
        TextInputEditText alamatText = findViewById(R.id.inputAlamat);
        TextInputEditText no_telpText = findViewById(R.id.inputNoHp);
        namaLengkapText.setText(namastr);
        alamatText.setText(alamatr);
        no_telpText.setText(nostr);
    }

    private void loadPreferences(){
        preferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        if(preferences!=null){
            namastr = preferences.getString("nama", "");
            alamatr = preferences.getString("alamat", "");
            nostr = preferences.getString("no handphone", "");
        }
    }

    private void savePreferences(){

        final TextInputEditText namaLengkapText = findViewById(R.id.inputNama);
        final TextInputEditText alamatText = findViewById(R.id.inputAlamat);
        final TextInputEditText no_telpText = findViewById(R.id.inputNoHp);
        SharedPreferences.Editor editor = preferences.edit();
        final Intent Main = new Intent(this, DashboardActivity.class);
        if(!namaLengkapText.getText().toString().isEmpty() && !alamatText.getText().toString().isEmpty() && !no_telpText.getText().toString().isEmpty()){
            editor.putString("nama", namaLengkapText.getText().toString());
            editor.putString("alamat", alamatText.getText().toString());
            editor.putString("no handphone", no_telpText.getText().toString());
            editor.apply();
            //Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
            startActivity(Main);
            finish();
        }else{
            Toast.makeText(this, "Fill correctly", Toast.LENGTH_SHORT).show();

        }
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