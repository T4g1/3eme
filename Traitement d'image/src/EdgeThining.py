#!/usr/bin/python -d
# -*- coding: utf-8 -*-

import Image, ImageQt, ImageFilter
from math import exp, sqrt
import numpy

##
# Amaincit les contours
#
def edgeThining(data, w, h):
    todelete = []
    
    for x in range(1, w-1):
        for y in range(1, h-1):
            voisin = [
                ((y-1) * w) + x,            # P2
                ((y-1) * w) + (x+1),
                (y * w) + (x+1),
                ((y+1) * w) + (x+1),
                ((y+1) * w) + x,
                ((y+1) * w) + (x-1),
                (y * w) + (x-1),
                ((y-1) * w) + (x-1),        # P9
            ]
            
            s = 0
            for i in range(len(voisin)-1):
                if data[voisin[i]] == 0 and data[voisin[i]] != data[voisin[i+1]]:
                    s += 1
                
            n = 0
            for i in voisin:
                if data[i] > 0:
                    n += 1
            
            if data[(y * w) + x] > 0 and s == 1 and n >= 2 and n <= 6 and data[voisin[0]] * data[voisin[2]] * data[voisin[4]] == 0 and data[voisin[2]] * data[voisin[4]] * data[voisin[6]] == 0 and data[voisin[5]] > 0:
                todelete.append((y * w) + x)
    
    for i in todelete:
        data[i] = 0
    
    todelete = []
        
    for x in range(1, w-1):
        for y in range(1, h-1):
            voisin = [
                ((y-1) * w) + x,            # P2
                ((y-1) * w) + (x+1),
                (y * w) + (x+1),
                ((y+1) * w) + (x+1),
                ((y+1) * w) + x,
                ((y+1) * w) + (x-1),
                (y * w) + (x-1),
                ((y-1) * w) + (x-1),        # P9
            ]
            
            s = 0
            for i in range(len(voisin)-1):
                if data[voisin[i]] == 0 and data[voisin[i]] != data[voisin[i+1]]:
                    s += 1
                
            n = 0
            for i in voisin:
                if data[i] > 0:
                    n += 1
            
            if data[(y * w) + x] > 0 and s == 1 and n >= 2 and n <= 6 and data[voisin[0]] * data[voisin[2]] * data[voisin[6]] == 0 and data[voisin[0]] * data[voisin[4]] * data[voisin[6]] == 0 and data[voisin[5]] > 0:
                todelete.append((y * w) + x)
    
    for i in todelete:
        data[i] = 0
    
    return data