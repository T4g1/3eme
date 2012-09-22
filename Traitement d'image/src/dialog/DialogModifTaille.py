#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogModifTaille import Ui_DialogModifTaille


##
# Fenetre de dialogue permettant de modifier
# la taille de l'image de base
#
class DialogModifTaille(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogModifTaille()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        super(DialogModifTaille, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        # Affiche les dimensions actuelles
        self.ui.inputWidth.setText("%d" % size[0])
        self.ui.inputHeight.setText("%d" % size[1])
        
        super(DialogModifTaille, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None:
            self.close()
        
        # Récupére les dimensions
        try:
            w = int(self.ui.inputWidth.text())
            h = int(self.ui.inputHeight.text())
        except ValueError:
            w = 0
            h = 0
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image)
        self.parent.resultPicture.setSize(w, h)
        self.parent.showResultPicture()
        
        self.close()