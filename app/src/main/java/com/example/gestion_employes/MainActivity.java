package com.example.gestion_employes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Operation operation;
    Button addEmp, delEmp, cheEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GESTION_EMPLOYES");
        db = new DatabaseHelper(this);
        chargerListEmp();
        // chargerList  apres impemante fonction

        addEmp = findViewById(R.id.buttonAdd);
        addEmp.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), Add_Employe.class);
            startActivity(intent);
        });

// Methode Delete 
        delEmp = (Button) findViewById(R.id.buttonDelete);
        delEmp.setOnClickListener(view ->{
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this,R.style.Theme_AppCompat_Dialog);
            alert.setTitle("Delete");
            alert.setMessage("Tape Nom Employe: ");

            final EditText input = new EditText(getApplicationContext());
            alert.setView(input);

            alert.setPositiveButton("OK",(dialog, which) -> {
                String _id = input.getText().toString();
                int id = Delete(_id);
                System.out.println(id);

                if (id > 0  ){
                    Toast.makeText(MainActivity.this,"Suppression effectue avec succés: ",Toast.LENGTH_SHORT).show();
                    chargerListEmp();
                }
                else {
                    Toast.makeText(MainActivity.this,"Employes"+_id+" n'exite pas !!! ",Toast.LENGTH_SHORT).show();
                }

            });
            alert.setNegativeButton("Cancel",(dialog, which) -> {

            });
            alert.show();
        });



        //Methode de chercher ...
        cheEmp = findViewById(R.id.buttonCherche);
        cheEmp.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog);
            alert.setTitle("Rechercher");
            alert.setTitle("Tapez your code");

            final EditText input = new EditText(getApplicationContext());
            alert.setView(input);
            alert.setPositiveButton("OK", (dialog, which) -> {
                int _ID = Integer.parseInt(input.getText().toString());
                System.out.println(_ID);
                Employe emp = getEmplByID(_ID);
                if (emp != null) {
                    Intent intent_Chercher = new Intent(getApplicationContext(), RechercheEmploye.class);
                    intent_Chercher.putExtra("ID", emp.getId());
                    intent_Chercher.putExtra("nom", emp.getNom());
                    intent_Chercher.putExtra("prenom", emp.getPrenom());
                    intent_Chercher.putExtra("tel", emp.getTel());
                    intent_Chercher.putExtra("mission", emp.getMission());
                    intent_Chercher.putExtra("date_dep", emp.getDate_depart());
                    intent_Chercher.putExtra("date_arr", emp.getDate_arrive());
                    startActivity(intent_Chercher);
                } else {
                    Toast.makeText(MainActivity.this, "Employed of " + _ID + " is not existed", Toast.LENGTH_SHORT).show();
                }

            });
            alert.setNegativeButton("Cancel", (dialog, which) -> {

            });
            alert.show();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        chargerListEmp();
    }

    public void chargerListEmp() {

        ArrayList<String> items = new ArrayList<>();
        for (Employe emp : getListEmployes())
            items.add(emp.getId() + " | " + emp.getNom() + " | " + emp.getPrenom() + " | " + emp.getTel() + " | " + emp.getMission() + " | " + emp.getDate_depart() + " | " + emp.getDate_arrive());

        ArrayAdapter<String> itemAdp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(itemAdp);
    }


    //Les Operations Secondaire

    public ArrayList<Employe> getListEmployes() {
        SQLiteDatabase dbEmp = db.getWritableDatabase();
        Cursor res = dbEmp.rawQuery("Select * From " + DatabaseHelper.TABLE_NAME, null);
        ArrayList<Employe> listemp = new ArrayList<>();
        while (res.moveToNext()) {
            Employe emp = new Employe(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
            listemp.add(emp);
        }
        return listemp;
    }

    public Employe getEmplByID(int id) {
        System.out.println("1");
        SQLiteDatabase dbemp = db.getWritableDatabase();
        System.out.println("2");
        Cursor res = dbemp.rawQuery("Select * From " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_id + "= " + id, null);
        System.out.println("3");
        if (res.moveToNext()) {
            System.out.println("4");
            Employe emp = new Employe(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
            return emp;
        } else {
            return null;
        }
    }


    public Integer Delete(String nom) {
        if (nom != null) {
            SQLiteDatabase dbEmpoyes = db.getWritableDatabase();
            return dbEmpoyes.delete(DatabaseHelper.TABLE_NAME, "nom = ? ", new String[]{nom});
        } else {
            return 0;
        }

    }

}



































    /* Operation operation;
    Button btnInsert,btnDelete;
    EditText txtID,txtNOM,txtPRENOM,txtTEL,txtMISSION,txtDATE_DEP,txtDATE_ARR;
    ListView listview;
 //   SQLiteDatabase db;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GESTION_EMPLOYES");

        db = new DatabaseHelper(this);
        operation = new Operation(db);

        btnInsert = (Button) findViewById(R.id.buttonEnrt);
     txtID = (EditText) findViewById(R.id.text_ID);
     txtNOM = (EditText) findViewById(R.id.text_nomm);
     txtPRENOM = (EditText) findViewById(R.id.text_prenomm);
     txtTEL = (EditText) findViewById(R.id.text_tell);
     txtMISSION = (EditText) findViewById(R.id.text_missionn);
     txtDATE_DEP = (EditText) findViewById(R.id.text_depp);
     txtDATE_ARR = (EditText) findViewById(R.id.text_arrr);
        listview = findViewById(R.id.listview);
        //showData();
    btnInsert.setOnClickListener( new View.OnClickListener()  {
       @Override
        public void onClick(View view) {
           String nom      = txtNOM.getText().toString();
           String prenom   = txtPRENOM.getText().toString();
           String tel      = txtTEL.getText().toString();
           String mission  = txtMISSION.getText().toString();
           String date_dep = txtDATE_DEP.getText().toString();
           String date_arr = txtDATE_ARR.getText().toString();
                db=openHelper.getWritableDatabase();
                 Employe emp = new Employe(nom,prenom,tel,mission,date_dep,date_arr);
                insertData(emp);
                //getAllEmp();

           Toast.makeText(getApplicationContext(), "INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }

    });

    btnDelete = (Button) findViewById(R.id.buttonBACK);
    btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            db = openHelper.getWritableDatabase();
            String id = txtID.getText().toString();
            delete(id);
            Toast.makeText(MainActivity.this, " Deleted this Employe", Toast.LENGTH_SHORT).show();
        }
    });
    }

public void insertData(Employe emp){

    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.COL_NOM,emp.getNom());
    values.put(DatabaseHelper.COL_PRENOM,emp.getPrenom());
    values.put(DatabaseHelper.COL_TELEPHONE,emp.getTel());
    values.put(DatabaseHelper.COL_MISSION,emp.getMission());
    values.put(DatabaseHelper.COL_DATE_DEPART,emp.getDate_depart());
    values.put(DatabaseHelper.COL_DATE_ARRIVE,emp.getDate_arrive());
    long id = db.insert(DatabaseHelper.TABLE_NAME,null,values);

    if (id == -1 )
        Toast.makeText(this, "NON", Toast.LENGTH_SHORT).show();
    else {
        Toast.makeText(this,id+"***"+ emp.toString(), Toast.LENGTH_SHORT).show();
    }
    txtNOM.setText("");
    txtPRENOM.setText("");
    txtTEL.setText("");
    txtMISSION.setText("");
    txtDATE_DEP.setText("");
    txtDATE_ARR.setText("");
}

public boolean delete(String id){
    return  db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COL_id +" =?",new String[]{id})>0;
    }


    public void showData(){

        ArrayList<String> listData = new ArrayList<>();
       for (Employe emp : operation.getListEmploye()){
          lst.add(emp.getId()+" "+emp.getNom()+" "+emp.getPrenom()+" "+emp.getTel()+" "+emp.getMission()+" "+emp.getDate_depart()+" "+emp.getDate_arrive());
       }
        ArrayAdapter lst = new ArrayAdapter(this, android.R.layout.activity_list_item,listData);

        ListView listView = findViewById(R.id.listview);
        listview.setAdapter(lst);
    }
}



/*package com.example.gestion_employes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnDelete;
    EditText txtID,txtNOM,txtPRENOM,txtTEL,txtMISSION,txtDATE_DEP,txtDATE_ARR;
    SQLiteOpenHelper openHelper;
    ListView listview;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GESTION_EMPLOYES");

        openHelper = new DatabaseHelper(this);

        btnInsert = (Button) findViewById(R.id.buttonEnrt);
     txtID = (EditText) findViewById(R.id.text_ID);
     txtNOM = (EditText) findViewById(R.id.text_nomm);
     txtPRENOM = (EditText) findViewById(R.id.text_prenomm);
     txtTEL = (EditText) findViewById(R.id.text_tell);
     txtMISSION = (EditText) findViewById(R.id.text_missionn);
     txtDATE_DEP = (EditText) findViewById(R.id.text_depp);
     txtDATE_ARR = (EditText) findViewById(R.id.text_arrr);
        listview = findViewById(R.id.listview);
        showData();
    btnInsert.setOnClickListener( new View.OnClickListener()  {
       @Override
        public void onClick(View view) {
           String nom      = txtNOM.getText().toString();
           String prenom   = txtPRENOM.getText().toString();
           String tel      = txtTEL.getText().toString();
           String mission  = txtMISSION.getText().toString();
           String date_dep = txtDATE_DEP.getText().toString();
           String date_arr = txtDATE_ARR.getText().toString();
                db=openHelper.getWritableDatabase();
                 Employe emp = new Employe(nom,prenom,tel,mission,date_dep,date_arr);
                insertData(emp);
                getAllEmp();

           Toast.makeText(getApplicationContext(), "INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }

    });

    btnDelete = (Button) findViewById(R.id.buttonBACK);
    btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            db = openHelper.getWritableDatabase();
            String id = txtID.getText().toString();
            delete(id);
            Toast.makeText(MainActivity.this, " Deleted this Employe", Toast.LENGTH_SHORT).show();
        }
    });


    }

public void insertData(Employe emp){

    ContentValues values = new ContentValues();
    values.put(DatabaseHelper.COL_NOM,emp.getNom());
    values.put(DatabaseHelper.COL_PRENOM,emp.getPrenom());
    values.put(DatabaseHelper.COL_TELEPHONE,emp.getTel());
    values.put(DatabaseHelper.COL_MISSION,emp.getMission());
    values.put(DatabaseHelper.COL_DATE_DEPART,emp.getDate_depart());
    values.put(DatabaseHelper.COL_DATE_ARRIVE,emp.getDate_arrive());
    long id = db.insert(DatabaseHelper.TABLE_NAME,null,values);

    if (id == -1 )
        Toast.makeText(this, "NON", Toast.LENGTH_SHORT).show();
    else {
        Toast.makeText(this,id+"***"+ emp.toString(), Toast.LENGTH_SHORT).show();
    }
    txtNOM.setText("");
    txtPRENOM.setText("");
    txtTEL.setText("");
    txtMISSION.setText("");
    txtDATE_DEP.setText("");
    txtDATE_ARR.setText("");
}

public boolean delete(String id){
    return  db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COL_id +" =?",new String[]{id})>0;
    }


public ArrayList getAllEmp(){

        ArrayList arrayList = new ArrayList();
    SQLiteDatabase db  =  openHelper.getReadableDatabase();
    Cursor res = db.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME,null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            String id = res.getString(0);
            String nom = res.getString(1);
            String pre = res.getString(2);
            String tel = res.getString(3);
            String miss = res.getString(4);
            String date_dep = res.getString(5);
            String date_arr = res.getString(6);
            Employe emp = new Employe(nom,pre,tel,miss,date_dep,date_arr);
            arrayList.add(emp.toString());
            //arrayList.add('{'+id+" |"+nom+" |"+pre+" |"+tel+" |"+miss+" |"+date_dep+" |"+date_arr+'}');
            res.moveToNext();
        }
            return arrayList;
    }
    public void showData(){
        ArrayList<String> listData = getAllEmp();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.activity_list_item,listData);
        listview.setAdapter(arrayAdapter);
    }
}*/