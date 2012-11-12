#ifndef __STATION4__
#define __STATION4__

// Actuateurs
#define POS_0               0  /* PC */     // Bit de positionnement 0
#define POS_1               1  /* PC */     // Bit de positionnement 1
#define POS_2               2  /* PC */     // Bit de positionnement 2
#define POS_3               3  /* PC */     // Bit de positionnement 3
#define START               4  /* PC */     // Permet d'effectuer le déplacement donné par
                                            // un front montant
#define AMPLIFICATEUR       6  /* PC */     // Amplificateur
#define REGULATEUR          7  /* PC */     // Regulateur
#define SORTIR_BRAS_H       8  /* PB */     // Fait sortir le bras horizontalement
#define RENTRER_BRAS_H      16 /* PA */     // Fait rentrer le bras horizontalement
#define BRAS_V              17 /* PA */     // Fait rentrer/sortie le bras verticalement
#define PINCE               18 /* PA */     // Active la pince

// Capteurs
#define BRAS_H_SORTIT       8  /* PB */     // Bras horizontal sortit
#define BRAS_V_SORTIT       9  /* PA */     // Bras vertical sortit
#define BRAS_H_RENTRE       15 /* PA */     // Bras horizontal rentre
#define BRAS_V_RENTRE       16 /* PA */     // Bras vertical rentre
#define READY               21 /* PA */     // ?
#define ALIM_ACTIVE         22 /* PA */     // ?
#define EN_POSITION         23 /* PA */     // ?

void deplacerBras(int position);

#endif