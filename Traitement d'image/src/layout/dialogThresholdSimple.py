# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialogThresholdSimple.ui'
#
# Created: Mon Oct 22 19:34:19 2012
#      by: PyQt4 UI code generator 4.9.1
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    _fromUtf8 = lambda s: s

class Ui_DialogThresholdSimple(object):
    def setupUi(self, DialogThresholdSimple):
        DialogThresholdSimple.setObjectName(_fromUtf8("DialogThresholdSimple"))
        DialogThresholdSimple.resize(387, 270)
        self.buttonBox = QtGui.QDialogButtonBox(DialogThresholdSimple)
        self.buttonBox.setGeometry(QtCore.QRect(40, 230, 341, 32))
        self.buttonBox.setOrientation(QtCore.Qt.Horizontal)
        self.buttonBox.setStandardButtons(QtGui.QDialogButtonBox.Cancel|QtGui.QDialogButtonBox.Ok)
        self.buttonBox.setObjectName(_fromUtf8("buttonBox"))
        self.seuilSlider = QtGui.QSlider(DialogThresholdSimple)
        self.seuilSlider.setGeometry(QtCore.QRect(50, 10, 261, 19))
        self.seuilSlider.setMaximum(255)
        self.seuilSlider.setOrientation(QtCore.Qt.Horizontal)
        self.seuilSlider.setObjectName(_fromUtf8("seuilSlider"))
        self.label = QtGui.QLabel(DialogThresholdSimple)
        self.label.setGeometry(QtCore.QRect(10, 10, 46, 13))
        self.label.setFocusPolicy(QtCore.Qt.NoFocus)
        self.label.setObjectName(_fromUtf8("label"))
        self.label_2 = QtGui.QLabel(DialogThresholdSimple)
        self.label_2.setGeometry(QtCore.QRect(10, 130, 46, 13))
        self.label_2.setObjectName(_fromUtf8("label_2"))
        self.seuilBox = QtGui.QSpinBox(DialogThresholdSimple)
        self.seuilBox.setGeometry(QtCore.QRect(330, 10, 42, 22))
        self.seuilBox.setMaximum(255)
        self.seuilBox.setObjectName(_fromUtf8("seuilBox"))
        self.apercu = QtGui.QLabel(DialogThresholdSimple)
        self.apercu.setGeometry(QtCore.QRect(70, 50, 151, 161))
        self.apercu.setObjectName(_fromUtf8("apercu"))

        self.retranslateUi(DialogThresholdSimple)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("accepted()")), DialogThresholdSimple.accept)
        QtCore.QObject.connect(self.buttonBox, QtCore.SIGNAL(_fromUtf8("rejected()")), DialogThresholdSimple.reject)
        QtCore.QMetaObject.connectSlotsByName(DialogThresholdSimple)

    def retranslateUi(self, DialogThresholdSimple):
        DialogThresholdSimple.setWindowTitle(QtGui.QApplication.translate("DialogThresholdSimple", "Seuillage simple", None, QtGui.QApplication.UnicodeUTF8))
        self.label.setText(QtGui.QApplication.translate("DialogThresholdSimple", "Seuil :", None, QtGui.QApplication.UnicodeUTF8))
        self.label_2.setText(QtGui.QApplication.translate("DialogThresholdSimple", "Apercu :", None, QtGui.QApplication.UnicodeUTF8))
        self.apercu.setText(QtGui.QApplication.translate("DialogThresholdSimple", "TextLabel", None, QtGui.QApplication.UnicodeUTF8))

