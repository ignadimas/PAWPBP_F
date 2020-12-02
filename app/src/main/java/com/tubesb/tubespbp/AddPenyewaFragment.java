package com.tubesb.tubespbp;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tubesb.tubespbp.database.DatabaseClient;
import com.tubesb.tubespbp.model.Penyewa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPenyewaFragment extends Fragment {

    TextInputEditText inputNama, inputAlamat, inputMobil, inputHari, inputTanggal;
    Button addBtn, cancelBtn;
    Penyewa penyewa;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextInputEditText tvDateResult;
    AutoCompleteTextView autoCompleteTextView;
    private String CHANNEL_ID = "Channel 1";

    public AddPenyewaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_fragment, container, false);
        inputNama = view.findViewById(R.id.input_nama);
        inputAlamat = view.findViewById(R.id.input_alamat);
        String[] choose = {"Avanza" , "HRV" , "INNOVA" , "FORTUNER" , "CRV"};
        autoCompleteTextView = view.findViewById(R.id.input_mobil);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, choose);
        autoCompleteTextView.setAdapter(arrayAdapter);
        inputHari = view.findViewById(R.id.input_hari);
        addBtn = view.findViewById(R.id.btn_add);
        cancelBtn = view.findViewById(R.id.btn_cancel);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = view.findViewById(R.id.input_tanggal);
        inputTanggal = view.findViewById(R.id.input_tanggal);

        penyewa = new Penyewa();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextInputLayout inputNamaLayout = view.findViewById(R.id.input_nama_layout);
        final TextInputLayout inputAlamatLayout = view.findViewById(R.id.input_alamat_layout);
        final TextInputLayout inputMobilLayout = view.findViewById(R.id.input_mobil_layout);
        final TextInputLayout inputHariLayout = view.findViewById(R.id.input_hari_layout);
        final TextInputLayout inputTanggalLayout = view.findViewById(R.id.input_tanggal_layout);

        tvDateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = String.valueOf(inputNama.getText());
                String alamat = String.valueOf(inputAlamat.getText());
                String mobil = String.valueOf(autoCompleteTextView.getText());
                String hari = String.valueOf(inputHari.getText());
                String tanggal = String.valueOf(inputTanggal.getText());

                if(nama.isEmpty()||alamat.isEmpty()||mobil.isEmpty()||hari.isEmpty()||tanggal.isEmpty()){
                    inputNamaLayout.setError("Harap isi nama dengan benar.");
                    inputAlamatLayout.setError("Harap isi alamat dengan benar.");
                    inputMobilLayout.setError("Harap isi mobil dengan benar.");
                    inputHariLayout.setError("Harap isi lama sewa ");
                    inputTanggalLayout.setError("Harap isi tanggal dengan benar.");
                }else{
                    penyewa.setNama(inputNama.getText().toString());
                    penyewa.setAlamat(inputAlamat.getText().toString());
                    penyewa.setMobil(autoCompleteTextView.getText().toString());
                    penyewa.setHari(inputHari.getText().toString());
                    penyewa.setTanggal(inputTanggal.getText().toString());
                    insert(penyewa);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(AddPenyewaFragment.this).commit();
            }
        });
    }

    private void insert(final Penyewa penyewa){
        class InsertPenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .insert(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Penyewa Tersimpan", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(AddPenyewaFragment.this).commit();
                createNotificationChannel();
                addNotification();
            }
        }

        InsertPenyewa insert = new InsertPenyewa();
        insert.execute();
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void createNotificationChannel() {
        //  NotificationChannel diperlukan untuk API 26+.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //  Register the channel with the system; yoyu can't change the importance
            //  or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {
        //konstruktor NotificationCompat.Builder hatus diberi CHANNEL_ID unbtuk API level 26+.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Selamat !!!")
                .setContentText("Anda berhasil menyewa mobil, silahkan berkendara dengan aman. Stay Safe :)")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //  Saat notif dipencet balik ke BookingActivity
        Intent notificationIntent = new Intent(this.requireContext(), BookingActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.requireContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        //Menampilkan notifikasi
        NotificationManager manager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
