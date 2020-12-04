package com.tubesb.tubespbp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.tubesb.tubespbp.api.ApiClient;
import com.tubesb.tubespbp.api.ApiInterface;
import com.tubesb.tubespbp.api.UserApi;
import com.tubesb.tubespbp.api.UserResponse;
import com.tubesb.tubespbp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.scottyab.aescrypt.AESCrypt;
import com.tubesb.tubespbp.api.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.volley.Request.Method.POST;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword, etNama, etAlamat, etTelp;
    private String email,address, password, nama, alamat, telp, userID, AESPassword = "password", encryptedPW;
    private Button btnCancel, btnSubmit;
    private Bitmap bitmap, resizedBitmap;
    private ProgressDialog progressDialog;
    private User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference, imageReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etTelp = findViewById(R.id.etTelp);
        btnCancel = findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        databaseReference = firebaseDatabase.getReference("users");
        storageReference = firebaseStorage.getReference();

//        readDatabase();

        ImageButton btnAddress = findViewById(R.id.btnAddress);
        btnAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final TextInputEditText alamatText = findViewById(R.id.etAlamat);
                Intent Map = new Intent(RegisterActivity.this, MapActivity.class);
                startActivity(Map);

                //Mendapatkan string dari MapActivity untuk alamat
                Intent intent = getIntent();
                address = intent.getStringExtra("alamat");
                alamatText.setText(address);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().isEmpty()) {
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                } else if(!etEmail.getText().toString().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")){
                    etEmail.setError("Please provide valid email!");
                    etEmail.requestFocus();
                } else if(etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Password is required!");
                    etPassword.requestFocus();
                } else if(etPassword.length() <6) {
                    etPassword.setError("Password must be more than 6 characters");
                    etPassword.requestFocus();
                } else if(etNama.getText().toString().isEmpty()) {
                    etNama.setError("Nama is required!", null);
                    etNama.requestFocus();
                } else if(etAlamat.getText().toString().isEmpty()) {
                    etAlamat.setError("Alamat is required!", null);
                    etAlamat.requestFocus();
                } else if(etTelp.getText().toString().isEmpty()) {
                    etTelp.setError("No Telepon is required!");
                    etTelp.requestFocus();
                } else {
                    email = etEmail.getText().toString().trim();
                    password = etPassword.getText().toString().trim();
                    nama = etNama.getText().toString().trim();
                    alamat = etAlamat.getText().toString().trim();
                    telp = etTelp.getText().toString().trim();
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.noimage);
                    bitmap = getResizedBitmap(bitmap, 512);
                    String imageString = encodeImage(bitmap);

                    user = new User(email, password, nama, alamat, telp);
                    registerUser(user, bitmap);
//                    saveUser(email, password, nama, alamat, telp, "");
                }
            }
        });

    }

    private void readDatabase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String encodeImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void registerUser(User user, Bitmap bitmap) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading....");
        progressDialog.setTitle("Register in Progress");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // send verification link
                    firebaseUser = firebaseAuth.getCurrentUser();
                    firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(com.tubesb.tubespbp.RegisterActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: Email not sent " + e.getMessage());
                        }
                    });

                    // SAVE USER TO DATABASE
                    encryptedPW = encrypt(AESPassword, password);
                    user.setPassword(encryptedPW);
                    userID = firebaseUser.getUid();
                    databaseReference.child(userID).setValue(user);

                    // SAVE IMAGE TO STORAGE
//                    imageReferences = storageReference.child(userID).child("Images").child("Profile Pic"); //{userID}/Images/Profile Pic.jpg
//                    UploadTask uploadTask = imageReferences.putFile(imagePath);

                    Toast.makeText(com.tubesb.tubespbp.RegisterActivity.this, "User Has Been Saved", Toast.LENGTH_SHORT).show();

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<UserResponse> add = apiService.createUser(etEmail.getText().toString(), etPassword.getText().toString(),
                            etNama.getText().toString(), etAlamat.getText().toString(), etTelp.getText().toString() );

                    add.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            Toast.makeText(RegisterActivity.this, "Berhasil menambah user", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Gagal menambah user", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });

                    firebaseAuth.signOut();
                    onBackPressed();
                } else {
                    Toast.makeText(com.tubesb.tubespbp.RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }
        });
    }

    private String encrypt(String AESPassword, String message) {
        String encryptedMsg = null;
        try {
            encryptedMsg = AESCrypt.encrypt(AESPassword, message);
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return encryptedMsg;
    }

}