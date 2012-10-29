/* Header file for the "ICPDAS pioD48" board resource manager 
	and the applications controlling it through DEVCTLs (device control messages) */
#include <devctl.h>
#define NULL 0
#define DEV_ENTRY "/dev/pio_d48"

#define pioD48_CODE 0x24

/* Get/Set pioD48 resource manager Input/Output selection ("control word") */
#define DEVCTL_GET_IO_SELECTION_0 __DIOF(_DCMD_MISC, pioD48_CODE + 0, unsigned char)
#define DEVCTL_SET_IO_SELECTION_0 __DIOT(_DCMD_MISC, pioD48_CODE + 1, unsigned char)

#define DEVCTL_GET_IO_SELECTION_1 __DIOF(_DCMD_MISC, pioD48_CODE + 8, unsigned char)
#define DEVCTL_SET_IO_SELECTION_1 __DIOT(_DCMD_MISC, pioD48_CODE + 9, unsigned char)

/* Get/Set pioD48 registers ( cat /dev/pci-pioD24  shows the current values ) */
#define DEVCTL_GET_PA_0 __DIOF(_DCMD_MISC, pioD48_CODE + 2, unsigned char)
#define DEVCTL_SET_PA_0 __DIOT(_DCMD_MISC, pioD48_CODE + 3, unsigned char)
#define DEVCTL_GET_PB_0 __DIOF(_DCMD_MISC, pioD48_CODE + 4, unsigned char)
#define DEVCTL_SET_PB_0 __DIOT(_DCMD_MISC, pioD48_CODE + 5, unsigned char)
#define DEVCTL_GET_PC_0 __DIOF(_DCMD_MISC, pioD48_CODE + 6, unsigned char)
#define DEVCTL_SET_PC_0 __DIOT(_DCMD_MISC, pioD48_CODE + 7, unsigned char)

#define DEVCTL_GET_PA_1 __DIOF(_DCMD_MISC, pioD48_CODE + 10, unsigned char)
#define DEVCTL_SET_PA_1 __DIOT(_DCMD_MISC, pioD48_CODE + 11, unsigned char)
#define DEVCTL_GET_PB_1 __DIOF(_DCMD_MISC, pioD48_CODE + 12, unsigned char)
#define DEVCTL_SET_PB_1 __DIOT(_DCMD_MISC, pioD48_CODE + 13, unsigned char)
#define DEVCTL_GET_PC_1 __DIOF(_DCMD_MISC, pioD48_CODE + 14, unsigned char)
#define DEVCTL_SET_PC_1 __DIOT(_DCMD_MISC, pioD48_CODE + 15, unsigned char)

#define READ_CAPTEUR __DIOF(_DCMD_MISC, pioD48_CODE + 16, unsigned int) 
#define WRITE_ACTUATEUR __DIOT(_DCMD_MISC, pioD48_CODE + 17, unsigned int)


