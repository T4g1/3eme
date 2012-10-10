#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogThresholdSimple import Ui_DialogThresholdSimple
import Picture


##
# Fenetre de dialogue permettant de modifier
# la taille de l'image de base
#
class DialogThresholdSimple(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogThresholdSimple()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
        
        # Affiche le seuil par défaut
        self.ui.seuilBox.setValue(125)
        self.ui.seuilSlider.setValue(125)
        
        self.ui.seuilBox.valueChanged.connect(self.majValue)
        self.ui.seuilSlider.valueChanged.connect(self.majValue)
    
    # Ferme le dialogue
    def close(self):
        super(DialogThresholdSimple, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode != "L":
            print "Operation interdite sur une image autre qu'en niveaux de gris"
            return
        
        self.majApercu(self.ui.seuilBox.value())
        
        super(DialogThresholdSimple, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None:
            self.close()
        
        # Récupére les dimensions
        try:
            seuil = self.ui.seuilBox.value()
        except ValueError:
            seuil = 125
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        self.parent.resultPicture.threshold(seuil)
        self.parent.showResultPicture()
        
        self.close()
    
    # Met a jour le slider et le spin box et l'apercu
    def majValue(self, value):
        self.ui.seuilBox.setValue(value)
        self.ui.seuilSlider.setValue(value)
        
        # Met a jour l'apercu
        self.majApercu(value)
    
    # Met a jour l'apercu
    def majApercu(self, value):
        # Copie l'apercu généré par la fenetre principale
        self.apercu = Picture.Picture()
        self.apercu.setImage(self.parent.apercu.image.copy())
        
        # Applique le threshold dessus
        self.apercu.threshold(value)
        
        # Met a jour le label
        self.ui.apercu.setPixmap(self.apercu.getPixmap())