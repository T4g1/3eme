#ifndef __STATION2__
#define __STATION2__

#include<piod_qnx_v2.2.h>
#include<appio.h>

#include "BitManager.h"     /* Manipulation et interrogation des station via des entiers */
#include "IOManager.h"      /* Manipulation de bit sur des variables entiére */

// Actuateurs
#define ASC_MONTE       9  /* PB */     // Fait monter l'ascenseur
#define PP              16 /* PA */     // Active le pousseur de piéce
#define ASC_DESCEND     17 /* PA */     // Fait descendre l'ascenseur
#define COUSSIN_AIR     18 /* PA */     // Active le coussin d'air

// Capteurs
#deifne ASC_HAUT        9  /* PB */     // Ascenseur en position haute
#define PP_RENTRE       16 /* PA */     // Pousseur en position initiale
#define ASC_BAS         17 /* PA */     // Ascenseur en position basse
#define PIECE           20 /* PA */     // Indique si la piéce est présente
#define PIECE_METAL     21 /* PA */     // Indique si la piéce est en métal ou non
#define BARRIERE        22 /* PA */     //
#define HAUTEUR_PIECE   23 /* PA */     // Hauteur de la piéce

void reinitialise();
void processPiece();
#endif