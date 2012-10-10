#!/usr/bin/python
# -*- coding: utf-8 -*-

import numpy
import math


# Repartit un nombre "count" selon "x" intervales
def calcInterval(count, x):
    l_interval = [0] * x
    
    i = 0
    while count:
        i = (i + 1) % x
        l_interval[i] += 1
        count -= 1
    
    return l_interval

# Calcule un histogramme cumulé
def histogrammeCumule(histogramme):
    # Calcule l'histogramme cumulé
    cumul = histogramme
    for i in range(len(histogramme) - 1):
        cumul[i + 1] += cumul[i]
    
    return cumul

# Applique un masque de convolution (3x3)
def convolutionXY(data, w, h, m1, m2, coef):
    src = data
    dst = [0] * w * h

    for i in range(1, h - 1):
        for j in range(1, w - 1):
            cpt = 0
            gx = 0
            gy = 0
        
            for x in range(-1, 2):
                for y in range(-1, 2):
                    gx = gx + m1[cpt] * src[(i + x) * w + j + y]
                    gy = gy + m2[cpt] * src[(i + x) * w + j + y]
                    
                    cpt += 1

            pt = abs(gx) + abs(gy)
            if pt < 0:
                pt = 0
            elif pt > 255:
                pt = 255

            dst[(i * w) + j] = int(pt);

    return dst