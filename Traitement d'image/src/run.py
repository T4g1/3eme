#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys, os, time
from PyQt4 import QtCore, QtGui
from layout.windowsLayout import Ui_MainWindow
from dialog.DialogModifTaille import DialogModifTaille
from dialog.DialogModifPalette import DialogModifPalette
from dialog.DialogExpansion import DialogExpansion
from dialog.DialogThresholdSimple import DialogThresholdSimple
from dialog.DialogThresholdMultiple import DialogThresholdMultiple
from dialog.DialogHistogramme import DialogHistogramme
from dialog.DialogFiltre import DialogFiltre
from dialog.DialogAutre import DialogAutre
import Picture
import Image, ImageQt
import time


##
# Classe principale
#
class MainWindows(QtGui.QMainWindow):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        
        self.dialogModifTaille = DialogModifTaille(self)
        self.dialogModifPalette = DialogModifPalette(self)
        self.dialogExpansion = DialogExpansion(self)
        self.dialogThresholdSimple = DialogThresholdSimple(self)
        self.dialogThresholdMultiple = DialogThresholdMultiple(self)
        self.dialogHistogramme = DialogHistogramme(self)
        self.dialogFiltre = DialogFiltre(self)
        self.dialogAutre = DialogAutre(self)
        # Fin du wrapper
        
        # Crée les scénes
        self.baseScene = QtGui.QGraphicsScene()
        self.resultScene = QtGui.QGraphicsScene()
        
        # Lie les scénes aux vues
        self.ui.baseView.setScene(self.baseScene)
        self.ui.resultView.setScene(self.resultScene)
        
        self.basePicture = Picture.Picture()
        self.resultPicture = Picture.Picture()
        self.apercu = Picture.Picture()
        
        # ROI
        self.roiCorner = []
        self.roiActivated = False
        
        # Color picker
        self.colorPickerActivated = False
        
        # Clic sur l'image
        self.ui.baseView.mousePressEvent = self.basePictureOnMousePress
        self.ui.baseView.mouseReleaseEvent = self.basePictureOnMouseRelease
        
        # Boutons pour modifications visible
        self.ui.modeROI.clicked.connect(self.setRoiMode)
        self.ui.modeNormal.clicked.connect(self.setNoMode)
        
        # Passer le resultat en image de base
        self.ui.toBasePicture.clicked.connect(self.switchResult2Base)
        self.ui.centralwidget.keyPressEvent = self.keyPressEvent
        
        # Menu
        self.ui.actionOuvrir.triggered.connect(self.promptImage)
        self.ui.actionQuitter.triggered.connect(QtGui.qApp.quit)
        
        self.ui.actionModifierTaille.triggered.connect(lambda: self.dialogModifTaille.show(self.basePicture.getSize()))
        self.ui.actionModifierPalette.triggered.connect(self.dialogModifPalette.show)
        self.ui.actionExpansion.triggered.connect(self.dialogExpansion.show)
        
        self.ui.actionSimple.triggered.connect(self.dialogThresholdSimple.show)
        self.ui.actionMultiple.triggered.connect(self.dialogThresholdMultiple.show)
        
        self.ui.actionAfficherHist.triggered.connect(self.dialogHistogramme.show)
        self.ui.actionEgaliserHist.triggered.connect(self.dialogHistogramme.egaliser)
        
        self.ui.actionMedian.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"median", 1))
        self.ui.actionMoyen.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"moyen", 1))
        self.ui.actionGaussien.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"gaussien", 1))
        self.ui.actionLaplacien.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"laplacien", 1))
        self.ui.actionKirsh.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"kirsh", 1))
        self.ui.actionSobel.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"sobel", 1))
        self.ui.actionPrewitt.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"prewitt", 1))
        self.ui.actionRoberts.triggered.connect(lambda: self.dialogFiltre.applyFilter(u"roberts", 1))
        
        self.ui.actionApplicationAvancee.triggered.connect(self.dialogFiltre.show)
        
        self.ui.actionErosion.triggered.connect(lambda: self.dialogAutre.applyFilter(u"Erosion", 1))
        self.ui.actionDilatation.triggered.connect(lambda: self.dialogAutre.applyFilter(u"Dilatation", 1))
        self.ui.actionOuverture.triggered.connect(lambda: self.dialogAutre.applyFilter(u"Ouverture", 1))
        self.ui.actionFermeture.triggered.connect(lambda: self.dialogAutre.applyFilter(u"Fermeture", 1))
        
        self.ui.actionAffiner.triggered.connect(self.applyAffinage)
        
        self.ui.actionApplicationAvanceeAutre.triggered.connect(self.dialogAutre.show)
        
        # Image ouverte des le depart
        self.openPicture("./picture/grayscale/lenna.bmp")
    
    # Applique l'affinnage
    def applyAffinage(self):
        self.resultPicture.setImage(self.basePicture.image.copy())
        
        oldData = []
        while oldData != self.resultPicture.data:
            oldData = list(self.resultPicture.data)
            self.resultPicture.edgeThining()
        
        self.showResultPicture()
        
    # Demande une image et la charge
    def promptImage(self):
        filename = QtGui.QFileDialog.getOpenFileName(
                self, u"Sélectionnez l'image source",
                u"./picture", "Images PNJ ou BMP (*.png *.bmp);; Images JPG (*.jpg)")
        if filename == "":
            return
        
        filename = str(filename).decode('utf-8')
        self.openPicture(filename)
    
    # Ouverture d'une image
    def openPicture(self, filename):
        self.basePicture.open(filename)
        self.generateApercu()
        
        # Affichage
        self.showBasePicture()
    
    # Génére un apercu en niveaux de gris
    def generateApercu(self):
        # Génére l'apercu
        apercuW = 100
        apercuH = 100
        
        w = self.basePicture.image.size[0]
        h = self.basePicture.image.size[1]
        
        data = [0] * apercuW * apercuH
        
        self.apercu.image = Image.new("L", (apercuW, apercuH))
        for x in range(apercuW):
            for y in range(apercuH):
                originX = (x * w) / apercuW
                originY = (y * h) / apercuH
                
                i = (y * apercuW) + x
                j = (originY * w) + originX
                data[i] = self.basePicture.data[j]
        
        self.apercu.image.putdata(data)
    
    # Affiche l'image de base
    def showBasePicture(self):
        self.baseScene.clear()
        self.baseScene.addPixmap(self.basePicture.getPixmap())
        self.baseScene.update()
        
        if self.basePicture.image.mode == "L":
            self.dialogHistogramme.showHistogrammeBase()
    
    # Affiche l'image résultat
    def showResultPicture(self):
        self.resultScene.clear()
        self.resultScene.addPixmap(self.resultPicture.getPixmap())
        self.resultScene.update()
        
        if self.basePicture.image.mode == "L":
            self.dialogHistogramme.showHistogrammeResult()
    
    # Passe l'image resultat en image de base
    def switchResult2Base(self):
        if self.resultPicture.image == 0:
            print "Pas encore d'image résultat ..."
            return
        
        self.basePicture.setImage(self.resultPicture.image.copy())
        self.generateApercu()
        
        # Affiche l'image
        self.showBasePicture()
    
    # Gestion des touches
    def keyPressEvent(self, event):
        #print "Touche :", event.key()
        
        # Touche windows
        if event.key() == QtCore.Qt.Key_Meta:
            self.switchResult2Base()
    
    # Désactive les mode particuliers
    def setNoMode(self):
        self.ui.baseView.setCursor(QtGui.QCursor(QtCore.Qt.ArrowCursor))
        self.roiActivated = False
        self.colorPickerActivated = True
        self.roiCorner = []
    
    # Active le mode prise de couleur
    def setColorPickerMode(self):
        self.setNoMode()
        self.ui.baseView.setCursor(QtGui.QCursor(QtCore.Qt.PointingHandCursor))
        self.colorPickerActivated = True
        
    # Active le mode ROI
    def setRoiMode(self):
        self.setNoMode()
        self.ui.baseView.setCursor(QtGui.QCursor(QtCore.Qt.CrossCursor))
        self.roiActivated = True
    
    # Clic enfoncé sur l'image source
    def basePictureOnMousePress(self, mouseEvent):
        x, y = self.getRealPos(mouseEvent)
        
        if self.roiActivated:
            self.onRoiClick(x, y)
        elif self.colorPickerActivated:
            color = self.basePicture.getPixel(x, y)
            self.dialogModifPalette.setPickedColor((x, y), color)
    
    # Clic enfoncé sur l'image source
    def basePictureOnMouseRelease(self, mouseEvent):
        x, y = self.getRealPos(mouseEvent)
        
        if self.roiActivated:
            self.onRoiClick(x, y)
    
    # Donne les coordonnées de l'event sur la scéne
    def getRealPos(self, mouseEvent):
        if self.basePicture.image == 0:
            return (0, 0)
        
        # Récupére les coordonées sur la scéne
        pos = self.ui.baseView.mapToScene(mouseEvent.pos())
        x = int(pos.x())
        y = int(pos.y())
        
        # Dépassement des X
        if x < 0:
            x = 0
        elif x > self.basePicture.image.size[0]:
            x = self.basePicture.image.size[0] - 1
        
        # Dépassement des Y
        if y < 0:
            y = 0
        elif y > self.basePicture.image.size[1]:
            y = self.basePicture.image.size[1] - 1
        
        return (x, y)
    
    # Clic pour le ROI
    def onRoiClick(self, x, y):
        if self.roiActivated:
            self.roiCorner.append((x, y))
            print "ROI coordinate", len(self.roiCorner), ":", (x, y)
            
            if len(self.roiCorner) >= 2:
                self.showROI()
                #self.setNoMode()
    
    # Affiche le ROI séléctionné
    def showROI(self):
        # Récupére la ROI
        roi = self.basePicture.getROI(self.roiCorner[0], self.roiCorner[1])
        self.roiCorner = []
        
        if roi == 0:
            return
        
        # Affiche l'image
        self.resultPicture.setImage(roi.copy())
        self.showResultPicture()


if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    windows = MainWindows()
    windows.show()
    sys.exit(app.exec_())