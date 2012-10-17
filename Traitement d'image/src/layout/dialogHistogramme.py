# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogHistogramme.ui'
#
# Created: Wed Oct 17 11:41:34 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogHistogramme(object):
    def setupUi(self, DialogHistogramme):
        DialogHistogramme.setObjectName(_fromUtf8("DialogHistogramme"))
        DialogHistogramme.resize(629, 405)
        self.histogrammeBaseView = QtGui.QGraphicsView(DialogHistogramme)
        self.histogrammeBaseView.setGeometry(QtCore.QRect(10, 30, 300, 300))
        self.histogrammeBaseView.setObjectName(_fromUtf8("histogrammeBaseView"))
        self.label = QtGui.QLabel(DialogHistogramme)
        self.label.setGeometry(QtCore.QRect(10, 10, 181, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.actionEgaliser = QtGui.QPushButton(DialogHistogramme)
        self.actionEgaliser.setGeometry(QtCore.QRect(470, 350, 75, 23))
        self.actionEgaliser.setObjectName(_fromUtf8("actionEgaliser"))
        self.actionFermer = QtGui.QPushButton(DialogHistogramme)
        self.actionFermer.setGeometry(QtCore.QRect(550, 350, 75, 23))
        self.actionFermer.setObjectName(_fromUtf8("actionFermer"))
        self.histogrammeResultView = QtGui.QGraphicsView(DialogHistogramme)
        self.histogrammeResultView.setGeometry(QtCore.QRect(320, 30, 300, 300))
        self.histogrammeResultView.setObjectName(_fromUtf8("histogrammeResultView"))
        self.label_2 = QtGui.QLabel(DialogHistogramme)
        self.label_2.setGeometry(QtCore.QRect(320, 10, 181, 16))
        self.label_2.setObjectName(_fromUtf8("label_2"))

        self.retranslateUi(DialogHistogramme)
        QtCore.QMetaObject.connectSlotsByName(DialogHistogramme)

    def retranslateUi(self, DialogHistogramme):
        DialogHistogramme.setWindowTitle(QtGui.QApplication.translate("DialogHistogramme", "Histogramme", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogHistogramme", "Histogramme de l\'image de base :", None, QtGui.QApplication.UnicodeUTF8))
        self.actionEgaliser.setText(QtGui.QApplication.translate("DialogHistogramme", "Egaliser", None, QtGui.QApplication.UnicodeUTF8))
        self.actionFermer.setText(QtGui.QApplication.translate("DialogHistogramme", "Fermer", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogHistogramme", "Histogramme de l\'image résultat :", None, QtGui.QApplication.UnicodeUTF8))

