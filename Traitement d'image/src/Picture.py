#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
import Image, ImageQt


##
# Classe de manipulation d'image
#
class Picture():
    def __init__(self):
        self.image = 0
        self.data = []                  # Couleur de chaque pixel
        self.palette = None             # Palette de couleurs utilisée pour les images indexées
    
    # Ouvre une image
    def open(self, filename):
        print "Ouverture de l'image ..."
        self.setImage(Image.open(filename))
        
        print "Mode :", self.image.mode
    
    # Donne une image
    def setImage(self, image):
        self.image = image
        self.data = list(self.image.getdata())
    
    # Modifie une couleur de la palette
    def setPaletteColor(self, pos, newColor):
        # Vérifie les absices
        if pos[0] < 0:
            pos[0] = 0
        elif pos[0] >= self.image.size[0]:
            pos[0] = self.image.size[0] - 1
        
        # Verifie les ordonnées
        if pos[1] < 0:
            pos[1] = 0
        elif pos[1] >= self.image.size[1]:
            pos[1] = self.image.size[1] - 1
        
        # Niveau de gris
        if self.image.mode == "L":
            # Vérification des paramétres
            if type(newColor) != type(0):
                print "Erreur: Color doit être en niveau de gris !", newColor
                return
            
            if newColor < 0:
                newColor = 0
            elif newColor > 255:
                newColor = 255
            
            # Récupére l'ancienne couleur
            oldColor = self.image.getpixel(pos)
            
            # Remplace la couleur
            for index, value in enumerate(self.data):
                if value == oldColor:
                    self.data[index] = newColor
        
            # Recrée l'image
            self.image.putdata(self.data)
        # Couleurs indexée
        elif self.image.mode == "P":
            # Vérification des paramétres
            if type(newColor) != type((0,0)):
                print "Erreur: Color doit être en RGB !", newColor
                return
            
            for index, value in enumerate(newColor):
                if value < 0:
                    newColor[index] = 0
                elif value > 255:
                    newColor[index] = 255
            
            # Récupére la palette
            if self.palette == None:
                self.getPalette()
            
            # Récupére la position de la couleur
            index = self.image.getpixel(pos) * 3
            
            self.palette[index] = newColor[0]
            self.palette[index + 1] = newColor[1]
            self.palette[index + 2] = newColor[2]
            
            #? Enregistre les modifications
            self.image.putpalette(self.palette)
    
    # Modifie la taille de l'image
    def setSize(self, w, h):
        if w <= 0 or h <= 0 or self.image == 0:
            return 0
        
        # Récupére les data de la nouvelle image
        data = []
        
        quantity = w    # Nombre de pixel pris par ligne
        reste = 0       # Pixel a créer par ligne
        
        if quantity >= self.getSize()[0]:
            reste = quantity - self.getSize()[0]
            quantity = self.getSize()[0]
        
        # Pour chaque ligne
        for y in range(h):
            first = (y * self.getSize()[0])
            
            data.extend(self.data[first:first + quantity])
            data.extend([data[-1]] * reste)
        
        # Création de la nouvelle image
        image = Image.new(self.image.mode, (w, h))
        image.putdata(data)
        image.putpalette(self.getPalette())
        self.setImage(image)
    
    # Donne une image Qt depuis une image PIL
    def getQtImage(self):
        # Bug dans PIL: conversion d'une image indexée/grayscale vers QImage -> image blanche
        # On convrtit donc l'image en RGB pour contourner le probléme
        self.QtImage = ImageQt.ImageQt(self.image.convert("RGB"))
        return QtGui.QImage(self.QtImage)
    
    # Donne un pixmap affichable dans Qt
    def getPixmap(self):
        self.pixmap = QtGui.QPixmap.fromImage(self.getQtImage())
        return self.pixmap
    
    # Donne les dimensions de l'image
    def getSize(self):
        if self.image == 0:
            return (0, 0)
        
        return self.image.size
        
    # Donne le pixel voulu
    def getPixel(self, x, y):
        if self.image == 0:
            return (0, 0, 0)
        
        # Couleurs indexée
        if self.image.mode == "P":
            if self.palette == None:
                self.getPalette()
            
            index = self.image.getpixel((x, y)) * 3
            r = self.palette[index]
            g = self.palette[index + 1]
            b = self.palette[index + 2]
            
            return (r, g, b)
        
        return self.image.getpixel((x, y))
        
    # Donne le ROI définit par les deux points donné
    def getROI(self, a, b):
        # Taille de la ROI
        w = abs(a[0] - b[0])
        h = abs(a[1] - b[1])
        print "Taille de la ROI :", (w, h)
        
        if w == 0 or h == 0:
            return 0
        
        # X coordonée
        if a[0] <= b[0]:
            x1 = a[0]
            x2 = b[0]
        else:
            x1 = b[0]
            x2 = a[0]
        
        # Y coordonée
        if a[1] <= b[1]:
            y1 = a[1]
            y2 = b[1]
        else:
            y1 = b[1]
            y2 = a[1]
            
        # Récupére les données de l'image chargée
        if self.data == []:
            self.data = list(self.image.getdata())
        
        # Extrait les donnée de la ROI depuis les données de l'image chargée
        data = []
        for y in range(y1, y2):
            for x in range(x1, x2):
                data.append(self.data[(y * self.image.size[0]) + x])
        
        # Crée la ROI
        roi = Image.new(self.image.mode, (w, h))
        roi.putdata(data)
        
        return roi
    
    # Génére la palette si elle n'existe pas
    def getPalette(self):
        if self.image.mode != "P":
            print "Aucune palette disponnible pour ce mode"
            return None
        
        self.palette = self.image.getpalette()
        return self.palette
        