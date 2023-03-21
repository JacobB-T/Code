package com.jbt2146206.exercicecontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button btnModifierProfil;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvContacts);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Récupérer le texte de l'item cliqué
                String courriel = listView.getItemAtPosition(position).toString();
                //Appeler la deuxième page
                Intent intent = new Intent(MainActivity.this, PageProfil.class);
                //Envoyer la variable
                intent.putExtra("courriel", courriel);
                startActivity(intent);

            }
        });
    }

    //Afficher le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Aller chercher quand un élément du menu est cliqué
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuModification:
                //Appeler la deuxième page
                Intent intent = new Intent(MainActivity.this, PageProfil.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Modification(View v){
        //Appeler la deuxième page
        Intent intent = new Intent(MainActivity.this, PageProfil.class);
        startActivity(intent);
    }





}