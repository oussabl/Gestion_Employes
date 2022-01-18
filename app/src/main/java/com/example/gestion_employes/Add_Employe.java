package com.example.gestion_employes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Employe extends AppCompatActivity {


    DatabaseHelper db ;
    Operation operation;
    Button btnEnregistrer,btnBack;
    EditText txt_nom ,txt_prenom,txt_tel,txt_mission,txt_date_dep,txt_date_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__employe);
    db= new DatabaseHelper(this);
    operation = new Operation(db);

    txt_nom = findViewById(R.id.text_nomm);
    txt_prenom = findViewById(R.id.text_prenomm);
    txt_tel = findViewById(R.id.text_tellView);
    txt_mission = findViewById(R.id.text_missionnView);
    txt_date_dep = findViewById(R.id.text_deppView);
    txt_date_arr = findViewById(R.id.text_arrrView);

    btnEnregistrer = findViewById(R.id.buttonEnrt);
    btnEnregistrer.setOnClickListener(v -> {
        String nom = txt_nom.getText().toString();
        String prenom = txt_prenom.getText().toString();
        String tel = txt_tel.getText().toString();
        String mission = txt_mission.getText().toString();
        String date_dep = txt_date_dep.getText().toString();
        String date_arr = txt_date_arr.getText().toString();

        operation.insertData(new Employe(nom,prenom,tel,mission,date_dep,date_arr));
        Toast.makeText(Add_Employe.this, "The Employed is Added ! ", Toast.LENGTH_SHORT).show();
    });

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        });
    }
}