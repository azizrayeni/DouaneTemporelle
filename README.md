# Douane Temporelle (Time Travel Customs) - Mini Projet DS1

Programmation Mobile - 3eme BI FSEGT - Java / Android

## Les 3 activites
1. **MainActivity** : formulaire (nom, annee de naissance, annee de destination, motif via RadioGroup) + bouton "Inspecter le profil" (Intent explicite).
2. **VerificationActivity** : passeport (LinearLayout horizontal : ImageView poids 0.3 + RelativeLayout poids 0.7). Boutons Approuver / Refuser.
3. **ResultActivity** : fond vert (approuve) ou rouge (refuse), message personnalise, bouton "Nouveau voyageur" (FLAG_ACTIVITY_CLEAR_TOP).

## Contraintes respectees
- Cles putExtra prefixees par le matricule **2160**, construites par des methodes statiques dans `Cles.java`.
- IDs des vues avec les initiales **ar** (ex: `btn_inspecter_ar`).
- Motif "Modification du passé" + age temporel premier => bouton Approuver desactive.
- Code commente en arabe (arabizi avec les chiffres).

## Tests
| Naissance | Destination | Motif | Age | Approuver |
|---|---|---|---|---|
| 1990 | 2050 | Tourisme | 60 | actif |
| 2000 | 2023 | Modification du passé | 23 (premier) | desactive |
| 2000 | 2024 | Modification du passé | 24 | actif |
