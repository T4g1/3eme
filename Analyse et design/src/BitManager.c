/**
 * Donne le bit demandé de la valeur donnée
 *
 * @param value         Valeur dont on veut extraire un bit
 * @param bit           Bit désiré
 *
 * @return              Valeur du bit demandé
 */
int getBit(int value, int bit)
{
    return (value >> bit) & 1;
}

/**
 * Modifie le bit demandé de la valeur donnée
 *
 * @param value         Pointeur de la variable dont
 *                      on veut modifier un bit
 * @param bit           Bit désiré
 * @param bitValue      Nouvelle valeur
 */
void setBit(int* value, int bit, int bitValue)
{
    int masked;
    int mask = 1 << bit;
    
    if(bitValue)
        *value |= mask;
    else
        *value &= ~mask;
}