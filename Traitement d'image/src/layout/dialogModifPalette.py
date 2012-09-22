# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogModifPalette.ui'
#
# Created: Sat Sep 22 00:59:50 2012
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
        self.label.setGeometry(QtCore.QRect(20, 10, 81, 16))
        self.label.setObjectName(_fromUtf8("label"))
        self.label_2 = QtGui.QLabel(DialogModifPalette)
        self.label_2.setGeometry(QtCore.QRect(20, 40, 71, 16))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.label_3 = QtGui.QLabel(DialogModifPalette)
        self.label_3.setGeometry(QtCore.QRect(20, 70, 71, 16))
        self.label_3.setFrameShape(QtGui.QFrame.NoFrame)
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
        self.label_4 = QtGui.QLabel(DialogModifPalette)
        self.label_4.setGeometry(QtCore.QRect(280, 10, 46, 13))
        self.label_4.setObjectName(_fromUtf8("label_4"))
        self.label_5 = QtGui.QLabel(DialogModifPalette)
        self.label_5.setGeometry(QtCore.QRect(280, 40, 46, 13))
        self.label_5.setObjectName(_fromUtf8("label_5"))
        self.inputX = QtGui.QLineEdit(DialogModifPalette)
        self.inputX.setGeometry(QtCore.QRect(330, 10, 51, 20))
        self.inputX.setObjectName(_fromUtf8("inputX"))
        self.inputY = QtGui.QLineEdit(DialogModifPalette)
        self.inputY.setGeometry(QtCore.QRect(330, 40, 51, 20))
        self.inputY.setObjectName(_fromUtf8("inputY"))
        self.label_6 = QtGui.QLabel(DialogModifPalette)
        self.label_6.setGeometry(QtCore.QRect(20, 120, 131, 16))
        self.label_6.setObjectName(_fromUtf8("label_6"))
        self.inputGray = QtGui.QLineEdit(DialogModifPalette)
        self.inputGray.setGeometry(QtCore.QRect(150, 120, 113, 20))
        self.inputGray.setObjectName(_fromUtf8("inputGray"))
        self.label_7 = QtGui.QLabel(DialogModifPalette)
        self.label_7.setGeometry(QtCore.QRect(50, 100, 46, 13))
        self.label_7.setObjectName(_fromUtf8("label_7"))

        self.retranslateUi(DialogModifPalette)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogModifPalette.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogModifPalette.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogModifPalette)

    def retranslateUi(self, DialogModifPalette):
        DialogModifPalette.setWindowTitle(QtGui.QApplication.translate("DialogModifPalette", "Modifier la palette", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogModifPalette", "Rouge (0-255) :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogModifPalette", "Vert (0-255) :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("DialogModifPalette", "Bleu (0-255) :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_4.setText(QtGui.QApplication.translate("DialogModifPalette", "X :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_5.setText(QtGui.QApplication.translate("DialogModifPalette", "Y :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_6.setText(QtGui.QApplication.translate("DialogModifPalette", "Niveau de gris (0 - 255) :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_7.setText(QtGui.QApplication.translate("DialogModifPalette", "ou", None, QtGui.QApplication.UnicodeUTF8))

