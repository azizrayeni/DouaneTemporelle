package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activite 3 : Le Verdict (fond vert ken approuve, rouge ken refuse).
 */
public class ResultActivity extends AppCompatActivity {

    // 1) Les views
    LinearLayout layoutFond;
    TextView tvMessage;
    Button btnNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // 2) N7otou el layout mta3 el verdict
        setContentView(R.layout.activity_result);
        // Code genere automatiquement par Android Studio (EdgeToEdge) :
        // y5alli el contenu ma yet7atech ta7t el barre mta3 fou9 w louta
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 3) findViewById
        layoutFond = findViewById(R.id.main);
        tvMessage = findViewById(R.id.tv_message_ar);
        btnNouveau = findViewById(R.id.btn_nouveau_ar);

        // 4) Na9raw el verdict (boolean) w el esm men l'Intent
        Intent intent = getIntent();
        boolean verdict = intent.getBooleanExtra(Cles.cleVerdict(Cles.MATRICULE), false);
        String nom = intent.getStringExtra(Cles.cleNom(Cles.MATRICULE));

        // 5) Ken approuve => fond vert + "Bon voyage [Nom]"
        //    Ken refuse   => fond rouge + "[Nom] est en etat d'arrestation temporelle"
        if (verdict) {
            layoutFond.setBackgroundResource(R.color.vert);
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            layoutFond.setBackgroundResource(R.color.rouge);
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // 6) Bouton "Nouveau voyageur" : nrja3ou lel MainActivity
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(ResultActivity.this, MainActivity.class);
                // 7) FLAG_ACTIVITY_CLEAR_TOP : ysakker les activites elli fou9 MainActivity
                //    (VerificationActivity w ResultActivity) => formulaire jdid fergh
                retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(retour);
                finish(); // 8) Nsakrou hedhi zeda
            }
        });
    }
}
