#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys, os, time
from PyQt4 import QtCore, QtGui
import Image, ImageQt
from layout.windowsLayout import Ui_MainWindow
from dialog.DialogModifTaille import DialogModifTaille
from dialog.DialogModifPalette import DialogModifPalette
import Picture


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
        # Fin du wrapper
        
        # Crée les scénes
        self.baseScene = QtGui.QGraphicsScene()
        self.resultScene = QtGui.QGraphicsScene()
        
        # Lie les scénes aux vues
        self.ui.baseView.setScene(self.baseScene)
        self.ui.resultView.setScene(self.resultScene)
        
        self.basePicture = Picture.Picture()
        self.resultPicture = Picture.Picture()
        
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
        self.ui.actionOuvrir.triggered.connect(self.openPicture)
        self.ui.actionQuitter.triggered.connect(QtGui.qApp.quit)
        
        self.ui.actionModifierTaille.triggered.connect(lambda: self.dialogModifTaille.show(self.basePicture.getSize()))
        self.ui.actionModifierPalette.triggered.connect(self.dialogModifPalette.show)
    
    # Ouverture d'une image
    def openPicture(self):
        filename = QtGui.QFileDialog.getOpenFileName(
                self, u"Sélectionnez l'image source",
                u"./picture", "Images PNJ ou BMP (*.png *.bmp)")
        if filename == "":
            return
        
        filename = str(filename).decode('utf-8')
        self.basePicture.open(filename)
        
        # Affichage
        self.showBasePicture()
    
    # Affiche l'image de base
    def showBasePicture(self):
        self.baseScene.clear()
        self.baseScene.addPixmap(self.basePicture.getPixmap())
        self.baseScene.update()
    
    # Affiche l'image résultat
    def showResultPicture(self):
        self.resultScene.clear()
        self.resultScene.addPixmap(self.resultPicture.getPixmap())
        self.resultScene.update()
    
    # Passe l'image resultat en image de base
    def switchResult2Base(self):
        if self.resultPicture.image == 0:
            print "Pas encore d'image résultat ..."
            return
        
        self.basePicture.setImage(self.resultPicture.image.copy())
        
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
            self.dialogModifPalette.setPickedColor(color)
    
    # Clic enfoncé sur l'image source
    def basePictureOnMouseRelease(self, mouseEvent):
        x, y = self.getRealPos(mouseEvent)
        
        if self.roiActivated:
            self.onRoiClick(x, y)
    
    # Donne les coordonnées de l'event sur la scéne
    def getRealPos(self, mouseEvent):
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