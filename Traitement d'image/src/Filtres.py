#!/usr/bin/python -d
# -*- coding: utf-8 -*-

import Image, ImageQt, ImageFilter
from math import exp, sqrt
import numpy

# Taille des filtres
size = 5

fenetre = [-1, 0, 1]

# Donne le noyau de convolution de gauss
def gaussian_grid(size=5):
    m = size / 2
    n = m + 1

    x, y = numpy.mgrid[-m:n,-m:n]

    fac = numpy.exp(m**2)
    g = fac * numpy.exp(-0.5 * (x ** 2 + y ** 2))

    return numpy.reshape(g.round().astype(int), -1)

# Liste des filtres
l_filtres = {
        u"moyen": [ImageFilter.Kernel((3, 3), [1] * 9, 9)],
        u"gaussien": [ImageFilter.Kernel((size, size), gaussian_grid(size))],
}

# Filtre de Laplace
def laplace(data, w, h):
    newData = [0] * w * h

    for y in range(1, h - 2):
        for x in range(1, w - 2):
            line1 = ((y-1) * w) + x
            line2 = (y * w) + x
            line3 = ((y+1) * w) + x
            pixel = (                         data[line1] +\
                        data[line2 - 1] - 4 * data[line2] + data[line2 + 1] +\
                                              data[line3])
            if pixel < 0:
                pixel = 0
            elif pixel > 255:
                pixel = 255

            newData[(y * w) + x] = pixel

    return newData

# Filtre de Kirsh
def kirsh(data, w, h):
    newData = [0] * w * h

    for y in range(1, h - 2):
        for x in range(1, w - 2):
            line1 = ((y-1) * w) + x
            line2 = (y * w) + x
            line3 = ((y+1) * w) + x
            a = (- data[line1 - 1] + data[line1 + 1]\
                 - data[line2 - 1] + data[line2 + 1]\
                 - data[line3 - 1] + data[line3 + 1]) / 3.0
            b = (- data[line1 - 1] - data[line1] - data[line1 + 1]\
                 + data[line3 - 1] + data[line3] + data[line3 + 1]) / 3.0
            c = (  data[line1]     + data[line1 + 1]\
                 - data[line2 - 1] + data[line2 + 1]\
                 - data[line3 - 1] - data[line3]) / 3.0
            d = (- data[line1]     - data[line1 + 1]\
                 + data[line2 - 1] - data[line2 + 1]\
                 + data[line3 - 1] + data[line3]) / 3.0

            pixel = int(sqrt((a * a) + (b * b) + (c * c) + (d * d)))
            if pixel < 0:
                pixel = 0
            elif pixel > 255:
                pixel = 255

            newData[(y * w) + x] = pixel

    return newData

# Filtre de Roberts
def roberts(data, w, h):
    newData = [0] * w * h

    for y in range(1, h - 2):
        for x in range(1, w - 2):
            line1 = (y * w) + x
            line2 = ((y+1) * w) + x
            
            a = data[line1 + 1] - data[line2]
            b = data[line1]     - data[line2 + 1]
            
            newData[line1] = max(abs(a), abs(b))

    return newData

##
# Pour chaque pixel, prends les valeurs de pixel voisin et remplace le pixel
# par la valeur médianne des valeurs récupérée (on prends les valeurs par ordre
# croissant et on garde celle du milieu)
#
def FiltreMedian(data, w, h):
    newData = [0] * w * h
    
    # Parcourt de l'image
    for x in range(1, w - 1):
        for y in range(1, h - 1):
            l_value = []
            
            # Parcourt de la fenetre
            for yi in fenetre:
                dy = y + yi
                i = (dy * w) + x + fenetre[0]
                
                l_value = l_value + data[i:i + len(fenetre)]
            
            l_value.sort()
            
            # Prend la medianne
            med = len(l_value) / 2
            
            newData[(y * w) + x] = l_value[med]
    
    return newData