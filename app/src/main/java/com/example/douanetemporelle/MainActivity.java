package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activite 1 : El formulaire d'enregistrement mta3 el voyageur.
 */
public class MainActivity extends AppCompatActivity {

    // 1) N3arfou el views elli bech nesta3mlouhom
    EditText etNom, etNaissance, etDestination;
    RadioGroup rgMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // 2) N7otou el layout mta3 el formulaire
        setContentView(R.layout.activity_main);
        // 3) Code mta3 Android Studio : bech el contenu ma yet7atech ta7t el barre mta3 fou9
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4) Nrabtou kol variable bel view mta3ha b findViewById
        etNom = findViewById(R.id.et_nom_ar);
        etNaissance = findViewById(R.id.et_naissance_ar);
        etDestination = findViewById(R.id.et_destination_ar);
        rgMotif = findViewById(R.id.rg_motif_ar);
        btnInspecter = findViewById(R.id.btn_inspecter_ar);

        // 5) Ki nenzlou 3al bouton "Inspecter le profil"
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecterProfil();
            }
        });
    }

    // 6) Methode elli tjib les donnees w tab3athhom lel Activite 2
    private void inspecterProfil() {
        // 7) Nekhdhou el texte mta3 kol champ (trim = nna7iw les espaces)
        String nom = etNom.getText().toString().trim();
        String naissanceTexte = etNaissance.getText().toString().trim();
        String destinationTexte = etDestination.getText().toString().trim();

        // 8) Ken champ fergh => naffichiw message (Toast) w nwa9fou
        if (nom.isEmpty() || naissanceTexte.isEmpty() || destinationTexte.isEmpty()) {
            Toast.makeText(this, R.string.erreur_champs, Toast.LENGTH_SHORT).show();
            return;
        }

        // 9) Nbadlou el texte l nombre (int)
        int naissance = Integer.parseInt(naissanceTexte);
        int destination = Integer.parseInt(destinationTexte);

        // 10) Nekhdhou el RadioButton elli mcochi w na9raw el texte mte3ou
        RadioButton rbChoisi = findViewById(rgMotif.getCheckedRadioButtonId());
        String motif = rbChoisi.getText().toString();

        // 11) Intent EXPLICITE : men MainActivity lel VerificationActivity
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // 12) N7otou les donnees fel Intent b les cles mta3 la classe Cles (prefixe 2160)
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);

        // 13) Nlanciw l'Activite 2
        startActivity(intent);
    }
}
