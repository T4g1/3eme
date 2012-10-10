#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogAutre import Ui_DialogAutre
import Picture
import time


##
# Fenetre de dialogue permettant d'appliquer
# une erosion/dilatation/ouverture/fermeture
#
class DialogAutre(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogAutre()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        super(DialogAutre, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode != "L":
            print "Operation interdite sur une image autre qu'en niveaux de gris"
            return
        
        super(DialogAutre, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Appliquer le filtre
    def accept(self):
        if self.parent == None:
            self.close()
        
        quantity = self.ui.quantityBox.value()
        filtre = self.ui.filtreBox.currentText()
        
        self.applyFilter(filtre, quantity)
        
        self.close()
    
    # Applique le filtre
    def applyFilter(self, filtre, quantity):
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        
        debut = time.time()
        
        # Applique le filtre le nombre de fois requis
        for i in [0] * quantity:
            if filtre == u"Erosion":
                self.parent.resultPicture.erosion()
            elif filtre == u"Dilatation":
                self.parent.resultPicture.dilatation()
            elif filtre == u"Ouverture":
                self.parent.resultPicture.ouverture()
            else:
                self.parent.resultPicture.fermeture()
        
        fin = time.time()
        self.parent.showResultPicture()
        
        print "Application du filtre", filtre, quantity, "fois en", fin - debut, "s"