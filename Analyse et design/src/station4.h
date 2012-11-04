#ifndef __STATION4__
#define __STATION4__

// Actuateurs
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

#endif