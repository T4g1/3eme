CC=qcc
CFLAGS=-DLNOT_REALENV
LDFLAGS=-l socket -Bstatic lib/libappio.a
EXEC=station1 station2
OBJ=network.o IOManager.o BitManager.o
OBJSTATION1=station1.o PROFIBUS_common.o $(OBJ)
OBJSTATION2=station2.o common.o $(OBJ)
OBJSTATION3=station3.o common.o $(OBJ)
OBJSTATION4=station4.o common.o $(OBJ)

all: $(EXEC)

station1: $(OBJSTATION1)
	$(CC) -o station1 $(OBJSTATION1) $(LDFLAGS)

station2: $(OBJSTATION2)
	$(CC) -o station2 $(OBJSTATION2) $(LDFLAGS)

station3: $(OBJSTATION3)
	$(CC) -o station2 $(OBJSTATION3) $(LDFLAGS)

station4: $(OBJSTATION4)
	$(CC) -o station4 $(OBJSTATION4) $(LDFLAGS)


station1.o: station1.c station1.h common.h network.h
	$(CC) -c station1.c $(CFLAGS)

station2.o: station2.c station2.h common.h network.h
	$(CC) -c station2.c $(CFLAGS)

station3.o: station3.c station3.h common.h network.h
	$(CC) -c station3.c $(CFLAGS)

station4.o: station4.c station4.h common.h network.h
	$(CC) -c station4.c $(CFLAGS)


PROFIBUS_common.o: common.c BitManager.h IOManager.h
	$(CC) -c common.c -o PROFIBUS_common.o -DPROFIBUS $(CFLAGS)

common.o: common.c
	$(CC) -c common.c $(CFLAGS)

network.o: network.c
	$(CC) -c network.c $(CFLAGS)

BitManager.o: BitManager.c
	$(CC) -c BitManager.c $(CFLAGS)

IOManager.o: IOManager.c
	$(CC) -c IOManager.c $(CFLAGS)

clean:
	rm -rf *.o

mrproper: clean
	rm -rf $(EXEC)