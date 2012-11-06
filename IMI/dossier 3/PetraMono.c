
#include <unistd.h>
#include <errno.h>
#include <fcntl.h>
#include <stdio.h>
#include <time.h>

/*Prototype de fonctions*/
int InitPetra(const char* File, int mode);
int ActiverPlongeur();
int PositionPlongeur(int pos);
int ActiverVide();
int ActiverConvoyeur1();
int ActiverConvoyeur2();
int ActiverArbre();
int ActiverGrappin();
int Tempo(int nb_sec, double nb_nano);
int chariotStable();
int ArmPosition();

#define FILE_PETRA_IN "/dev/capteursPETRA"
#define FILE_PETRA_OUT "/dev/actuateursPETRA"

struct	ACTUATEURS 
{
	unsigned CP : 2;
    unsigned C1 : 1;
    unsigned C2 : 1;
    unsigned PV : 1;
    unsigned PA : 1;
    unsigned AA : 1;
    unsigned GA : 1;
} ;

union
{
	struct ACTUATEURS act ;
	unsigned char byte ;
} u_act ;



struct 	CAPTEURS
{
	unsigned L1 : 1;
    unsigned L2 : 1;
    unsigned T  : 1; /* cabl H */
    unsigned S  : 1;
    unsigned CS : 1;
    unsigned AP : 1;
    unsigned PP : 1;
    unsigned DE : 1;
};

union
{
	struct CAPTEURS capt ;
	unsigned char byte ;
} u_capt ;

struct
{
    time_t tv_sec;
    long tv_nsec;
}timespec;

/*---------------------------------------------------
 * permet d'initialiser le petra (ouverture des fi-
 * chier) 
 * const char* : sp√©cifie le nom de fichier
 * int : le mode d'ouverture
 * retourne le descripteur du fichier
---------------------------------------------------*/
int InitPetra(const char* , int );

int fdPetraIn;
int fdPetraOut;
struct timespec tempo, remain;

int main()
{
	//int fdPetraIn, fdPetraOut;
	char c;
	int i = 0;
	int piecebonne = 0;
		
	/*mets tout les bite des actuateurs a† 0*/
	u_act.byte = 0x00;
	
	fdPetraIn = InitPetra(FILE_PETRA_IN, O_RDONLY);
	
	fdPetraOut = InitPetra(FILE_PETRA_OUT, O_WRONLY);
	
	read(fdPetraIn, &u_capt.byte, 1);
	while( u_capt.capt.DE == 0 ){
		piecebonne = 0;
		/*---Placer piece sur C1---*/
		read(fdPetraOut, &u_act.byte, 1);
		//Activer Plongeur
		u_act.act.PA = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		//vide d'air ‡ 1
		u_act.act.PV = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		//Remonte le Plongeur
		u_act.act.PA = 0x00;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		//Plongeur au dessus de C1
		u_act.act.CP= 1;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		//Attendre chariot Stable
		do{ read(fdPetraIn, &u_capt.byte, 1);}while(u_capt.capt.CS == 1); //Wait Chariot Stable
		//vide d'air ‡ 0
		u_act.act.PV = 0x00;
		write(fdPetraOut, &u_act.byte, 1);
		/*-------------------------*/
		/*---Deplacer jusqu'au bras---*/
		//Activer C1
		u_act.act.C1 = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 8 sec
		tempo.tv_sec = 8;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		/*----------------------------*/
		/*---Placer sur C2---*/
		//Activer Grappin
		u_act.act.GA = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Desactiver C1
		u_act.act.C1 = 0x00;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		//Activer arbre
		u_act.act.AA = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 1 sec
		tempo.tv_sec = 1;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		do{	read(fdPetraOut, &u_act.byte, 1);}while(u_capt.capt.AP);
		//Desactiver Grappin
		u_act.act.GA = 0x00;
		write(fdPetraOut, &u_act.byte, 1);
		/*-------------------*/
		//Activer C2
		u_act.act.C2 = 0x01;
		write(fdPetraOut, &u_act.byte, 1);
		//Tempo de 2 sec
		tempo.tv_sec = 2;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		/*attend que la piece arrive au capteur*/
		do{
		  read(fdPetraIn, &u_capt.byte, 1);
		}while(!u_capt.capt.L1);
		/*verifie que la piece est bonne*/
		do{
		  read(fdPetraIn, &u_capt.byte, 1);
		  if( u_capt.capt.L1 == 0 && u_capt.capt.L2 == 0)
		    piecebonne = 1;
		  
		}while( u_capt.capt.L1 == 1 && u_capt.capt.L2 == 0 );
		printf("piecce bonne: %d", piecebonne);
		if( !piecebonne){
			//Plongeur au dessus de C2
			u_act.act.CP= 3;
			write(fdPetraOut, &u_act.byte, 1);
			//Tempo de 1,5 sec
			tempo.tv_sec = 2;
			tempo.tv_nsec = 200000000;
			nanosleep(&tempo, &remain);
			//Desactiver C2
			u_act.act.C2 = 0x00;
			write(fdPetraOut, &u_act.byte, 1);
			//Attendre chariot Stable
			do{ read(fdPetraIn, &u_capt.byte, 1);}while(u_capt.capt.CS == 1); //Wait Chariot Stable
			//Activer Plongeur
			u_act.act.PA = 0x01;
			write(fdPetraOut, &u_act.byte, 1);
			//Tempo de 1 sec
			tempo.tv_sec = 1;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
			//vide d'air ‡ 1
			u_act.act.PV = 0x01;
			write(fdPetraOut, &u_act.byte, 1);
			//Desactiver Plongeur
			u_act.act.PA = 0x00;
			write(fdPetraOut, &u_act.byte, 1);
			//Tempo de 1 sec
			tempo.tv_sec = 1;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
			//Plongeur au dessus de bac de piece mauvaise
			u_act.act.CP= 2;
			write(fdPetraOut, &u_act.byte, 1);
			//Tempo de 1 sec
			tempo.tv_sec = 1;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
			do{ read(fdPetraIn, &u_capt.byte, 1);}while(u_capt.capt.CS == 1); //Wait Chariot Stable
			//Activer Plongeur
			u_act.act.PA = 0x01;
			write(fdPetraOut, &u_act.byte, 1);
			//Tempo de 1 sec
			tempo.tv_sec = 1;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
			//vide d'air ‡ 0
			u_act.act.PV = 0x00;
			write(fdPetraOut, &u_act.byte, 1);
			//Desactiver Plongeur
			u_act.act.PA = 0x00;
			write(fdPetraOut, &u_act.byte, 1);
			//Plongeur au dessus du dispenser
			u_act.act.CP= 0;
			write(fdPetraOut, &u_act.byte, 1);
			
			do{read(fdPetraIn, &u_capt.byte, 1);}while(u_capt.capt.CS == 1); //Wait Chariot Stable
			//Tempo de 5 sec
			tempo.tv_sec = 1;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
		}
		else{
			//Tempo de 5 sec
			tempo.tv_sec = 5;
			tempo.tv_nsec = 0;
			nanosleep(&tempo, &remain);
		}
		u_act.byte = 0x00;
		write ( fdPetraOut , &u_act.byte ,1 );
		//Tempo de 3 sec
		tempo.tv_sec = 3;
		tempo.tv_nsec = 0;
		nanosleep(&tempo, &remain);
		read(fdPetraIn, &u_capt.byte, 1);
	}
	
	u_act.byte = 0x00;
	write ( fdPetraOut , &u_act.byte ,1 );
	close ( fdPetraIn );
	close ( fdPetraOut );
		
}

