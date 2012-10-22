#!/usr/bin/python -d
# -*- coding: utf-8 -*-

import Image, ImageQt, ImageFilter
from math import exp, sqrt
import numpy


# Affinage de contours
def affiner(data, w, h):
    while 1:
        todelete = []
        
        for y in range(1, h - 2):
            for x in range(1, w - 2):
                #   p9 p2 p3
                #   p8 p1 p4
                #   p7 p6 p5

                voisinnage = [
                    data[(y * w) + x],            # P1
                    data[((y-1) * w) + x],        # P2
                    data[((y-1) * w) + (x+1)],    # P3
                    data[(y * w) + (x+1)],        # P4
                    data[((y+1) * w) + (x+1)],    # P5
                    data[((y+1) * w) + x],        # P6
                    data[((y+1) * w) + (x-1)],    # P7
                    data[(y * w) + (x-1)],        # P8
                    data[((y-1) * w) + (x-1)],    # P9
                ]
                
                # Si le pixel est déjà effacé on continue
                if voisinnage[0] == 0:
                    continue
                
                # Calcul de n = somme des pixel non effacé dans les voisins
                # et s = nombre de transitions blanc vers noir dans les voisins
                n = 0
                s = 0
                precedent = voisinnage[0]
                for voisin in voisinnage[1:]:
                    # Voisin de couleur
                    if voisin != 0:
                        n += 1
                        
                        # Transition de blanc vers noir ?
                        if precedent == 0:
                            s += 1
                    
                    precedent = voisin
                
                # Quel pixel supprimer
                if n > 1 and n < 7 and s < 2:
                    todelete.append((y * w) + x)
        
        # Si on a rien a modifier
        if todelete == []:
            break
        
        # Supprime les pixel
        for i in todelete:
            data[i] = 0
    
    return data