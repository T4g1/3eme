#!/usr/bin/python -d
# -*- coding: utf-8 -*-

import Image, ImageQt, ImageFilter
from math import exp


##
# Erosion d'une image
#
def erosion(data, w, h):
    temp = [0] * w * h
    
    # parcrout l'image
    for x in range(1, w-1):
        for y in range(1, h-1):
            # Remplace le pixel par le max dans son voissinage
            temp[(y * w) + x] = min([
                data[((y-1) * w) + (x-1)],
                data[((y-1) * w) + x],
                data[((y-1) * w) + (x+1)],
                data[(y * w) + (x-1)],
                data[(y * w) + x],
                data[(y * w) + (x+1)],
                data[((y+1) * w) + (x-1)],
                data[((y+1) * w) + x],
                data[((y+1) * w) + (x+1)]
            ])

    return temp

##
# Dilatation d'une image
#
def dilatation(data, w, h):
    temp = [0] * w * h
    
    # parcrout l'image
    for x in range(1, w-1):
        for y in range(1, h-1):
            # Remplace le pixel par le max dans son voissinage
            temp[(y * w) + x] = max([
                data[((y-1) * w) + (x-1)],
                data[((y-1) * w) + x],
                data[((y-1) * w) + (x+1)],
                data[(y * w) + (x-1)],
                data[(y * w) + x],
                data[(y * w) + (x+1)],
                data[((y+1) * w) + (x-1)],
                data[((y+1) * w) + x],
                data[((y+1) * w) + (x+1)]
            ])

    return temp

##
# Ouverture d'une image
# Inverse de la fermeture
#
def ouverture(data, w, h):
    data = erosion(data, w, h)
    return dilatation(data, w, h)

##
# Fermeture d'une image
# Ferme les trou, fissures, ...
#
def fermeture(data, w, h):
    data = dilatation(data, w, h)
    return erosion(data, w, h)
