# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogModifPalette.ui'
#
# Created: Wed Oct 17 11:41:31 2012
#      by: PyQt4 UI code generator 4.9.4
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
        DialogModifPalette.resize(307, 149)
        self.buttonBox = QtGui.QDialogButtonBox(DialogModifPalette)
        self.buttonBox.setGeometry(QtCore.QRect(-40, 110, 341, 32))
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
        self.label_4 = QtGui.QLabel(DialogModifPalette)
        self.label_4.setGeometry(QtCore.QRect(160, 40, 16, 16))
        self.label_4.setObjectName(_fromUtf8("label_4"))
        self.label_5 = QtGui.QLabel(DialogModifPalette)
        self.label_5.setGeometry(QtCore.QRect(160, 70, 16, 16))
        self.label_5.setObjectName(_fromUtf8("label_5"))
        self.label_6 = QtGui.QLabel(DialogModifPalette)
        self.label_6.setGeometry(QtCore.QRect(160, 10, 81, 16))
        self.label_6.setObjectName(_fromUtf8("label_6"))
        self.label_7 = QtGui.QLabel(DialogModifPalette)
        self.label_7.setGeometry(QtCore.QRect(130, 10, 46, 13))
        self.label_7.setObjectName(_fromUtf8("label_7"))
        self.inputGreen = QtGui.QSpinBox(DialogModifPalette)
        self.inputGreen.setGeometry(QtCore.QRect(70, 40, 42, 22))
        self.inputGreen.setObjectName(_fromUtf8("inputGreen"))
        self.inputRed = QtGui.QSpinBox(DialogModifPalette)
        self.inputRed.setGeometry(QtCore.QRect(70, 10, 42, 22))
        self.inputRed.setObjectName(_fromUtf8("inputRed"))
        self.inputBlue = QtGui.QSpinBox(DialogModifPalette)
        self.inputBlue.setGeometry(QtCore.QRect(70, 70, 42, 22))
        self.inputBlue.setObjectName(_fromUtf8("inputBlue"))
        self.inputGray = QtGui.QSpinBox(DialogModifPalette)
        self.inputGray.setGeometry(QtCore.QRect(250, 10, 42, 22))
        self.inputGray.setObjectName(_fromUtf8("inputGray"))
        self.inputX = QtGui.QSpinBox(DialogModifPalette)
        self.inputX.setGeometry(QtCore.QRect(180, 40, 42, 22))
        self.inputX.setObjectName(_fromUtf8("inputX"))
        self.inputY = QtGui.QSpinBox(DialogModifPalette)
        self.inputY.setGeometry(QtCore.QRect(180, 70, 42, 22))
        self.inputY.setObjectName(_fromUtf8("inputY"))
        self.oldColor = QtGui.QLabel(DialogModifPalette)
        self.oldColor.setGeometry(QtCore.QRect(10, 100, 41, 31))
        self.oldColor.setText(_fromUtf8(""))
        self.oldColor.setObjectName(_fromUtf8("oldColor"))
        self.newColor = QtGui.QLabel(DialogModifPalette)
        self.newColor.setGeometry(QtCore.QRect(90, 100, 41, 31))
        self.newColor.setText(_fromUtf8(""))
        self.newColor.setObjectName(_fromUtf8("newColor"))
        self.label_8 = QtGui.QLabel(DialogModifPalette)
        self.label_8.setGeometry(QtCore.QRect(60, 110, 46, 13))
        self.label_8.setObjectName(_fromUtf8("label_8"))

        self.retranslateUi(DialogModifPalette)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogModifPalette.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogModifPalette.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogModifPalette)

    def retranslateUi(self, DialogModifPalette):
        DialogModifPalette.setWindowTitle(QtGui.QApplication.translate("DialogModifPalette", "Modifier la palette", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogModifPalette", "Rouge :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogModifPalette", "Vert  :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_3.setText(QtGui.QApplication.translate("DialogModifPalette", "Bleu  :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_4.setText(QtGui.QApplication.translate("DialogModifPalette", "X :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_5.setText(QtGui.QApplication.translate("DialogModifPalette", "Y :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_6.setText(QtGui.QApplication.translate("DialogModifPalette", "Niveau de gris  :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_7.setText(QtGui.QApplication.translate("DialogModifPalette", "ou", None, QtGui.QApplication.UnicodeUTF8))
        self.label_8.setText(QtGui.QApplication.translate("DialogModifPalette", "->", None, QtGui.QApplication.UnicodeUTF8))

