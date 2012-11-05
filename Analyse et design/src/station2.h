#ifndef __STATION2__
#define __STATION2__

// Actuateurs
#define ASC_MONTE       9  /* PB */     // Fait monter l'ascenseur
#define PP              16 /* PA */     // Active le pousseur de piece
#define ASC_DESCEND     17 /* PA */     // Fait descendre l'ascenseur
#define COUSSIN_AIR     18 /* PA */     // Active le coussin d'air

// Capteurs
#define ASC_HAUT        9  /* PB */     // Ascenseur en position haute
#define PP_RENTRE       16 /* PA */     // Pousseur en position initiale
#define ASC_BAS         17 /* PA */     // Ascenseur en position basse
#define PIECE           20 /* PA */     // Indique si la piéce est presente
#define PIECE_METAL     21 /* PA */     // Indique si la piéce est en metal ou non
#define BARRIERE        22 /* PA */     //
#define HAUTEUR_PIECE   23 /* PA */     // Hauteur de la piece

#endif