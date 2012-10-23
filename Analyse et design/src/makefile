CC=qcc
CFLAGS=-Bstatic libappio.a
LDFLAGS=
EXEC=station1 station2
OBJ=IOManager.o BitManager.o
OBJSTATION1=station1.o PROFIBUS_common.o $(OBJ)
OBJSTATION2=station2.o common.o $(OBJ)

all: $(EXEC)

station1: $(OBJSTATION1)
    echo Creation de $@
	$(CC) -o station1 $(OBJSTATION1) $(LDFLAGS)

station2: $(OBJSTATION2)
    echo Creation de $@
	$(CC) -o station2 $(OBJSTATION2) $(LDFLAGS)


station1.o: station1.c station1.h common.h
    echo Creation de $@
	$(CC) -o station1.o -c station1.c $(CFLAGS)

station2.o: station2.c station2.h common.h
    echo Creation de $@
	$(CC) -o station1.o -c station1.c $(CFLAGS)

    
PROFIBUS_common.o: common.c BitManager.h IOManager.h
    echo Creation de $@
	$(CC) -o common.o -c common.c -DPROFIBUS $(CFLAGS)

common.o: common.c BitManager.h IOManager.h
    echo Creation de $@
	$(CC) -o common.o -c common.c $(CFLAGS)


BitManager.o: BitManager.c
    echo Creation de $@
	$(CC) -o BitManager.o -c BitManager.c $(CFLAGS)

IOManager.o: IOManager.c
    echo Creation de $@
	$(CC) -o IOManager.o -c IOManager.c $(CFLAGS)

clean:
	rm -rf *.o

mrproper: clean
	rm -rf $(EXEC)