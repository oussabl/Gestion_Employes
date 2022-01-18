package com.example.gestion_employes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Operation {
    DatabaseHelper db;

    public Operation(DatabaseHelper db) {
        this.db = db;
    }

    public void insertData(Employe emp) {
        SQLiteDatabase dbemp = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COL_NOM, emp.getNom());
        values.put(DatabaseHelper.COL_PRENOM, emp.getPrenom());
        values.put(DatabaseHelper.COL_TELEPHONE, emp.getTel());
        values.put(DatabaseHelper.COL_MISSION, emp.getMission());
        values.put(DatabaseHelper.COL_DATE_DEPART, emp.getDate_depart());
        values.put(DatabaseHelper.COL_DATE_ARRIVE, emp.getDate_arrive());
        long id = dbemp.insert(DatabaseHelper.TABLE_NAME, null, values);

        if (id == -1)
            System.out.println("********* IT FIELD !**********");
        else {
            System.out.println("****** IS DONE !*************" + emp.toString());

        }
    }


}
