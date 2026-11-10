package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activite 2 : Le Scanner de Paradoxe (yaffichi el passeport mta3 el voyageur).
 */
public class VerificationActivity extends AppCompatActivity {

    // 1) Les views mta3 el layout
    TextView tvNom, tvNaissance, tvDestination, tvAge, tvMotif, tvAlerte;
    Button btnApprouver, btnRefuser;

    // 2) Esm el voyageur : n7ajtou bih ba3d bech nab3athouh lel Activite 3
    String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // 3) N7otou el layout mta3 el passeport
        setContentView(R.layout.activity_verification);
        // Code genere automatiquement par Android Studio (EdgeToEdge) :
        // y5alli el contenu ma yet7atech ta7t el barre mta3 fou9 w louta
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4) findViewById l kol view
        tvNom = findViewById(R.id.tv_nom_ar);
        tvNaissance = findViewById(R.id.tv_naissance_ar);
        tvDestination = findViewById(R.id.tv_destination_ar);
        tvAge = findViewById(R.id.tv_age_ar);
        tvMotif = findViewById(R.id.tv_motif_ar);
        tvAlerte = findViewById(R.id.tv_alerte_ar);
        btnApprouver = findViewById(R.id.btn_approuver_ar);
        btnRefuser = findViewById(R.id.btn_refuser_ar);

        // 5) Njibou l'Intent elli jebna men MainActivity w na9raw les donnees
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intent.getIntExtra(Cles.cleNaissance(Cles.MATRICULE), 0);
        int destination = intent.getIntExtra(Cles.cleDestination(Cles.MATRICULE), 0);
        String motif = intent.getStringExtra(Cles.cleMotif(Cles.MATRICULE));

        // 6) N7asbou el 3omr el temporel = 3am el destination - 3am el wil3da
        int ageTemporel = destination - naissance;

        // 7) Naffichiw el resume fel passeport
        tvNom.setText("Nom : " + nom);
        tvNaissance.setText("Ne(e) en : " + naissance);
        tvDestination.setText("Destination : " + destination);
        tvAge.setText("Age temporel : " + ageTemporel + " ans");
        tvMotif.setText("Motif : " + motif);

        // 8) CONTRAINTE : ken el motif "Modification du passe" W el difference nombre premier
        //    => bouton Approuver ywalli desactive (disabled) men awel ma t7ell l'activite
        String motifModification = getString(R.string.motif_modification);
        if (motif.equals(motifModification) && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            tvAlerte.setVisibility(View.VISIBLE); // 9) Naffichiw message d'alerte
        }

        // 10) Bouton Approuver => verdict = true
        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(true);
            }
        });

        // 11) Bouton Refuser => verdict = false
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(false);
            }
        });
    }

    // 12) Intent EXPLICITE lel ResultActivity : yab3ath el verdict (boolean) W esm el voyageur
    private void envoyerVerdict(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), verdict);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }

    // 13) Methode statique : traja3 true ken n nombre premier
    //     (nombre premier = akber men 1 w ma yt9assamch ken 3la 1 w 3la rou7ou)
    public static boolean estPremier(int n) {
        // 14) 0, 1 w les nombres negatifs moch premiers
        if (n < 2) {
            return false;
        }
        // 15) Njarbou kol diviseur men 2 lin n-1
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false; // 16) l9ina diviseur => moch premier
            }
        }
        return true; // 17) ma l9ina 7atta diviseur => premier
    }
}
