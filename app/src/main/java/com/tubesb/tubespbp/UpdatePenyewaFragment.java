package com.tubesb.tubespbp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.tubesb.tubespbp.database.DatabaseClient;
import com.tubesb.tubespbp.model.Penyewa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdatePenyewaFragment extends Fragment {

    TextInputEditText inputNama, inputAlamat, inputHari, inputTanggal;
    Button saveBtn, deleteBtn, cancelBtn;
    Penyewa penyewa;
    AutoCompleteTextView autoCompleteTextView;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextInputEditText tvDateResult;

    public UpdatePenyewaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.update_fragment, container, false);
        penyewa = (Penyewa) getArguments().getSerializable("penyewa");
        inputNama = view.findViewById(R.id.input_nama);
        inputAlamat = view.findViewById(R.id.input_alamat);
        String[] choose = {"AVANZA" , "HRV" , "INNOVA" , "FORTUNER" , "CRV"};
        autoCompleteTextView = view.findViewById(R.id.input_mobil);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.dropdown, choose);
        autoCompleteTextView.setAdapter(arrayAdapter);
        inputHari = view.findViewById(R.id.input_hari);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = view.findViewById(R.id.input_tanggal);
        inputTanggal = view.findViewById(R.id.input_tanggal);
        saveBtn = view.findViewById(R.id.btn_update);
        deleteBtn = view.findViewById(R.id.btn_delete);
        cancelBtn = view.findViewById(R.id.btn_cancel);

        try {
            if (penyewa.getNama() != null && penyewa.getAlamat() != null && penyewa.getMobil() != null && penyewa.getHari() != null  && penyewa.getTanggal() != null) {
                inputNama.setText(penyewa.getNama());
                inputAlamat.setText(penyewa.getAlamat());
                inputHari.setText(penyewa.getHari());
                inputTanggal.setText(penyewa.getTanggal());
            } else {
                inputNama.setText("-");
                inputAlamat.setText("-");
                inputHari.setText("-");
                inputTanggal.setText("-");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penyewa.setNama(inputNama.getText().toString());
                penyewa.setAlamat(inputAlamat.getText().toString());
                penyewa.setMobil(autoCompleteTextView.getText().toString());
                penyewa.setHari(inputHari.getText().toString());
                penyewa.setTanggal(inputTanggal.getText().toString());
                update(penyewa);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdatePenyewaFragment.this).commit();
            }
        });
    }

    private void update(final Penyewa penyewa){
        class UpdatePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .update(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Data Sewa berhasil diperbarui", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdatePenyewaFragment.this).commit();
            }
        }

        UpdatePenyewa update = new UpdatePenyewa();
        update.execute();
    }

    private void delete(final Penyewa penyewa){
        class DeletePenyewa extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getActivity().getApplicationContext()).getDatabase()
                        .penyewaDao()
                        .delete(penyewa);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity().getApplicationContext(), "Data Sewa Telah Terhapus", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.hide(UpdatePenyewaFragment.this).commit();
            }
        }

        DeletePenyewa delete = new DeletePenyewa();
        delete.execute();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setMessage("Apakah Yakin Untuk Menghapus Data ini");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(penyewa);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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

}

