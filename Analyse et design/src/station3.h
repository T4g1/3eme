#ifndef __STATION3__
#define __STATION3__

// Actuateurs
#define FRAISE_MONTE        9  /* PB */     // Fait monter la fraiseuse
#define CAROUSSEL           16 /* PA */     // Active le caroussel
#define FRAISE_DESC         17 /* PA */     // Fait descendre la fraiseuse
#define SOL_H               18 /* PA */     // ?
#define SOL_V               19 /* PA */     // ?
#define EJECTER             20 /* PA */     // Ejecteur de piéces

// Capteurs
#define FRAISE_HAUT         9  /* PB */     // Fraiseuse en position haute
#define CAROUSSEL_STABLE    16 /* PA */     // Caroussel stable ou non
#define FRAISE_BAS          17 /* PA */     // Fraiseuse en position basse
#define PIECE_FRAISEE       18 /* PA */     // Indique si la piéce est fraisee ou non
#define PIECE_SLOT_1        21 /* PA */     // Piece dans le premier slot
#define PIECE_SLOT_2        22 /* PA */     // Piece dans le second slot
#define PIECE_SLOT_3        23 /* PA */     // Piece dans le troisiem slot

#endif