int ActiverPlongeur(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.PA == 0x00)
		u_act.act.PA = 0x01;
	else
		u_act.act.PA = 0x00;
	u_act.act.PA = 0x01;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int PositionPlongeur(int pos){
	read(fdPetraOut, &u_act.byte, 1);
	switch(pos){
		case 1:
				u_act.act.CP = 1;
				break;
		case 2:
				u_act.act.CP = 2;
				break;
		case 3:
				u_act.act.CP= 3;
			    break;
		case 0:
				u_act.act.CP = 0;	
				break;
		}
		u_act.act.CP= 3;
		write(fdPetraOut, &u_act.byte, 1);
		chariotStable();
		return 0;	
}

int ActiverVide(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.PV == 0x00)
		u_act.act.PV = 0x01;
	else
		u_act.act.PV = 0x00;
	u_act.act.PV = 0x01;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int ActiverConvoyeur1(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.C1 == 0x00)
		u_act.act.C1 = 0x01;
	else
		u_act.act.C1 = 0x00;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int ActiverConvoyeur2(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.C2 == 0x00)
		u_act.act.C2 = 0x01;
	else
		u_act.act.C2 = 0x00;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int InitPetra(const char* File, int mode){
	int descriptor;
	
	descriptor = open(File, mode);
	if(descriptor ==-1)
	{
		perror("Err. ouvertue petra out");
		return -1;
	}
	write(fdPetraOut, &u_act.byte, 1);
	return descriptor;
}

int ActiverArbre(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.AA == 0x00)
		u_act.act.AA = 0x01;
	else
		u_act.act.AA = 0x00;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int ActiverGrappin(){
	read(fdPetraOut, &u_act.byte, 1);
	if(u_act.act.GA == 0x00)
		u_act.act.GA = 0x01;
	else
		u_act.act.GA = 0x00;
	write(fdPetraOut, &u_act.byte, 1);
	return 0;
}

int chariotStable(){
	Tempo(1,0);
	do{
		Tempo(0,0.5); 
		read(fdPetraOut, &u_capt.byte, 1);
	}while(u_capt.capt.CS == 1);
	
	return 0;
}

int ArmPosition(){
	Tempo(1,0);
	do{
		Tempo(0,0.5); 
		read(fdPetraOut, &u_act.byte, 1);
	}while(u_capt.capt.AP);
	
	return 0;
	
}

int Tempo(int nb_sec, double nb_nano){
    tempo.tv_sec = nb_sec;
    tempo.tv_nsec = nb_nano;
    /*en cas d'interruption on sauvegarde le temps restant*/
    remain.tv_sec = 0;
    remain.tv_nsec = 0;
    
    nanosleep(&tempo, &remain);
}

void DisplayCapteurs(){
    
	printf("*----------Capteurs----------*\n");
	printf("L1: %d\n", u_capt.capt.L1);
	printf("L2: %d\n",u_capt.capt.L2);
	printf("T: %d\n",u_capt.capt.T);
	printf("S: %d\n",u_capt.capt.S);
	printf("CS: %d\n",u_capt.capt.CS);
	printf("AP: %d\n",u_capt.capt.AP);
	printf("PP: %d\n",u_capt.capt.PP);
	printf("DE: %d\n",u_capt.capt.DE);
	printf("*-------------------------------*\n");	
}