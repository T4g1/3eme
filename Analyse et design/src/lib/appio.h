/****************************************************************************}
'*                                                                          *}
'*  APPIO.H  Version 2.0  date :13/06/2000                                  *}
'*  Copyright (c) Applicom International. All rights reserved.              *}
'*                                                                          *}
'*      Definitions file for  applicomIO Windows applications               *}
'*                                                                          *}
'*               ******************************************                 *}
'*               *   WARNING   do not modify this file    *                 *}
'*               ******************************************                 *}
'*                                                                          *}
'****************************************************************************/

#define DWORD	unsigned long  /* unsigned 32 bits*/
#define WORD	unsigned short /* unsigned 16 bits */
#define BYTE	unsigned char  /* unsigned 8 bits */

#define BOOL	int
#define TRUE	1
#define FALSE	0
#define WINAPI
/* appio.dll functions declaration*/
BOOL WINAPI IO_Init( WORD wCard, short * pStatus );
BOOL WINAPI IO_Exit( WORD wCard, short * pStatus );

BOOL WINAPI IO_VerifAuto( BOOL bAuto);

WORD WINAPI IO_GetDigitalInput( WORD wCard, short * pStatus );

BOOL WINAPI IO_RefreshInput(WORD wCard, short * pStatus );
BOOL WINAPI IO_RefreshOutput(WORD wCard, short * pStatus );

BOOL WINAPI IO_VerifParamStatus(WORD wCard, WORD wEquip, short * pStatus );
BOOL WINAPI IO_VerifParamRead(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, WORD TYPE, short * pStatus);
BOOL WINAPI IO_VerifParamWrite(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, WORD TYPE, short * pStatus);


BOOL WINAPI IO_WriteQBit(WORD wCard, WORD wEquip, WORD Offsetbit, WORD Nb, BYTE * TabBit, short * pStatus );

BOOL WINAPI IO_WriteQByte(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, BYTE * TabByte, short * pStatus );

BOOL WINAPI IO_WriteQWord(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, WORD * TabWord, short * pStatus );
BOOL WINAPI IO_WriteQDWord(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, DWORD *  TabWord, short * pStatus );

BOOL WINAPI IO_ReadIBit(WORD wCard, WORD wEquip, WORD Offsetbit, WORD Nb, BYTE * TabBit, short * pStatus );
BOOL WINAPI IO_ReadIByte(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, BYTE * TabByte, short * pStatus );
BOOL WINAPI IO_ReadIWord(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, WORD * TabWord, short * pStatus );
BOOL WINAPI IO_ReadIDWord(WORD wCard, WORD wEquip, WORD Offset, WORD Nb, DWORD *  TabWord, short * pStatus );

BOOL WINAPI IO_GetEquipmentList( WORD wCard, WORD * pByNb, WORD * TabEquip, short * pStatus );
BOOL WINAPI IO_GetEquipmentInfo( WORD wCard, WORD wEquip, WORD * InputSize, WORD * OutputSize, short * pStatus );

BOOL WINAPI IO_SetWatchDog(WORD wCard, WORD TimeWD, short * pStatus );
BOOL WINAPI IO_SetReply(WORD wCard, WORD TimeReply, short * pStatus );
BOOL WINAPI IO_GetEquipmentStatus(WORD wCard, WORD wEquip, WORD * EquipmentStatus,  short * pStatus );
BOOL WINAPI IO_GetGlobalStatus(WORD wCard , short * GlobalStatus,  short * pStatus  );

BOOL WINAPI IO_ReadEquipmentOut(WORD wCard, WORD wEquip, WORD offset, WORD Nb, BYTE * TabByte, short * pStatus);

BOOL WINAPI IO_ReadDiag(WORD wCard ,WORD wEquip,  WORD * TabDiag , short * pStatus );
BOOL WINAPI IO_GetGlobalDiag(WORD wCard , WORD * GlobalDiag,  short * Status );

WORD WINAPI IO_GetStateReply(WORD NumBoard , short *  Status ) ;
WORD WINAPI IO_GetStateWatchDog(WORD NumBoard , short *  Status ) ;
