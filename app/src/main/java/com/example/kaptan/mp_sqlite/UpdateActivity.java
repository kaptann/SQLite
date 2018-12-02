package com.example.kaptan.mp_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    EditText updateId,updateName,updateSurname,updatePhone;
    Button updateButton,updateShow;
    ListView updateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);


        updateId = (EditText) findViewById(R.id.et_update);
        updateName = (EditText) findViewById(R.id.et_updateName);
        updateSurname = (EditText) findViewById(R.id.et_updateSurname);
        updatePhone = (EditText) findViewById(R.id.et_updatePhone);
        updateButton = (Button) findViewById(R.id.btn_update);
        updateShow = (Button) findViewById(R.id.btn_showUpdate);
        updateList = (ListView) findViewById(R.id.lv_updateList);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (updateId.getText().toString().isEmpty())
                    Toast.makeText(UpdateActivity.this,"LUTFEN BIR ID GIRINIZ. ID HAKKINDA FIKRINIZ YOKSA KAYITLARI LISTELEYEREK OGRENEBILIRSINIZ...",Toast.LENGTH_LONG).show();
                else{
                    Database db = new Database(UpdateActivity.this);
                    int id = Integer.parseInt(updateId.getText().toString());
                    String isim = updateName.getText().toString();
                    String soyisim = updateSurname.getText().toString();
                    String tel = updatePhone.getText().toString();
                    db.Guncelle(id,isim,soyisim,tel);
                    Toast.makeText(UpdateActivity.this,"GUNCELLEME BASARILI...",Toast.LENGTH_LONG).show();
                }
            }
        });

        updateShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Listele();

            }
        });
    }
    public void Listele() {
        Database vt= new Database(UpdateActivity.this);
        List<String> gelenveri=vt.veritabani_listele();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(UpdateActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,gelenveri);
        updateList.setAdapter(adapter);
    }
}
