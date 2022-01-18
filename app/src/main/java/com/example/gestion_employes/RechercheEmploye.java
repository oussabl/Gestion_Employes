package com.example.gestion_employes;

import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RechercheEmploye extends AppCompatActivity {

            TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_employe);
        setTitle("Recherche_Employed");

        txt1 = findViewById(R.id.textView_ID);
        txt2 = findViewById(R.id.text_Viewnomm);
        txt3 = findViewById(R.id.text_Viewprenomm);
        txt4 = findViewById(R.id.text_tellView);
        txt5 = findViewById(R.id.text_missionnView);
        txt6 = findViewById(R.id.text_deppView);
        txt7 = findViewById(R.id.text_arrrView);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
            String _ID = Integer.toString(bundle.getInt("ID"));
            String nom = bundle.getString("nom");
            String prenom = bundle.getString("prenom");
            String tel = bundle.getString("tel");
            String mission = bundle.getString("mission");
            String date_dep = bundle.getString("date_dep");
            String date_arr = bundle.getString("date_arr");

        txt1.setText(_ID);
        txt2.setText(nom);
        txt3.setText(prenom);
        txt4.setText(tel);
        txt5.setText(mission);
        txt6.setText(date_dep);
        txt7.setText(date_arr);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()== android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
