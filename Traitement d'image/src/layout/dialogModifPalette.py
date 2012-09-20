# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogModifPalette.ui'
#
# Created: Thu Sep 20 23:55:10 2012
#      by: PyQt4 UI code generator 4.9.1
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogModifPalette(object):
    def setupUi(self, DialogModifPalette):
        DialogModifPalette.setObjectName(_fromUtf8("DialogModifPalette"))
        DialogModifPalette.resize(400, 300)
        self.buttonBox = QtGui.QDialogButtonBox(DialogModifPalette)
        self.buttonBox.setGeometry(QtCore.QRect(30, 240, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.label = QtGui.QLabel(DialogModifPalette)
        self.label.setGeometry(QtCore.QRect(20, 10, 46, 13))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_2 = QtGui.QLabel(DialogModifPalette)
        self.label_2.setGeometry(QtCore.QRect(20, 40, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.label_3 = QtGui.QLabel(DialogModifPalette)
        self.label_3.setGeometry(QtCore.QRect(20, 70, 46, 13))
        self.label_3.setObjectName(_fromUtf8("label_3"))
        self.inputRed = QtGui.QLineEdit(DialogModifPalette)
        self.inputRed.setGeometry(QtCore.QRect(100, 10, 113, 20))
        self.inputRed.setObjectName(_fromUtf8("inputRed"))
        self.inputGreen = QtGui.QLineEdit(DialogModifPalette)
        self.inputGreen.setGeometry(QtCore.QRect(100, 40, 113, 20))
        self.inputGreen.setObjectName(_fromUtf8("inputGreen"))
        self.inputBlue = QtGui.QLineEdit(DialogModifPalette)
        self.inputBlue.setGeometry(QtCore.QRect(100, 70, 113, 20))
        self.inputBlue.setObjectName(_fromUtf8("inputBlue"))

        self.retranslateUi(DialogModifPalette)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogModifPalette.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogModifPalette.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogModifPalette)

    def retranslateUi(self, DialogModifPalette):
        DialogModifPalette.setWindowTitle(QtGui.QApplication.translate("DialogModifPalette", "Modifier la palette", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogModifPalette", "Rouge :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogModifPalette", "Vert :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("DialogModifPalette", "Bleu :", None, QtGui.QApplication.UnicodeUTF8))

