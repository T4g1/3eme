#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogExpansion import Ui_DialogExpansion


##
# Fenetre de dialogue permettant
# l'expansion de l'image
#
class DialogExpansion(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogExpansion()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        super(DialogExpansion, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode == "P":
            print "Operation interdite sur une image en couleur indéxée"
            return
        
        self.ui.factor.setValue(2.0)
        
        super(DialogExpansion, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None:
            self.close()
        
        # Récupére le facteur d'expansion
        try:
            factor = self.ui.factor.value()
        except ValueError:
            factor = 1.0
        
        if self.ui.buttonGroup.checkedButton().text() == "Agrandir":
            self.expansion(factor)
        else:
            self.extraction(factor)
        
        self.close()
    
    # Extraction de l'image
    def extraction(self, factor):
        self.parent.resultPicture.setImage(self.parent.basePicture.image)
        self.parent.resultPicture.extract(factor)
        self.parent.showResultPicture()
    
    # Expansion de l'image
    def expansion(self, factor):
        self.parent.resultPicture.setImage(self.parent.basePicture.image)
        self.parent.resultPicture.expand(factor)
        self.parent.showResultPicture()