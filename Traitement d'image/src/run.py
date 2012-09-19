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
        
        # Conversion
        self.basePicture = Image.open(filename)
        self.QtImage = ImageQt.ImageQt(self.basePicture)
        self.QtGuiImage = QtGui.QImage(self.QtImage)
        
        # Affichage
        self.pixmap = QtGui.QPixmap.fromImage(self.QtGuiImage)
        self.ui.basePicture.setPixmap(self.pixmap)
        

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    windows = MainWindows()
    windows.show()
    sys.exit(app.exec_())