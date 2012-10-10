#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogThresholdMultiple import Ui_DialogThresholdMultiple
import Picture


##
# Fenetre de dialogue permettant de modifier
# la taille de l'image de base
#
class DialogThresholdMultiple(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogThresholdMultiple()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
        
        # Affiche le seuil par défaut
        self.ui.seuilBox1.setValue(63)
        self.ui.seuilSlider1.setValue(63)
        self.ui.seuilBox2.setValue(120)
        self.ui.seuilSlider2.setValue(120)
        self.ui.seuilBox3.setValue(190)
        self.ui.seuilSlider3.setValue(190)
        
        self.ui.seuilBox1.valueChanged.connect(lambda value: self.majValue(0, value))
        self.ui.seuilSlider1.valueChanged.connect(lambda value: self.majValue(0, value))
        self.ui.seuilBox2.valueChanged.connect(lambda value: self.majValue(1, value))
        self.ui.seuilSlider2.valueChanged.connect(lambda value: self.majValue(1, value))
        self.ui.seuilBox3.valueChanged.connect(lambda value: self.majValue(2, value))
        self.ui.seuilSlider3.valueChanged.connect(lambda value: self.majValue(2, value))
    
    # Ferme le dialogue
    def close(self):
        super(DialogThresholdMultiple, self).accept()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode != "L":
            print "Operation interdite sur une image autre qu'en niveaux de gris"
            return
        
        self.majApercu()
        
        super(DialogThresholdMultiple, self).show()

    # Rejet des modifications
    def reject(self):
        self.close()

    # Accepte le changement de taille
    def accept(self):
        if self.parent == None:
            self.close()
        
        # Récupére les seuils
        try:
            l_seuil = []
            l_seuil.append(self.ui.seuilBox1.value())
            l_seuil.append(self.ui.seuilBox2.value())
            l_seuil.append(self.ui.seuilBox3.value())
        except ValueError:
            l_seuil = [125]
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        self.parent.resultPicture.multiThreshold(l_seuil)
        self.parent.showResultPicture()
        
        self.close()
    
    # Met a jour le slider et le spin box et l'apercu
    def majValue(self, group, value):
        if group == 0:
            self.ui.seuilBox1.setValue(value)
            self.ui.seuilSlider1.setValue(value)
        elif group == 1:
            self.ui.seuilBox2.setValue(value)
            self.ui.seuilSlider2.setValue(value)
        else:
            self.ui.seuilBox3.setValue(value)
            self.ui.seuilSlider3.setValue(value)
        
        # Met a jour l'apercu
        self.majApercu()
    
    # Met a jour l'apercu
    def majApercu(self):
        # Copie l'apercu généré par la fenetre principale
        self.apercu = Picture.Picture()
        self.apercu.setImage(self.parent.apercu.image.copy())
        
        # Récupére les seuils
        try:
            l_seuil = []
            l_seuil.append(self.ui.seuilBox1.value())
            l_seuil.append(self.ui.seuilBox2.value())
            l_seuil.append(self.ui.seuilBox3.value())
        except ValueError:
            l_seuil = [125]
        
        # Applique le threshold dessus
        self.apercu.multiThreshold(l_seuil)
        
        # Met a jour le label
        self.ui.apercu.setPixmap(self.apercu.getPixmap())