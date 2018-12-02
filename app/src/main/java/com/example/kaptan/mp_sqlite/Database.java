package com.example.kaptan.mp_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Kisiler";
    private static final String DATABASE_TABLE="Rehber";
    private static final Integer DATABASE_VERSION=1;

    private static final String KEY_ROW_ID="id";
    private static final String KEY_NAME="isim";
    private static final String KEY_SURNAME="soyisim";
    private static final String KEY_PHONE="telefon";

    public Database(MainActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public Database(UpdateActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public Database(DeleteActivity contex){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "("+KEY_ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_NAME+" TEXT NOT NULL, "
                +KEY_SURNAME+" TEXT NOT NULL, "
                +KEY_PHONE+" TEXT NOT NULL)");
    }
    public List<String> veritabani_listele(){
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase veritabani=this.getReadableDatabase();
        String[] getir={"id","isim","soyisim","telefon"};
        Cursor c= veritabani.query("Rehber",getir,null,null,null,null,null);

        while(c.moveToNext()){
            veriler.add(c.getInt(0) + "-" + c.getString(1) + "-" + c.getString(2) + "-" + c.getString(3));
        }
        veritabani.close();
        return veriler;
    }
    public void sil (int id){
        SQLiteDatabase veritabani = this.getWritableDatabase();
        try {
            String where=KEY_ROW_ID + "=" + id;
            veritabani.delete("Rehber",where,null);
        }
        catch (Exception e){

        }
        veritabani.close();
    }
    public void Guncelle(int id,String adi,String soyadi,String telefon){
        SQLiteDatabase veritabani=this.getWritableDatabase();
        ContentValues tdb=new ContentValues();
        tdb.put(KEY_NAME,adi);
        tdb.put(KEY_SURNAME,soyadi);
        tdb.put(KEY_PHONE,telefon);
        String where=KEY_ROW_ID + "=" + id;
        veritabani.update("Rehber",tdb,where,null);
        veritabani.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public void addData(String ad,String soyad,String telefon ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues tdb=new ContentValues();
        tdb.put(KEY_NAME,ad);
        tdb.put(KEY_SURNAME,soyad);
        tdb.put(KEY_PHONE,telefon);
        db.insert(DATABASE_TABLE,null,tdb);
        db.close();
    }
}
