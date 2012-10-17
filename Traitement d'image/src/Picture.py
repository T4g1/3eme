#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
import time
import Image, ImageQt, ImageFilter
from common import *
from GrayScaleStepFour import *
from StepSix import *
from Filtres import *
from EdgeThining import *


##
# Classe de manipulation d'image
#
class Picture():
    def __init__(self):
        self.image = 0
        self.data = []                  # Couleur de chaque pixel
        self.palette = None             # Palette de couleurs utilisée pour les images indexées
        
        # operation de l'etape 4
    
    # Simple seuillage
    def threshold(self, seuil):
        return threshold(self, seuil)
    
    # Multiple seuillage (3 seuils)
    def multiThreshold(self, seuil):
        return multiThreshold(self, seuil)
    
    # Applique l'erosion sur l'image
    def erosion(self):
        self.data = erosion(self.data, self.image.size[0], self.image.size[1])
        self.image.putdata(self.data)
    
    # Applique l'ammaincissement des contours
    def edgeThining(self):
        self.data = edgeThining(self.data, self.image.size[0], self.image.size[1])
        self.image.putdata(self.data)
    
    # Applique une dilatation sur l'image
    def dilatation(self):
        self.data = dilatation(self.data, self.image.size[0], self.image.size[1])
        self.image.putdata(self.data)
    
    # Applique une ouverture sur l'image
    def ouverture(self):
        self.data = ouverture(self.data, self.image.size[0], self.image.size[1])
        self.image.putdata(self.data)
    
    # Applique une fermeture sur l'image
    def fermeture(self):
        self.data = fermeture(self.data, self.image.size[0], self.image.size[1])
        self.image.putdata(self.data)
    
    # Applique le filtre demandé
    def filtre(self, filtre):
        w = self.image.size[0]
        h = self.image.size[1]
        
        if filtre == "median":
            self.data = FiltreMedian(self.data, self.image.size[0], self.image.size[1])
            self.image.putdata(self.data)
        elif filtre == "sobel" or filtre == "prewitt":
            c = 2
            if filtre == "prewitt":
                c = 1
            
            self.data = convolutionXY(
                    self.data, w, h,
                    [-1, 0, 1, -c, 0, c, -1, 0, 1],
                    [-1, -c, -1, 0, 0, 0, 1, c, 1], 2+c)
            self.image.putdata(self.data)
        elif filtre == "kirsh":
            self.data = kirsh(self.data, w, h)
            self.image.putdata(self.data)
        elif filtre == "roberts":
            self.data = roberts(self.data, w, h)
            self.image.putdata(self.data)
        elif filtre == "laplacien":
            self.data = laplace(self.data, w, h)
            self.image.putdata(self.data)
        else:
            for kernel in l_filtres[filtre]:
                self.image = self.image.filter(kernel)
    
    # Ouvre une image
    def open(self, filename):
        print "Ouverture de l'image ..."
        self.setImage(Image.open(filename))
        
        print "Mode :", self.image.mode
    
    # Egalise l'histogramme de l'image
    def egalise(self):
        histogramme = self.getHistogramme()
        
        ratio = 255.0 / (self.image.size[0] * self.image.size[1])
        print "Ratio :", ratio

        # Calcule l'histogramme cumulé
        cumul = histogrammeCumule(histogramme)

        # Retro projection de l'histogramme sur l'image
        for i in range(len(self.data)):
            self.data[i] = int(cumul[self.data[i]] * ratio)
        
        self.image.putdata(self.data)
    
    # Extraction de l'image
    def extract(self, factor):
        debut = time.time()
        # Vérification des paramétres
        if factor < 1.0 or factor > 5.0:
            return
        
        # Operation interdite sur des image en couleur indexée
        if self.image == 0 or self.image.mode == "P":
            return 0
        
        # Calcul des dimensions
        w = self.image.size[0]
        h = self.image.size[1]
        newW = int(w / factor)
        newH = int(h / factor)
        print "Nouvelles dimension :", newW, newH
        
        data = [0] * newW * newH
        
        # Calcule le nombre de pixel pris pour la moyenne de chaque pixel
        intervalValueX = calcInterval(w, newW)
        intervalValueY = calcInterval(h, newH)
        print sum(intervalValueX[:0])
        fin = time.time()
        print "Generation intervale :", fin - debut
        
        imageW = self.image.size[0]
        
        # Pour chaque pixel de la nouvelle image
        for y in range(newH):
            for x in range(newW):
                nbElem = (intervalValueY[y] + intervalValueX[x])
                
                if self.image.mode == "L":
                    somme = 0
                else:
                    somme = (0, 0, 0)
                
                for yi in range(intervalValueY[y]):
                    for xi in range(intervalValueX[x]):
                        realX = sum(intervalValueX[:x]) + xi
                        realY = sum(intervalValueY[:y]) + yi
                        
                        if self.image.mode == "L":
                            color = self.data[(realY * imageW) + realX]
                            somme += color
                        else:
                            color = self.data[(realY * imageW) + realX]
                            somme = (sum(t) for t in zip(somme, color))
                
                if self.image.mode == "L":
                    moyenne = somme / nbElem
                else:
                    moyenne = tuple([t / nbElem for t in somme])
                
                data[(y * newH) + x] = moyenne
        
        print "Nombre de pixel voulu :", newW*newH
        print "Nombre de pixel obtenus :", len(data)
        
        # Création de la nouvelle image
        image = Image.new(self.image.mode, (newW, newH))
        image.putdata(data)
        self.setImage(image)
        
        fin = time.time()
        print "Temps total:", fin - debut
    
    # Expansion de l'image
    def expand(self, factor):
        debut = time.time()
        # Vérification des paramétres
        if factor < 1.0 or factor > 5.0:
            return
        
        # Operation interdite sur des image en couleur indexée
        if self.image == 0 or self.image.mode == "P":
            return 0
        
        # Calcul des dimensions
        w = self.image.size[0]
        h = self.image.size[1]
        newW = int(w * factor)
        newH = int(h * factor)
        print "Nouvelles dimension :", newW, newH
        
        data = [0] * newW * newH
        
        # Calcule le nombre de pixel ajouté par inter-pixel disponnible
        intervalValueX = calcInterval(newW, (w - 1))
        intervalValueY = calcInterval(newH, (w - 1))
        
        fin = time.time()
        print "Generation intervale :", fin - debut
        
        # Parcourt de l'image
        i = 0
        for y in range(h-1):
            # Pour chaque pixel ajouté
            for yi in range(intervalValueY[y]):
                # Ecart par rapport a ce pixel (en pourcent)
                ecartY = (1.0 / intervalValueY[y]) * yi
                subEcartY = 1 - ecartY
                        
                for x in range(w-1):
                    # Pour chaque pixel ajouté
                    for xi in range(intervalValueX[x]):
                        # Ecart par rapport a ce pixel (en pourcent)
                        ecartX = (1.0 / intervalValueX[x]) * xi
                
                        # Interpolation bilinéaire
                        if self.image.mode == "L":
                            color = (1-ecartX) * (subEcartY * self.data[(y * w) + x] +\
                                                  ecartY * self.data[((y+1) * w) + x]) +\
                                    ecartX * (subEcartY * self.data[(y * w) + (x+1)] +\
                                              ecartY * self.data[((y+1) * w) + (x+1)])
                            data[i] = color
                        else:
                            color = [0, 0, 0]
                            for j in range(3):
                                color[j] = int((1-ecartX) * (subEcartY * self.data[(y * w) + x][j] +\
                                                         ecartY * self.data[((y+1) * w) + x][j]) +\
                                            ecartX * (subEcartY * self.data[(y * w) + (x+1)][j] +\
                                                      ecartY * self.data[((y+1) * w) + (x+1)][j]))
                            data[i] = (color[0], color[1], color[2])
                        i += 1
        
        print "Nombre de pixel voulu :", newW*newH
        print "Nombre de pixel obtenus :", len(data)
        
        # Création de la nouvelle image
        image = Image.new(self.image.mode, (newW, newH))
        image.putdata(data)
        self.setImage(image)
        
        fin = time.time()
        print "Temps total:", fin - debut
    
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
            
            # Enregistre les modifications
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
        if self.image.mode == "P":
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
    
    # Accede au tableau data
    def getData(self, x, y):
        return self.data[(y * self.image.size[0]) + x]
        
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
        if self.image.mode == "P":
            roi.putpalette(self.getPalette())
        
        return roi
    
    # Génére la palette si elle n'existe pas
    def getPalette(self):
        if self.image.mode != "P":
            print "Aucune palette disponnible pour ce mode"
            return None
        
        self.palette = self.image.getpalette()
        return self.palette
        
    # Donne l'histogramme de l'image
    def getHistogramme(self):
        if self.image.mode != "L":
            print "Aucun histogramme ne peut être généré pour ce mode de couleur"
            return []
        
        histogramme = [0] * 256
        
        # Parcourt les data
        for i in self.data:
            histogramme[i] += 1
        
        return histogramme