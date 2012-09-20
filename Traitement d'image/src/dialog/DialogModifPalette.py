#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogModifPalette import Ui_DialogModifPalette


##
# Fenetre de dialogue permettant de modifier
# la taille de l'image de base
#
class DialogModifPalette(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogModifPalette()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.ui.inputRed.setText("0")
        self.ui.inputGreen.setText("0")
        self.ui.inputBlue.setText("0")
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        if self.parent != None:
            self.parent.setNoMode()
        
        super(DialogModifPalette, self).accept()
    
    # Affiche le dialogue
    def show(self):
        if self.parent == None:
            self.close()
        
        self.parent.setColorPickerMode()
        super(DialogModifPalette, self).show()
    
    # Récupére une couleur
    def setPickedColor(self, color):
        self.ui.inputRed.setText("%d" % color[0])
        self.ui.inputGreen.setText("%d" % color[1])
        self.ui.inputBlue.setText("%d" % color[2])
    
    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None:
            self.close()
        
        # Récupére la couleur
        try:
            r = self.ui.inputRed.text()
            g = self.ui.inputGreen.text()
            b = self.ui.inputBlue.text()
        except ValueError:
            r = 0
            g = 0
            b = 0
        
        color = (r, g, b)
        print "Nouvelle couleur :", color
        
        self.close()