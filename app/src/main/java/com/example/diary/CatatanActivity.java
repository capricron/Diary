package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class CatatanActivity extends AppCompatActivity {

    EditText namaFileEditText, catatanEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        namaFileEditText = findViewById(R.id.namaFileEditText);
        catatanEditText = findViewById(R.id.catatanEditText);

        final Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            simpanData();
        });

    }

    private void simpanData(){
        // lakukan sanity check
        String namaFile = namaFileEditText.getText().toString();
        if(TextUtils.isEmpty(namaFile)){
            Toast.makeText(this,"Mohon nama file di isi", Toast.LENGTH_LONG).show();
            return;
        }

        String catatan = catatanEditText.getText().toString();
        if(TextUtils.isEmpty(catatan)){
            Toast.makeText(this,"Mohon catatan di isi", Toast.LENGTH_LONG).show();
            return;
        }

        buatFile(namaFile,catatan);
        finish();
    }

    private void buatFile(String namaFile, String catatan){
        File file = new  File(getFilesDir(), namaFile);
        FileOutputStream outputStream;

        try{
            file.createNewFile();
            outputStream =  new FileOutputStream(file,true);
            outputStream.write(catatan.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}