#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys, os
import time
from PyQt4 import QtCore, QtGui
import Image
import ImageQt
from layout.windowsLayout import Ui_MainWindow
import Picture


##
# Classe principale
#
class MainWindows(QtGui.QMainWindow):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
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
        self.roiCorner = [];
        self.roiActivated = False
        
        # Clic sur l'image
        self.ui.baseView.mousePressEvent = self.basePictureOnClick
        self.ui.baseView.mouseReleaseEvent = self.basePictureOnClick
        
        # Boutons pour modifications visible
        self.ui.modeROI.clicked.connect(self.setRoiMode)
        self.ui.modeNormal.clicked.connect(self.setNoMode)
        
        # Menu
        self.ui.actionOuvrir.triggered.connect(self.openPicture)
        self.ui.actionQuitter.triggered.connect(QtGui.qApp.quit)
    
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
        self.showBasePicture(self.basePicture)
    
    # Affiche l'image de base
    def showBasePicture(self, picture):
        # Taille de l'image
        w = picture.image.size[0]
        h = picture.image.size[1]
        
        self.baseScene.clear()
        self.baseScene.addPixmap(picture.getPixmap())
        self.baseScene.update()
    
    # Affiche l'image résultat
    def showResultPicture(self, picture):
        # Taille de l'image
        w = picture.image.size[0]
        h = picture.image.size[1]
        
        self.resultScene.clear()
        self.resultScene.addPixmap(picture.getPixmap())
        self.resultScene.update()
    
    # Passe l'image resultat en image de base
    def switchResult2Base(self):
        if self.resultPicture.image == 0:
            print "Pas encore d'image résultat ..."
            return
        
        self.basePicture = self.resultPicture
        
        # Affiche l'image
        self.showBasePicture(self.basePicture)
    
    # Désactive les mode particuliers
    def setNoMode(self):
        self.ui.baseView.setCursor(QtGui.QCursor(QtCore.Qt.ArrowCursor))
        self.roiActivated = False
        self.roiCorner = []
        
    # Active le mode ROI
    def setRoiMode(self):
        self.ui.baseView.setCursor(QtGui.QCursor(QtCore.Qt.CrossCursor))
        self.roiActivated = True
    
    # Clic sur l'image source
    def basePictureOnClick(self, mouseEvent):
        if self.roiActivated:
            # Récupére les coordonées sur la scéne
            pos = self.ui.baseView.mapToScene(mouseEvent.pos())
            x = int(pos.x())
            y = int(pos.y())
            
            # Dépassement des X
            if x < 0:
                x = 0
            elif x >= self.basePicture.image.size[0]:
                x = self.basePicture.image.size[0] - 1
            
            # Dépassement des Y
            if y < 0:
                y = 0
            elif y >= self.basePicture.image.size[1]:
                y = self.basePicture.image.size[1] - 1
                
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
        
        # Affiche l'image
        self.resultPicture.setImage(roi)
        self.showResultPicture(self.resultPicture)


if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    windows = MainWindows()
    windows.show()
    sys.exit(app.exec_())