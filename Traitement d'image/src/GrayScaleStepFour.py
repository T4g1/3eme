#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
import time
import Image, ImageQt


# Applique un seuillage simple
def threshold(self, seuil):
    # Vérifie les paramétres
    if seuil < 0 or seuil > 255:
        return 0
    
    # Si l'image n'est pas en grayscale
    if self.image == 0 or self.image.mode != "L":
        return 0
    
    # Parcourt l'image
    for i in range(len(self.data)):
        if self.data[i] < seuil:
            self.data[i] = 0
        else:
            self.data[i] = 255
    
    # Reconstruit l'image
    self.image.putdata(self.data)

# Applique un multiple seuillage
def multiThreshold(self, l_seuil):
    l_seuil = sorted(l_seuil)
    
    # Vérifie les paramétres
    for seuil in l_seuil:
        if seuil < 0 or seuil > 255:
            return 0
    
    # Si l'image n'est pas en grayscale
    if self.image == 0 or self.image.mode != "L":
        return 0
    
    step = 255 / len(l_seuil)
    
    # Parcourt l'image
    for i in range(len(self.data)):
        for key, seuil in enumerate(l_seuil):
            if self.data[i] < seuil:
                self.data[i] = key * step
                break
        else:
            self.data[i] = 255
    
    # Reconstruit l'image
    self.image.putdata(self.data)