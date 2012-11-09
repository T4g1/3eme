CC=qcc
CFLAGS=
LDFLAGS=-l socket
EXEC=petra
OBJ=petra.o common.o

all: $(EXEC)

petra: $(OBJ)
	$(CC) -o petra $(OBJ) $(LDFLAGS)

common.o: common.c
	$(CC) -c common.c $(CFLAGS)

clean:
	rm -rf *.o

mrproper: clean
	rm -rf $(EXEC)