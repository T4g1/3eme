#!/usr/bin/python
# -*- coding: utf-8 -*-

import sys, os
import time
from PyQt4 import QtCore, QtGui
import Image
import ImageQt
from layout.windowsLayout import Ui_MainWindow


##
# Classe principale
#
class MainWindows(QtGui.QMainWindow):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.basePicture = 0
        self.resultPicture = 0
        
        # ROI
        self.roiCorner = [];
        self.roiActivated = False
        
        # Clic sur l'image
        self.ui.basePicture.mousePressEvent = self.basePictureOnClick
        self.ui.basePicture.mouseReleaseEvent = self.basePictureOnClick
        
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
        
        # Conversion et ouverture du fichier
        self.basePicture = Image.open(filename)
        self.QtGuiImage = self.getQtGuiImage(self.basePicture)
        
        # Affichage
        self.showBasePicture(self.QtGuiImage)
    
    # Donne une image QtGui depuis une image PIL
    def getQtGuiImage(self, pilImage):
        self.QtImage = ImageQt.ImageQt(pilImage)
        return QtGui.QImage(self.QtImage)
    
    # Affiche l'image de base
    def showBasePicture(self, qtImage):
        self.pixmap = QtGui.QPixmap.fromImage(qtImage)
        self.ui.basePicture.setPixmap(self.pixmap)
    
    # Affiche l'image résultat
    def showResultPicture(self, qtImage):
        self.pixmap = QtGui.QPixmap.fromImage(qtImage)
        self.ui.resultPicture.setPixmap(self.pixmap)
    
    # Passe l'image resultat en image de base
    def switchResult2Base(self):
        if self.resultPicture == 0:
            print "Pas encore d'image résultat ..."
            return
        
        self.basePicture = self.resultPicture
        
        # Affiche l'image
        self.QtGuiImage = self.getQtGuiImage(self.basePicture)
        self.showBasePicture(self.QtGuiImage)
    
    # Désactive les mode particuliers
    def setNoMode(self):
        self.ui.basePicture.setCursor(QtGui.QCursor(QtCore.Qt.ArrowCursor))
        self.roiActivated = False
        self.roiCorner = []
        
    # Active le mode ROI
    def setRoiMode(self):
        self.ui.basePicture.setCursor(QtGui.QCursor(QtCore.Qt.CrossCursor))
        self.roiActivated = True
    
    # Clic sur l'image source
    def basePictureOnClick(self, mouseEvent):
        if self.roiActivated:
            x = mouseEvent.pos().x() 
            y = mouseEvent.pos().y()
            self.roiCorner.append((x, y))
            
            if len(self.roiCorner) >= 2:
                self.showROI()
                self.setNoMode()
    
    # Affiche le ROI séléctionné
    def showROI(self):
        # Taille de la ROI
        # Crée l'image
        # Place les pixels voulu dans l'image
        # Affiche l'image
        pass


if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    windows = MainWindows()
    windows.show()
    sys.exit(app.exec_())