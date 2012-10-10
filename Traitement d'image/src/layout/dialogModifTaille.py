# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogModifTaille.ui'
#
# Created: Fri Sep 28 20:56:10 2012
#      by: PyQt4 UI code generator 4.9.4
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogModifTaille(object):
    def setupUi(self, DialogModifTaille):
        DialogModifTaille.setObjectName(_fromUtf8("DialogModifTaille"))
        DialogModifTaille.resize(287, 105)
        DialogModifTaille.setContextMenuPolicy(QtCore.Qt.DefaultContextMenu)
        self.buttonBox = QtGui.QDialogButtonBox(DialogModifTaille)
        self.buttonBox.setGeometry(QtCore.QRect(-60, 70, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.label = QtGui.QLabel(DialogModifTaille)
        self.label.setGeometry(QtCore.QRect(10, 10, 46, 13))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_2 = QtGui.QLabel(DialogModifTaille)
        self.label_2.setGeometry(QtCore.QRect(10, 40, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.inputWidth = QtGui.QLineEdit(DialogModifTaille)
        self.inputWidth.setGeometry(QtCore.QRect(160, 10, 113, 20))
        self.inputWidth.setObjectName(_fromUtf8("inputWidth"))
        self.inputHeight = QtGui.QLineEdit(DialogModifTaille)
        self.inputHeight.setGeometry(QtCore.QRect(160, 40, 113, 20))
        self.inputHeight.setObjectName(_fromUtf8("inputHeight"))

        self.retranslateUi(DialogModifTaille)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogModifTaille.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogModifTaille.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogModifTaille)

    def retranslateUi(self, DialogModifTaille):
        DialogModifTaille.setWindowTitle(QtGui.QApplication.translate("DialogModifTaille", "Modification de la taille de l\'image", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogModifTaille", "Largeur :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogModifTaille", "Hauteur :", None, QtGui.QApplication.UnicodeUTF8))

