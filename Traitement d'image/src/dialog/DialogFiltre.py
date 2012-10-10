#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogFiltre import Ui_DialogFiltre
import Picture
import time


##
# Fenetre de dialogue permettant d'appliquer
# un filtre
#
class DialogFiltre(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogFiltre()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        super(DialogFiltre, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode != "L":
            print "Operation interdite sur une image autre qu'en niveaux de gris"
            return
        
        super(DialogFiltre, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Appliquer le filtre
    def accept(self):
        if self.parent == None:
            self.close()
        
        quantity = self.ui.quantityBox.value()
        filtre = self.ui.filtreBox.currentText()
        filtre = unicode(filtre).lower()
        
        self.applyFilter(filtre, quantity)
        
        self.close()
    
    # Applique le filtre donné le nombre de fois demandé
    def applyFilter(self, filtre, quantity):
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        
        debut = time.time()
        
        # Applique le filtre le nombre de fois requis
        for i in [0] * quantity:
            self.parent.resultPicture.filtre(filtre)
        
        fin = time.time()
        self.parent.showResultPicture()
        
        print "Application du filtre", filtre, quantity, "fois en", fin - debut, "s"