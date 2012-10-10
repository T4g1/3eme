#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogModifPalette import Ui_DialogModifPalette


##
# Fenetre de dialogue permettant de modifier
# la palette de l'image de base
#
class DialogModifPalette(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogModifPalette()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.ui.inputRed.setMinimum(0)
        self.ui.inputGreen.setMinimum(0)
        self.ui.inputBlue.setMinimum(0)
        self.ui.inputGray.setMinimum(0)
        
        self.ui.inputRed.setMaximum(255)
        self.ui.inputGreen.setMaximum(255)
        self.ui.inputBlue.setMaximum(255)
        self.ui.inputGray.setMaximum(255)
            
        self.ui.inputX.setMinimum(0)
        self.ui.inputY.setMinimum(0)
        
        self.parent = parent
        
        # Evenements
        self.ui.inputRed.valueChanged.connect(self.majColor)
        self.ui.inputGreen.valueChanged.connect(self.majColor)
        self.ui.inputBlue.valueChanged.connect(self.majColor)
        self.ui.inputGray.valueChanged.connect(self.majColor)
        self.ui.inputX.valueChanged.connect(self.majColor)
        self.ui.inputY.valueChanged.connect(self.majColor)
    
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
        
        if self.parent.basePicture.image.mode != "L" and self.parent.basePicture.image.mode != "P":
            print "Operation interdite sur une image couleur"
            return
        
        self.ui.inputX.setMaximum(self.parent.basePicture.image.size[0])
        self.ui.inputY.setMaximum(self.parent.basePicture.image.size[1])
        
        self.parent.setColorPickerMode()
        super(DialogModifPalette, self).show()
    
    # Met a jour les couleurs affichées
    def majColor(self, value):
        # Récupére les saisies
        r = self.ui.inputRed.value()
        g = self.ui.inputGreen.value()
        b = self.ui.inputBlue.value()
        
        gray = self.ui.inputGray.value()
        
        x = self.ui.inputX.value()
        y = self.ui.inputY.value()
        
        # Récupére l'ancienne couleur
        oldColor = self.parent.basePicture.getPixel(x, y)
        
        # Crée les pixmap
        oldColorPixmap = QtGui.QPixmap(25, 25)
        newColorPixmap = QtGui.QPixmap(25, 25)
        
        # Niveau de gris
        if self.parent.basePicture.image.mode == "L":
            oldColorPixmap.fill(QtGui.QColor(oldColor, oldColor, oldColor))
            newColorPixmap.fill(QtGui.QColor(gray, gray, gray))
        # Couleur
        else:
            oldColorPixmap.fill(QtGui.QColor(oldColor[0], oldColor[1], oldColor[2]))
            newColorPixmap.fill(QtGui.QColor(r, g, b))
        
        # Met a jout l'image
        self.ui.oldColor.setPixmap(oldColorPixmap)
        self.ui.newColor.setPixmap(newColorPixmap)
    
    # Récupére une couleur et sa position
    def setPickedColor(self, pos, color):
        # Niveau de gris
        if type(color) == type(0):
            self.ui.inputRed.setValue(0)
            self.ui.inputGreen.setValue(0)
            self.ui.inputBlue.setValue(0)
            self.ui.inputGray.setValue(color)
        # Indexées
        else:
            self.ui.inputRed.setValue(color[0])
            self.ui.inputGreen.setValue(color[1])
            self.ui.inputBlue.setValue(color[2])
            self.ui.inputGray.setValue(0)
        
        self.ui.inputX.setValue(pos[0])
        self.ui.inputY.setValue(pos[1])
    
    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None or self.parent.basePicture.image == 0:
            self.close()
            return
        
        # Récupére la couleur
        r = self.ui.inputRed.value()
        g = self.ui.inputGreen.value()
        b = self.ui.inputBlue.value()
        
        gray = self.ui.inputGray.value()
        
        x = self.ui.inputX.value()
        y = self.ui.inputY.value()
        
        pos = (x, y)
        if self.parent.basePicture.image.mode == "L":
            color = (gray)
        else:
            color = (r, g, b)
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        self.parent.resultPicture.setPaletteColor(pos, color)
        self.parent.showResultPicture()
        
        self.close()