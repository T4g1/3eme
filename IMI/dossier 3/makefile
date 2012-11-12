CC=qcc
CFLAGS=
LDFLAGS=-l socket
EXEC=petra
OBJ=petra.o common.o

all: $(EXEC)

petra: $(OBJ)
	$(CC) -o petra $(OBJ) $(LDFLAGS)

petra.o: petra.c common.h
	$(CC) -c petra.c $(CFLAGS)

common.o: common.c
	$(CC) -c common.c $(CFLAGS)

clean:
	rm -rf *.o

mrproper: clean
	rm -rf $(EXEC)