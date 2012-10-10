#!/usr/bin/python
# -*- coding: utf-8 -*-

from PyQt4 import QtCore, QtGui
from layout.dialogHistogramme import Ui_DialogHistogramme


##
# Fenetre de dialogue permettant
# l'Histogramme de l'image
#
class DialogHistogramme(QtGui.QDialog):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)
        self.ui = Ui_DialogHistogramme()
        self.ui.setupUi(self)
        # Fin du wrapper
        
        self.parent = parent
        
        # Crée la scéne pour l'histogramme de l'image de base
        self.baseScene = QtGui.QGraphicsScene()
        self.ui.histogrammeBaseView.setScene(self.baseScene)
        
        # Crée la scéne pour l'histogramme de l'image résultat
        self.resultScene = QtGui.QGraphicsScene()
        self.ui.histogrammeResultView.setScene(self.resultScene)
        
        self.ui.actionEgaliser.clicked.connect(self.egaliser)
        self.ui.actionFermer.clicked.connect(self.close)
    
    # Ferme le dialogue
    def close(self):
        super(DialogHistogramme, self).close()
    
    # Affiche le dialogue
    def show(self, size):
        if self.parent.basePicture.image == 0:
            print "Operation interdite sans image"
            return
        
        if self.parent.basePicture.image.mode == "P":
            print "Operation interdite sur une image en couleur indéxée"
            return
        
        # Affiche les histogramme
        self.showHistogrammeBase()
        self.showHistogrammeResult()
        
        super(DialogHistogramme, self).show()

    # Egaliser l'histogramme
    def egaliser(self):
        if self.parent == None:
            self.close()
        
        self.parent.resultPicture.setImage(self.parent.basePicture.image.copy())
        self.parent.resultPicture.egalise()
        self.parent.showResultPicture()
    
    # Affiche l'histogramme de l'image de base
    def showHistogrammeBase(self):
        if self.parent.basePicture.image == 0:
            return
        
        histogramme = self.parent.basePicture.getHistogramme()
        self.showHistogramme(histogramme, self.ui.histogrammeBaseView)
    
    # Affiche l'histogramme de l'image résultat
    def showHistogrammeResult(self):
        if self.parent.resultPicture.image == 0:
            return
        
        histogramme = self.parent.resultPicture.getHistogramme()
        self.showHistogramme(histogramme, self.ui.histogrammeResultView)
    
    # Affiche l'histogramme donné sur la vue donnée
    def showHistogramme(self, histogramme, view):
        # Dimension de la vue
        viewW = view.size().width()
        viewH = view.size().height() - 5     # -5 car sinon, la barre de défilement apparait
        
        # Scene affichée par la vue
        scene = view.scene()
        
        barreW = viewW / len(histogramme)
        
        scene.clear()
        
        # Plus grande valeur dans l'histogramme
        maxCount = max(histogramme)
        
        # Parcourt de l'histogramme
        for color, count in enumerate(histogramme):
            # Calcule la hauteur de la barre selon la valeur de count maximale
            if count > 0:
                h = int(viewH / (maxCount / float(count)))
            else:
                h = 0
            
            scene.addRect(color * barreW, viewH - h, barreW, h, QtGui.QPen(), QtGui.QBrush(QtCore.Qt.SolidPattern))
        
        scene.update()