package com.example.kaptan.mp_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    EditText delId;
    Button del,delList;
    ListView delliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        delId = (EditText) findViewById(R.id.et_delId);
        del = (Button) findViewById(R.id.btn_delete);
        delList = (Button) findViewById(R.id.btn_delShow);
        delliste = (ListView) findViewById(R.id.lv_delShow);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(delId.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"LUTFEN BIR ID GIRINIZ. ID HAKKINDA FIKRINIZ YOKSA KAYITLARI LISTELEYEREK OGRENEBILIRSINIZ...",Toast.LENGTH_LONG).show();
                else{
                    Database db = new Database(DeleteActivity.this);
                    int id = Integer.parseInt(delId.getText().toString());
                    db.sil(id);
                    Toast.makeText(DeleteActivity.this,"SILME ISLEMI BASARILI...",Toast.LENGTH_LONG).show();
                }
            }
        });

        delList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Listele(); }
        });
    }
    public void Listele() {
        Database vt= new Database(DeleteActivity.this);
        List<String> gelenveri=vt.veritabani_listele();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(DeleteActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,gelenveri);
        delliste.setAdapter(adapter);
    }
}
