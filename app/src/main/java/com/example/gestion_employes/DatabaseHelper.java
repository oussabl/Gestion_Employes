package com.example.gestion_employes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


 public class DatabaseHelper extends SQLiteOpenHelper {

    public  static final String DATABASE_NAME= "Employes.db";
    public  static final String TABLE_NAME= "employes";
    public  static final int VERSION= 1;
    public  static final String COL_id= "ID";
    public  static final String COL_NOM= "nom";
    public  static final String COL_PRENOM= "prenom";
    public  static final String COL_TELEPHONE= "tel";
    public  static final String COL_MISSION= "mission";
    public  static final String COL_DATE_DEPART= "date_dep";
    public  static final String COL_DATE_ARRIVE= "date_arr";


    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE "+TABLE_NAME+"( ID INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,prenom TEXT,tel TEXT,mission TEXT,date_dep TEXT,date_arr TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);
    }
}
