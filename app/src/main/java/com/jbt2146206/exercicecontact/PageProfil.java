package com.jbt2146206.exercicecontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PageProfil extends AppCompatActivity {

    //Constantes de notification
    private static int NOTIF_ID = 1;
    private static String CHANNEL_ID = "channel1";
    private static int PENDING_INTENT_ID = 2;

    //Variables des éléments de la vue
    Button btnEnregistrer;
    EditText etPrenom;
    EditText etInitiales;
    EditText etNom;
    EditText etCourriel1;
    EditText etCourriel2;
    EditText etTelephone1;
    EditText etTelephone2;
    Spinner spinTypeEmail1;
    Spinner spinTypeEmail2;
    Spinner spinTypeTelephone1;
    Spinner spinTypeTelephone2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_profil);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);
        etPrenom = findViewById(R.id.etPrenom);
        etNom = findViewById(R.id.etNom);
        etInitiales = findViewById(R.id.etInitiales);
        etCourriel1 = findViewById(R.id.etEmail1);
        etCourriel2 = findViewById(R.id.etEmail2);
        etTelephone1 = findViewById(R.id.etTelephone1);
        etTelephone2 = findViewById(R.id.etTelephone2);
        spinTypeEmail1 = findViewById(R.id.spinTypeEmail1);
        spinTypeEmail2 = findViewById(R.id.spinTypeEmail2);
        spinTypeTelephone1 = findViewById(R.id.spinTypeTelephone1);
        spinTypeTelephone2 = findViewById(R.id.spinTypeTelephone2);



        //Recevoir le courriel
        Intent intent = getIntent();

        //Aller chercher le courriel dans le intent
        String courriel = intent.getStringExtra("courriel");

        //Vérifier s'il a reçu un courriel
        if(courriel != null){
            //Mettre le courriel dans le champs
            etCourriel1.setText(courriel);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Chanel1";
            String description = "Chanel 1";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }


    }

    public void Enregistrer(View v){
        EnregistrerDonnees();
        CreerNotification(getString(R.string.confirmationEnregistre), getString(R.string.ConfirmationLongueEnregistre), NotificationCompat.PRIORITY_DEFAULT, android.R.drawable.star_on);
        //Toast pour confirmer l'enregistrement
        Toast.makeText(PageProfil.this, R.string.confirmationEnregistre, Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * Fonction pour enregistrer les données dans le SharedPreference
     */
    public void EnregistrerDonnees(){
        //Enregistrer le tout dans les SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("profil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Aller chercher les informations données
        editor.putString("prenom", etPrenom.getText().toString());
        editor.putString("initiales", etInitiales.getText().toString());
        editor.putString("nom", etNom.getText().toString());
        editor.putString("Courriel1", etCourriel1.getText().toString());
        editor.putString("Courriel2", etCourriel2.getText().toString());
        editor.putString("Telephone1", etTelephone1.getText().toString());
        editor.putString("Telephone2", etTelephone2.getText().toString());
        editor.putInt("TypeCourriel1", spinTypeEmail1.getSelectedItemPosition());
        editor.putInt("TypeCourriel2", spinTypeEmail1.getSelectedItemPosition());
        editor.putInt("spinTypeTelephone1", spinTypeTelephone1.getSelectedItemPosition());
        editor.putInt("spinTypeTelephone2", spinTypeTelephone2.getSelectedItemPosition());
        editor.commit();
    }

    /**
     * Méthode permettant de créer une notification
     * @param Title Titre de la notification
     * @param Text Text de la notification
     * @param Priorite Priorite de la notification
     * @param Icon Icon de la notification
     */
    public void CreerNotification(String Title, String Text, int Priorite, int Icon){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(Title)
                .setContentText(Text)
                .setPriority(Priorite)
                .setSmallIcon(Icon);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIF_ID, builder.build());
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

            case R.id.menuRetourContact:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}