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
        
        self.parent = parent
    
    # Ferme le dialogue
    def close(self):
        if self.parent != None:
            self.parent.setNoMode()
        
        super(DialogModifPalette, self).accept()
    
    # Affiche le dialogue
    def show(self):
        if self.parent == None:
            return
        
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode != "L":
            print "Operation interdite sur une image couleur"
            return
        
        self.parent.setColorPickerMode()
        super(DialogModifPalette, self).show()
    
    # Récupére une couleur et sa position
    def setPickedColor(self, pos, color):
        # Niveau de gris
        if type(color) == type(0):
            self.ui.inputRed.setText("0")
            self.ui.inputGreen.setText("0")
            self.ui.inputBlue.setText("0")
            self.ui.inputGray.setText("%d" % color)
        # Indexées
        else:
            self.ui.inputRed.setText("%d" % color[0])
            self.ui.inputGreen.setText("%d" % color[1])
            self.ui.inputBlue.setText("%d" % color[2])
            self.ui.inputGray.setText("0")
        
        self.ui.inputX.setText("%d" % pos[0])
        self.ui.inputY.setText("%d" % pos[1])
    
    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None or self.parent.basePicture.image == 0:
            self.close()
            return
        
        # Récupére la couleur
        try:
            r = int(self.ui.inputRed.text())
            g = int(self.ui.inputGreen.text())
            b = int(self.ui.inputBlue.text())
            
            gray = int(self.ui.inputGray.text())
            
            x = int(self.ui.inputX.text())
            y = int(self.ui.inputY.text())
        except ValueError:
            r = 0
            g = 0
            b = 0
            
            gray = 0
            
            x = 0
            y = 0
        
        oldColor = self.parent.basePicture.getPixel(x, y)
        if self.parent.basePicture.image.mode == "L":
            color = (gray)
        else:
            color = (r, g, b)
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        self.parent.resultPicture.setPaletteColor(oldColor, color)
        self.parent.showResultPicture()
        
        self.close()