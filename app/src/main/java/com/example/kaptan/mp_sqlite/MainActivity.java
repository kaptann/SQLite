package com.example.kaptan.mp_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText ad,soyad,telefon,updateId;
    Button kaydet,sil,guncelle,listele;
    ListView goster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ad=(EditText)findViewById(R.id.et_name);
        soyad=(EditText)findViewById(R.id.et_surname);
        telefon=(EditText)findViewById(R.id.et_phone);
        updateId=(EditText)findViewById(R.id.et_update);
        goster=(ListView)findViewById(R.id.lv_list);

        kaydet=(Button)findViewById(R.id.btn_save);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ad.getText().toString().isEmpty() || soyad.getText().toString().isEmpty() || telefon.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"LÜTFEN BİLGİLERİ EKSİKSİZ OALRAK GİRİNİZ...", Toast.LENGTH_LONG).show();
                else {
                    String isim = ad.getText().toString();
                    String soyisim = soyad.getText().toString();
                    String tel = telefon.getText().toString();

                    Database db = new Database(MainActivity.this);
                    db.addData(isim, soyisim, tel);
                    Toast.makeText(MainActivity.this, "KAYIT BASARILI.", Toast.LENGTH_LONG).show();
                    ad.setText("");
                    soyad.setText("");
                    telefon.setText("");
                }
            }
        });

        listele=(Button)findViewById(R.id.btn_show);
        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listele();
            }
        });

        guncelle=(Button)findViewById(R.id.btn_update);
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(next);
            }
        });

        sil=(Button)findViewById(R.id.btn_delete);
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(next);
                }
        });
    }
    public void Listele() {
        Database vt= new Database(MainActivity.this);
        List<String> gelenveri=vt.veritabani_listele();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,gelenveri);
        goster.setAdapter(adapter);
    }
}
