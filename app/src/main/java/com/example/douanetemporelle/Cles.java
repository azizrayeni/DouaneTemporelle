package com.example.douanetemporelle;

/**
 * 1) Classe Cles : hne nebniw les cles mta3 putExtra.
 * 2) El cles moch maktoubin "en dur" : kol cle tetbna b methode statique
 *    te5ou el matricule (4 ar9am lekhrin mta3 carte etudiant) en parametre.
 *    Mithel : cleNom("2160") traja3 "2160_NOM_VOYAGEUR".
 */
public class Cles {

    // 3) El matricule mta3na (4 ar9am lekhrin mta3 el carte etudiant)
    public static final String MATRICULE = "2160";

    // 4) Methode statique principale : tzid el matricule 9bal esm el cle
    public static String construireCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }

    // 5) Cle mta3 esm el voyageur
    public static String cleNom(String matricule) {
        return construireCle(matricule, "NOM_VOYAGEUR");
    }

    // 6) Cle mta3 3am el wil3da
    public static String cleNaissance(String matricule) {
        return construireCle(matricule, "ANNEE_NAISSANCE");
    }

    // 7) Cle mta3 3am el destination
    public static String cleDestination(String matricule) {
        return construireCle(matricule, "ANNEE_DESTINATION");
    }

    // 8) Cle mta3 el motif mta3 el voyage
    public static String cleMotif(String matricule) {
        return construireCle(matricule, "MOTIF");
    }

    // 9) Cle mta3 el verdict (boolean : true = approuve, false = refuse)
    public static String cleVerdict(String matricule) {
        return construireCle(matricule, "VERDICT");
    }
}
